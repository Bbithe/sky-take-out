package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Author:  blithe.xwj
 * Date:    2026/3/26 19:23
 * Description:
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;


	/**
	 * 分页查询菜品或套餐分类
	 * @param categoryPageQueryDTO
	 * @return
	 */
	@Override
	public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
		PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
		Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 新增菜品或套餐分类
	 * @param categoryDTO
	 */
	@Override
	public void save(CategoryDTO categoryDTO) {
		// 将CategoryDTO 的同名属性拷贝到 Category 对象中
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);

		// 设置状态为默认禁用，因为新增的菜品分类需要管理员审核后才能启用
		category.setStatus(StatusConstant.DISABLE);

		// 设置创建时间和更新时间
		category.setCreateTime(LocalDateTime.now());
		category.setUpdateTime(LocalDateTime.now());

		// 设置创建人和更新人
		category.setCreateUser(BaseContext.getCurrentId());
		category.setUpdateUser(BaseContext.getCurrentId());

		// 将 Category 对象保存到数据库中
		categoryMapper.save(category);
	}

	/**
	 * 更新菜品或套餐分类
	 * @param categoryDTO
	 */
	@Override
	public void update(CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		category.setUpdateUser(BaseContext.getCurrentId());
		category.setUpdateTime(LocalDateTime.now());
		categoryMapper.update(category);
	}

	public void startOrStop(Integer status, Long id){
		categoryMapper.update(Category.builder().status(status).id(id).build());
	}
}
