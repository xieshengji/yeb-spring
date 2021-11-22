package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
public interface RoleMapper extends BaseMapper<Role> {
  /**
   * 根据用户查询角色列表
   * @param adminId
   * @return
   */
  List<Role> getRoles(Integer adminId);
}
