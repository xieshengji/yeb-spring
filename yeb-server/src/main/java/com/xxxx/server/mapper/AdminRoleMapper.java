package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.AdminRole;
import com.xxxx.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stx
 * @since 2021-10-16
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

  /**
   * 更新操作员角色
   * @param adminId
   * @param rids
   * @return
   */
  Integer addAdminRole(@Param("adminId") Integer adminId,@Param("rids") Integer[] rids);
}
