package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiuxuechen
 * @since 2021-08-07
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
  /**
   * 获取所有员工
   * @param employeePage
   * @param employee
   * @param beginDateScope
   * @return
   */
  IPage<Employee> getAllEmployee(@Param("employeePage") Page<Employee> employeePage, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

  /**
   * 获取list的员工
   * @param ids
   * @return
   */
  List<Employee> getEmployee(@Param("ids") Integer ids);

  /**
   * 根据id获取员工信息（包括部门职位）
   * @param id
   * @return
   */
  Employee getEmployeeByOne(@Param("id") Integer id);

  /**
   * 获取有所员工工资账套（分页）
   * @param page
   * @return
   */
  IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
