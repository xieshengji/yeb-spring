package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Admin;
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
public interface AdminMapper extends BaseMapper<Admin> {
  /**
   * 获取所有操作员
   * @param id
   * @param name
   * @return
   */
  List<Admin> getAllAdmins(@Param("id") Integer id, @Param("name") String name);
}
