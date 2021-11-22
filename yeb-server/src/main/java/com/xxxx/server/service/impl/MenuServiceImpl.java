package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-11-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
  @Autowired
  private MenuMapper menuMapper;

  /**
   * 根据用户id查询菜单列表
   * 这里我没用redis 不会用
   *
   * @return
   */
  @Override
  public List<Menu> getMenusByAdminId() {
    return menuMapper.getMenusByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
  }

  /**
   * 根据角色获取菜单列表
   *
   * @return
   */
  @Override
  public List<Menu> getMenusWithRole() {
    List<Menu> list = menuMapper.getMenusWithRole();
    return list;
  }

  /**
   * 查询所有菜单
   * @return
   */
  @Override
  public List<Menu> getAllMenus() {
    return menuMapper.getAllMenus();
  }
}
