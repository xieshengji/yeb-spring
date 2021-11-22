package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
  @Autowired
  private MenuRoleMapper menuRoleMapper;
  @Override
  @Transactional
  public RespBean updateMenuRole(Integer rid, Integer[] mids) {
    menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
    if (null==mids||0==mids.length){
      return RespBean.success("更新成功");
    }
    Integer num=menuRoleMapper.insertMenuRole(rid,mids);
    if (num==mids.length){
      return RespBean.success("更新成功");
    }
    return RespBean.error("更新失败");
  }
}
