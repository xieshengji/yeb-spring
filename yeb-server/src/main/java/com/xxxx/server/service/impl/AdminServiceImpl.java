package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.mapper.AdminRoleMapper;
import com.xxxx.server.mapper.RoleMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.AdminRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private AdminMapper adminMapper;
  @Autowired
  private RoleMapper roleMapper;
  @Autowired
  private AdminRoleMapper adminRoleMapper;

  @Value("${jwt.tokenHead}")
  private String tokenHead;

  /**
   * 登录之后返回token
   *
   *
   * @param username
   * @param password
   * @param request
   * @return
   */
  @Override
  public RespBean login(String username, String password, String code,HttpServletRequest request) {
    //从session中获取验证码
    String captcha = (String)request.getSession().getAttribute("captcha");
    if (StringUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(code)){
      return RespBean.error("验证码为空或验证码错误");
    }
    //登录
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())) {
      return RespBean.error("用户名或密码不正确");
    }
    if (!userDetails.isEnabled()) {
      return RespBean.error("帐号被禁用，请联系管理员！");
    }
    //更新security登录用户对象
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
      null,userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    //生成token
    String token = jwtTokenUtil.generateToken(userDetails);
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);
    tokenMap.put("tokenHead", tokenHead);
    return RespBean.success("登录成功", tokenMap);
  }

  /**
   *根据username返回用户对象
   * @param username
   * @return
   */
  @Override
  public Admin getAdminByUserName(String username) {
    Admin admin = adminMapper.
      selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    return admin;
  }

  /**
   * 根据用户查询角色列表
   * @param id
   * @return
   */
  @Override
  public List<Role> getRoles(Integer id) {
    List<Role> roles= roleMapper.getRoles(id);
    return roles;
  }

  /**
   * 获取所有操作员
   * @param name
   */
  @Override
  public List<Admin> getAllAdmins(String name) {
    //从SecurityContextHolder的上下文中取到admin
    Admin admin = (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return  adminMapper.getAllAdmins(admin.getId(),name);
  }

  /**
   * 更新操作员角色
   * @param adminId
   * @param rids
   * @return
   */
  @Override
  @Transactional
  public RespBean updateAdmin(Integer adminId, Integer[] rids) {
    adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
    Integer result= adminRoleMapper.updateAdmin(adminId,rids);
    //当影响的行数和修改的行数一样时，就更新成功
    if (rids.length == result) {
      return RespBean.success("更新操作员及角色成功");
    }
    return RespBean.error("更新操作员及角色失败");
  }

  /**
   * 删除操作员及角色
   * @param adminId
   * @return
   */
  @Override
  @Transactional
  public RespBean deleteAdmin(Integer adminId) {
    adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
    int result = adminMapper.delete(new QueryWrapper<Admin>().eq("id", adminId));
    if(1==result){
      return RespBean.success("删除操作员角色成功");
    }
    return RespBean.error("删除操作员角色失败");
  }

  /**
   * 更新用户密码
   * @param oldPass
   * @param pass
   * @param adminId
   * @return
   */
  @Override
  public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
    // 因为要把你原来的旧密码和前端传过来的新密码进行比较 所以要获取admin对象
    Admin admin = adminMapper.selectById(adminId);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // 比较传入的密码和旧密码是否一致 因为密码是加密的 所以需要BCryptPasswordEncoder的matches方法进行比较
    if(encoder.matches(oldPass,admin.getPassword())){
      // 对新密码进行加密 再将加密后的密码存入admin
      admin.setPassword(encoder.encode(pass));
      int result = adminMapper.updateById(admin);
      if(result == 1){
        return RespBean.success("更新成功！");
      }
    }
    return RespBean.error("更新失败！");
  }

  /**
   * 更新用户头像
   * @param url
   * @param id
   * @param authentication
   * @return
   */
  @Override
  public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
    Admin admin = adminMapper.selectById(id);
    admin.setUserFace(url);
    int result = adminMapper.updateById(admin);
    if(result == 1){
      Admin principal = (Admin) authentication.getPrincipal();
      principal.setUserFace(url);
      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
        admin,null,authentication.getAuthorities()));
      return RespBean.success("更新成功!",url);
    }
    return RespBean.error("更新失败!");
  }
}
