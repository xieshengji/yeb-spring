package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-11-01
 */
public interface IMenuRoleService extends IService<MenuRole> {
  /**
   *根据角色id查询菜单id
   * @param rid
   * @param mids
   * @return
   */
  RespBean updateMenuRole(Integer rid, Integer[] mids);

}
