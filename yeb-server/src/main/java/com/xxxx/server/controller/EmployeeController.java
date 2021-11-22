package com.xxxx.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/employee/basic/employee")
public class EmployeeController {
  @Autowired
  private IEmployeeService employeeService;
  @Autowired
  private IPoliticsStatusService politicsStatusService;
  @Autowired
  private IJoblevelService joblevelService;
  @Autowired
  private INationService nationService;
  @Autowired
  private IPositionService positionService;
  @Autowired
  private IDepartmentService departmentService;

  @ApiOperation(value = "获取所有员工")
  @PostMapping("/getAllEmployee")
  public RespPageBean getAllEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "10") Integer size,
                                     Employee employee,
                                     LocalDate[] beginDateScope
  ) {

    return employeeService.getAllEmployee(currentPage, size, employee, beginDateScope);
  }
  @ApiOperation("获取所有政治面貌")
  @GetMapping("/politicsstatus")
  public List<PoliticsStatus> getAllPoliticsStatus(){
    return politicsStatusService.list();
  }
  @ApiOperation("获取所有民族")
  @GetMapping("/nations")
  public List<Nation> getAllNations(){
    return nationService.list();
  }
  @ApiOperation("获取所有职称")
  @GetMapping("/joblevels")
  public List<Joblevel> getAllJoblevels(){
    return joblevelService.list();
  }
  @ApiOperation("获取所有职位")
  @GetMapping("/positions")
  public List<Position> getAllPositions(){
    return positionService.list();
  }
  @ApiOperation("获取所有部门")
  @GetMapping("/deps")
  public List<Department> getAllDepartments(){
    return departmentService.list();
  }

  @ApiOperation("获取工号")
  @GetMapping("/maxWorkID")
  public RespBean maxWorkID() {
    return employeeService.maxWorkID();
  }

  @ApiOperation(value = "添加新员工")
  @PostMapping("/addEmployee")
  public RespBean addEmployee(@RequestBody Employee employee) {
    return employeeService.addEmployee(employee);
  }

  @ApiOperation(value = "更新员工")
  @PostMapping("/updateEmployee")
  public RespBean updateEmployee(Employee employee) {
    return employeeService.updateEmployee(employee);
  }

  @ApiOperation(value = "删除员工")
  @PostMapping("/deleteEmployee")
  public RespBean deleteEmployee(Integer id) {
    if (employeeService.removeById(id)) {
      return RespBean.success("删除成功");
    }
    return RespBean.error("删除失败");
  }

//  @ApiOperation(value = "批量导出员工数据")
//  @GetMapping(value = "/export",produces = "application/octet-stream")
//  public void exportEmployee(HttpServletResponse response) {
//    List<Employee> employeeList = employeeService.getEmployee(null);
//    ExportParams params=new ExportParams("员工表","员工表", ExcelType.HSSF);
//    Workbook workbook= ExcelExportUtil.exportExcel(params,Employee.class,employeeList);
//    ServletOutputStream outputStream = null;
//    //流形式
//    try {
//      //流形式
//      response.setHeader("content-type", "application/octet-stream");
//      //防止中文乱码
//      response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
//      outputStream = response.getOutputStream();
//      workbook.write(outputStream);
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      if (outputStream != null) {
//        try {
//          outputStream.close();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }
//  }

}
