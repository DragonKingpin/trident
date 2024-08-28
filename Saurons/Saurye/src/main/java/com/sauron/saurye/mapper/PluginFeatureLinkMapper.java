package com.sauron.saurye.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PluginFeatureLinkMapper {



    void addPluginAndFeatureRelationship(Long pluginId, Long featureId);

    void deletePluginAndFeatureRelationshipByPluginId(@Param("pluginId") Long pluginId);

    List<String> selectFeatureNameByPluginId(Long pluginId);

    void deletePluginAndFeatureRelationshipByFeatureId(Long featureId);

}
