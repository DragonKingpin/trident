package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.dto.PluginCategoryDTO;
import com.sauron.saurye.domain.pojo.PluginCategory;
import com.sauron.saurye.domain.vo.PluginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PluginCategoryMapper {

    PluginCategory selectByCategoryId(Long categoryId);

    void addPluginCategory(PluginCategory pluginCategory);


    void updatePluginCategory(PluginCategory pluginCategory);

    List<PluginCategory> listPluginCategory();

    void deletePluginCategory(Long categoryId);
}
