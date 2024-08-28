package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.pojo.PluginFeatureInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PluginFeatureInfoMapper {
    List<PluginFeatureInfo> selectAllFeature();

    void insertPluginFeature(PluginFeatureInfo pluginFeatureInfo);

    void updatePluginFeature(PluginFeatureInfo pluginFeatureInfo);


    PluginFeatureInfo selectByFeatureName(String featureName);

    PluginFeatureInfo selectByFeatureId(Long featureId);

    void deletePluginFeature(Long featureId);
}
