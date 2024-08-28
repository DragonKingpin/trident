package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.PluginCategoryDTO;
import com.sauron.saurye.domain.pojo.PluginCategory;

import java.util.List;

public interface PluginCategoryService {

    /**
     * 添加插件分类
     * @param pluginCategoryDTO
     */
    void addPluginCategory(PluginCategoryDTO pluginCategoryDTO);

    /**
     * 更新插件分类
     * @param pluginCategoryDTO
     */
    void updatePluginCategory(PluginCategoryDTO pluginCategoryDTO);

    /**
     * 获取插件分类列表
     * @return
     */
    List<PluginCategory> listPluginCategory();

    /**
     * 删除插件分类
     * @param categoryId
     */
    void deletePluginCategory(Long categoryId);
}
