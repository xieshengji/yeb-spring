package com.xxxx.server.controller;


import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/system/admin/")
public class AdminController {

  @Autowired
  private IAdminService adminService;
  @Autowired
  private IRoleService roleService;


  @ApiOperation("获取所有操作员可以模糊查询")
  @PostMapping("/getAllAdmins")
  public List<Admin> getAllAdmins(String name){
    return adminService.getAllAdmins(name);
  }
  @ApiOperation("更新操作员")
  @PostMapping("/updateAdmin")
  public RespBean updateAdmin(@RequestBody Admin  admin){
    boolean update = adminService.updateById(admin);
    if (update){
      return RespBean.success("更新操作员成功");
    }
    return RespBean.error("更新操作员失败");
  }
  @ApiOperation("获取所有角色")
  @PostMapping("/roles")
  public List<Role> getAllRoles(){
    return roleService.list();
  }
  @ApiOperation("更新操作员角色")
  @PostMapping("/updateAdminAndRole")
  public RespBean updateAdminAndRole(Integer adminId,Integer[] rids){
    return adminService.updateAdmin(adminId,rids);
  }
  @ApiOperation("删除操作员及角色")
  @PostMapping("/deleteAdmin")
  public RespBean deleteAdmin(Integer adminId){
    return adminService.deleteAdmin(adminId);
  }
}
