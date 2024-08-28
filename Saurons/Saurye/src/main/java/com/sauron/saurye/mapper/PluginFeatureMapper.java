package com.sauron.saurye.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PluginFeatureMapper {

    void insertPluginFeature(Long id);


    void deletePluginFeatureByInfoId(Long featureId);
}
