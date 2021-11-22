package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.EmployeeMapper;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
  @Autowired
  private EmployeeMapper employeeMapper;
  /**
   * 获取最大工号
   * @return
   */
  @Override
  public RespBean maxWorkID() {
    List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
    //将查询到的最后一个workId + 1
    Integer maxWorkId =Integer.parseInt(maps.get(0).get("max(workID)").toString())+1;
    //此格式化目的是不让前面的零消失，比如00000101，如果不设置此格式化，输出结果就为101
    String formatMaxWorkId = String.format("%08d", maxWorkId);
    return RespBean.success("成功获取到到最大工号",formatMaxWorkId);
  }

  /**
   * 获取所有员工
   * @param currentPage
   * @param size
   * @param employee
   * @param beginDateScope
   * @return
   */
  @Override
  public RespPageBean getAllEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
    Page<Employee> employeePage = new Page<>(currentPage, size);
    IPage<Employee> employeeIPage = employeeMapper.getAllEmployee(employeePage, employee, beginDateScope);
    RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
    return respPageBean;
  }

  /**
   * 添加新员工
   * @param employee
   * @return
   */
  @Override
  public RespBean addEmployee(Employee employee) {
    //却是后台信息的校验
    //合同起始日期
    LocalDate beginContract = employee.getBeginContract();
    //合同结束日期
    LocalDate endContract = employee.getEndContract();
    long date = beginContract.until(endContract, ChronoUnit.DAYS);
    //DecimalFormat关于数字操作的类
    DecimalFormat decimalFormat = new DecimalFormat("##.00");
    employee.setContractTerm(Double.parseDouble(decimalFormat.format(date/365)));
    if (1==employeeMapper.insert(employee)){
      Employee emp=employeeMapper.getEmployeeByOne(employee.getId());
      return RespBean.success("添加新员工成功");
    }
    return RespBean.error("添加新员工失败");
  }

  @Override
  public RespBean updateEmployee(Employee employee) {
    //却是后台信息的校验
    //合同起始日期
    LocalDate beginContract = employee.getBeginContract();
    //合同结束日期
    LocalDate endContract = employee.getEndContract();
    long date = beginContract.until(endContract, ChronoUnit.DAYS);
    //DecimalFormat关于数字操作的类
    DecimalFormat decimalFormat = new DecimalFormat("##.00");
    employee.setContractTerm(Double.parseDouble(decimalFormat.format(date/365)));
    if (1== employeeMapper.updateById(employee)){
      return RespBean.success("更新员工成功");
    }
    return RespBean.error("更新员工失败");
  }

  /**
   * 获取所有员工工资账套
   * @param currentPage
   * @param size
   * @return
   */
  @Override
  public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
    // 开启分页
    Page<Employee> page = new Page<>(currentPage,size);
    IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
    RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
    return respPageBean;
  }

}
