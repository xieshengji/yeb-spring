package com.xxxx.server.controller;


import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
  @Autowired
  private IDepartmentService departmentService;
  @ApiOperation(value = "获取所有部门")
  @PostMapping("/getAllDepartment")
  public List<Department> getAllDepartment(){
    return departmentService.getAllDepartment();
  }
  @ApiOperation(value = "添加部门")
  @PostMapping("/addDepartment")
  public RespBean addDepartment(@RequestBody Department dep){
    return departmentService.addDepartment(dep);
  }
  @ApiOperation(value = "删除部门")
  @PostMapping("/deleteDepartment")
  public RespBean deleteDepartment(Integer id){
    return departmentService.deleteDepartment(id);
  }

}
