package com.xxxx.server.controller;


import com.xxxx.server.pojo.Joblevel;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 * 职称管理
 *
 * @author xieshengji
 * @since 2021-11
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
  @Autowired
  private IJoblevelService joblevelService;

  @ApiOperation(value = "查询所有职称信息")
  @GetMapping("/")
  public List<Joblevel> getAllJoblevel() {
    List<Joblevel> list = joblevelService.list();
    return list;
  }

  @ApiOperation(value = "添加职称信息")
  @PostMapping("/")
  public RespBean addJoblevel(@RequestBody Joblevel joblevel) {
    boolean save = joblevelService.save(joblevel);
    if (save) {
      return RespBean.success("添加职称信息成功");
    }
    return RespBean.error("添加职称信息失败");
  }

//  @ApiOperation(value = "批量添加职称信息")
//  @PostMapping("/addJoblevelList")
//  public RespBean addJoblevelList(@RequestBody List<Joblevel> joblevel) {
//    boolean save = joblevelService.saveBatch(joblevel);
//    if (save) {
//      return RespBean.success("批量添加职称信息成功");
//    }
//    return RespBean.error("批量添加职称信息失败");
//  }

  @ApiOperation(value = "更新职称信息")
  @PutMapping("/")
  public RespBean updateJoblevel(@RequestBody Joblevel joblevel) {
    boolean update = joblevelService.updateById(joblevel);
    if (update) {
      return RespBean.success("更新职称信息成功");
    }
    return RespBean.error("更新职称信息失败");
  }

  @ApiOperation(value = "删除职称信息")
  @DeleteMapping("/{id}")
  public RespBean deleteJoblevel(@PathVariable Integer id) {
    boolean remove = joblevelService.removeById(id);
    if (remove) {
      return RespBean.success("删除职称信息成功");
    }
    return RespBean.error("删除职称信息失败");
  }

  @ApiOperation(value = "批量删除职称信息")
  @DeleteMapping("/")
  public RespBean deleteJoblevel(Integer[] id) {
    boolean remove = joblevelService.removeByIds(Arrays.asList(id));
    if (remove) {
      return RespBean.success("批量删除职称信息成功");
    }
    return RespBean.error("批量删除职称信息失败");
  }
}
