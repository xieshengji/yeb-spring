package com.xxxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IMenuRoleService;
import com.xxxx.server.service.IMenuService;
import com.xxxx.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xieshengji
 * @Date: 2021-11
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissController {

  @Autowired
  private IRoleService roleService;
  @Autowired
  private IMenuService menuService;
  @Autowired
  private IMenuRoleService menuRoleService;

  @ApiOperation("获取所有角色")
  @GetMapping("/")
  public List<Role> getAllRole() {
    List<Role> roleList = roleService.list();
    return roleList;
  }

  @ApiOperation("添加角色")
  @PostMapping("/")
  public RespBean addRole(@RequestBody Role role) {
    //判断role开头的名字是否是ROLE_开头的，不是的话给他添加ROLE_前缀
    boolean startsWith = role.getName().startsWith("ROLE_");
    if (!startsWith) {
      role.setName("ROLE_" + role.getName());
    }
    boolean save = roleService.save(role);
    if (save) {
      return RespBean.success("添加角色成功");
    }
    return RespBean.error("添加角色失败");
  }

  @ApiOperation("删除角色")
  @DeleteMapping("/role/{rid}")
  public RespBean deleteRole(@PathVariable Integer rid) {
    boolean removeById = roleService.removeById(rid);
    if (removeById) {
      return RespBean.success("删除角色成功");
    }
    return RespBean.error("删除角色失败");
  }

  @ApiOperation("查询所有菜单")
  @GetMapping("/menus")
  public List<Menu> getAllMenus() {
    return menuService.getAllMenus();
  }

  @ApiOperation("根据角色id查询菜单id")
  @GetMapping("/menus/{rid}")
  public List<Integer> getByIdMenus(@PathVariable Integer rid) {
    return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid))
      //用steam流转出menuRole的菜单id
      .stream().map(MenuRole::getMid/*拿到menuRole的菜单id*/).collect(Collectors.toList());
  }

  @ApiOperation("更新角色的权限菜单")
  @PostMapping("/menus/update")
  public RespBean updateMenuRole(Integer rid,Integer[] mids){
    return menuRoleService.updateMenuRole(rid,mids);
  }
}
