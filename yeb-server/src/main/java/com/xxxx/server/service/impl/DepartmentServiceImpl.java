package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.DepartmentMapper;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
  @Autowired
  private DepartmentMapper departmentMapper;
  /**
   * 获取所有部门
   * @return
   */
  @Override
  public List<Department> getAllDepartment() {
    Integer id = new Integer(-1);
    return  departmentMapper.getAllDepartment(id);
  }

  /**
   * 添加部门
   * @param dep
   * @return
   */
  @Override
  public RespBean addDepartment(Department dep) {
    dep.setEnabled(true);
    departmentMapper.addDep(dep);
    if (1==dep.getResult()){
      return RespBean.success("添加成功");
    }
    return RespBean.error("添加失败");

  }

  @Override
  public RespBean deleteDepartment(Integer id) {
    Department dep = new Department();
    dep.setId(id);
    departmentMapper.deleteDep(dep);
    if (-1==dep.getResult()){
      return RespBean.error("该部门有员工存在，删除失败");
    }
    if (-2==dep.getResult()){
      return RespBean.error("该部门有子部门存在，删除失败");
    }
    if (1==dep.getResult()) {
      return RespBean.success("删除成功");
    }
    return RespBean.error("删除失败");
  }
}
