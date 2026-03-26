package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

/**
 * Author:  blithe.xwj
 * Date:    2026/3/26 19:23
 * Description:
 */

public interface CategoryService {
    /**
     * 分页查询菜品或套餐分类
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增菜品或套餐分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 更新菜品或套餐分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用或禁用菜品或套餐分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
