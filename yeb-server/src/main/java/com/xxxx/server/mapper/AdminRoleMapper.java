package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
  /**
   * 更新操作员角色
   * @param adminId
   * @param rids
   */
  Integer updateAdmin(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
