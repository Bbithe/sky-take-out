package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author:  blithe.xwj
 * Date:    2026/3/26 19:23
 * Description:
 */

@Mapper
public interface CategoryMapper {

	/**
	 * 分页查询菜品套餐分类
	 * @param categoryPageQueryDTO
	 * @return
	 */
	Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

	/**
     * 新增菜品套餐分类
     * @param category
     */
	@Insert("insert into category(name, type, status, create_time, update_time, create_user, update_user, sort)" +
            "values (#{name}, #{type}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{sort})")
	void save(Category category);

	/**
	 * 根据主键动态修改属性
	 * 因为禁用和启用菜品分类也是修改状态，所以update方法既可以修改菜品分类的名称、类型、排序等属性，也可以修改菜品分类的状态
	 * @param category
	 */
	void update(Category category);
}
