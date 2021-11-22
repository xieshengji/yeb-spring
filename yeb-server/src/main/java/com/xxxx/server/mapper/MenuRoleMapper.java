package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-11-01
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
  /**
   * 更新角色的权限菜单
   * @param rid
   * @param mids
   * @return
   */
  Integer insertMenuRole(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
