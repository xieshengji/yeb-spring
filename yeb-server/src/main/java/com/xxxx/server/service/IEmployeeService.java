package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
public interface IEmployeeService extends IService<Employee> {
  /**
   * 获取所有员工
   * @param currentPage
   * @param size
   * @param employee
   * @param beginDateScope
   * @return
   */
  RespPageBean getAllEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);
  /**
   * 获取最大工号
   * @return
   */
  RespBean maxWorkID();

  /**
   * 添加新员工
   * @param employee
   * @return
   */
  RespBean addEmployee(Employee employee);

  /**
   * 更新员工
   * @param employee
   * @return
   */
  RespBean updateEmployee(Employee employee);
  /**
   * 查询员工
   * @return
   */

  /**
   * 获取所有员工工资账套
   * @param currentPage
   * @param size
   * @return
   */
  RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
