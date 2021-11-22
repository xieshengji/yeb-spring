package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
public interface IAdminService extends IService<Admin> {
  /**
   * 登录之后返回token
   * @param username
   * @param password
   * @param request
   * @return
   */
  RespBean login(String code, String username, String password, HttpServletRequest request);

  /**
   *  根据username返回用户对象
   * @param username
   * @return
   */
  Admin getAdminByUserName(String username);

  /**
   * 根据用户id查询角色列表
   * @param id
   * @return
   */
  List<Role> getRoles(Integer id);

  /**
   * 获取所有操作员
   * @param name
   */
  List<Admin> getAllAdmins(String name);

  /**
   * 更新操作员角色
   * @param adminId
   * @param rids
   * @return
   */
  RespBean updateAdmin(Integer adminId, Integer[] rids);

  /**
   *  删除操作员及角色
   * @param adminId
   * @return
   */
  RespBean deleteAdmin(Integer adminId);

  /**
   * 更新用户密码
   * @param oldPass
   * @param pass
   * @param adminId
   * @return
   */
  RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

  /**
   * 更新用户头像
   * @param url
   * @param id
   * @param authentication
   * @return
   */
  RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
