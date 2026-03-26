package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * Author:  blithe.xwj
 * Date:    2026/3/26 19:22
 * Description:
 */

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "菜品分类相关接口")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 分页查询菜品或套餐分类
	 * @param categoryPageQueryDTO
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询菜品分类")
	public Result<PageResult> page( CategoryPageQueryDTO categoryPageQueryDTO){
		log.info("分页查询菜品分类：{}", categoryPageQueryDTO);
		PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
		return Result.success(pageResult);
	}

	/**
	 * 新增菜品或套餐分类
	 * @param categoryDTO
	 * @return
	 */
	@PostMapping()
	@ApiOperation("新增菜品或套餐分类")
	public Result<T> save(@RequestBody CategoryDTO categoryDTO){
		log.info("新增菜品分类：{}", categoryDTO);
		categoryService.save(categoryDTO);
		return Result.success();
	}

	@PutMapping
	@ApiOperation("修改菜品或套餐分类")
	public Result<T> update(@RequestBody CategoryDTO categoryDTO){
		log.info("修改菜品分类：{}", categoryDTO);
		categoryService.update(categoryDTO);
		return Result.success();
	}


	@PostMapping("/status/{status}")
	@ApiOperation("启用或禁用菜品分类")
	public Result<T> startOrStop(@PathVariable Integer status, Long id){
		log.info("启用或禁用菜品分类：status={}, id={}", status, id);
		categoryService.startOrStop(status, id);
		return Result.success();
	}
}
