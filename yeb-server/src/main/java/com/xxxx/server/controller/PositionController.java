package com.xxxx.server.controller;


import com.xxxx.server.pojo.Position;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {
  @Autowired
  private IPositionService positionService;

  @ApiOperation(value = "获取所有职位信息")
  @GetMapping("/")
  public List<Position> getAllPositions() {
    List<Position> list = positionService.list();
    return list;
  }

  @ApiOperation(value = "添加职位信息")
  @PostMapping("/")
  public RespBean addPosition(@RequestBody Position position) {
    boolean save = positionService.save(position);
    if (save) {
      return RespBean.success("添加成功");
    }
    return RespBean.error("添加失败");
  }

  @ApiOperation(value = "更新职位新信息")
  @PutMapping("/")
  private RespBean updatePosition(@RequestBody Position position) {
    boolean update = positionService.updateById(position);
    if (update) {
      return RespBean.success("更新成功");
    }
    return RespBean.error("添加失败");
  }

  @ApiOperation(value = "删除职位信息")
  @DeleteMapping("/{id}")
  private RespBean deletePosition(@PathVariable Integer id) {
    boolean delete = positionService.removeById(id);
    if (delete) {
      return RespBean.success("删除成功");
    }
    return RespBean.error("删除失败");
  }

  @ApiOperation(value = "批量删除职位信息")
  @DeleteMapping("/")
  private RespBean deletePosition(Integer[] ids) {
    boolean delete = positionService.removeByIds(Arrays.asList(ids));
    if (delete) {
      return RespBean.success("批量删除成功");
    }
    return RespBean.error("批量删除失败");
  }
}
