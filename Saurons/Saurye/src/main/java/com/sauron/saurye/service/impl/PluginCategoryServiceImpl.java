package com.sauron.saurye.service.impl;

import com.sauron.saurye.domain.dto.PluginCategoryDTO;
import com.sauron.saurye.domain.pojo.PluginCategory;
import com.sauron.saurye.mapper.NotePluginMapper;
import com.sauron.saurye.mapper.PluginCategoryMapper;
import com.sauron.saurye.service.PluginCategoryService;
import com.sauron.saurye.service.PluginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PluginCategoryServiceImpl implements PluginCategoryService {

    @Resource
    private PluginCategoryMapper pluginCategoryMapper;

    @Resource
    private NotePluginMapper notePluginMapper;

    /**
     * 添加插件分类
     * @param pluginCategoryDTO
     */
    @Override
    public void addPluginCategory(PluginCategoryDTO pluginCategoryDTO) {
        PluginCategory pluginCategory = new PluginCategory();
        BeanUtils.copyProperties(pluginCategoryDTO, pluginCategory);

        pluginCategory.setCreateDateTime(LocalDateTime.now());

        pluginCategoryMapper.addPluginCategory(pluginCategory);

    }

    /**
     * 更新插件
     * @param pluginCategoryDTO
     */
    @Override
    public void updatePluginCategory(PluginCategoryDTO pluginCategoryDTO) {
        //根据id更改插件分类
        PluginCategory pluginCategory = new PluginCategory();
        BeanUtils.copyProperties(pluginCategoryDTO, pluginCategory);

        pluginCategoryMapper.updatePluginCategory(pluginCategory);

    }

    /**
     * 获取插件分类列表
     * @return
     */
    @Override
    public List<PluginCategory> listPluginCategory() {
        return pluginCategoryMapper.listPluginCategory();
    }

    /**
     * 删除插件分类
     * @param categoryId
     */
    @Override
    public void deletePluginCategory(Long categoryId) {

        //对有关的插件实行逻辑删除
        notePluginMapper.updatePluginCategoryUncategorizedByCategoryId(categoryId);

        //删除插件分类表中的数据
        pluginCategoryMapper.deletePluginCategory(categoryId);
    }
}
