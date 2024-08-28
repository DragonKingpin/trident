package com.sauron.saurye.domain.dto;

import java.util.List;

public class AddPluginDTO {

    //插件名称
    private String pluginName;

    //插件选择的分类id
    private Long categoryId;

    //插件选择的功能列表
    private List<Long> featureIdList;

    public AddPluginDTO() {
    }

    public AddPluginDTO(String pluginName, Long categoryId, List<Long> featureIdList) {
        this.pluginName = pluginName;
        this.categoryId = categoryId;
        this.featureIdList = featureIdList;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getFeatureIdList() {
        return featureIdList;
    }

    public void setFeatureIdList(List<Long> featureIdList) {
        this.featureIdList = featureIdList;
    }

    @Override
    public String toString() {
        return "AddPluginDTO{" +
                "pluginName='" + pluginName + '\'' +
                ", categoryId=" + categoryId +
                ", featureIdList=" + featureIdList +
                '}';
    }
}
