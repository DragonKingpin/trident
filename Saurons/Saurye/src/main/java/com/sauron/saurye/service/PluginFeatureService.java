package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.PluginFeatureDTO;
import com.sauron.saurye.domain.pojo.PluginFeatureInfo;

import java.util.List;

public interface PluginFeatureService {

    /**
     * 获取所有插件功能
     * @return
     */
    List<PluginFeatureInfo> listPluginFeature();


    /**
     * 添加插件功能
     * @param pluginFeatureDTO
     */
    void addPluginFeature(PluginFeatureDTO pluginFeatureDTO);

    /**
     * 更新插件功能
     * @param pluginFeatureDTO
     */
    void updatePluginFeature(PluginFeatureDTO pluginFeatureDTO);

    /**
     * 删除插件功能
     * @param featureId
     */
    void deletePluginFeature(Long featureId);
}
