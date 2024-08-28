package com.sauron.saurye.domain.dto;

import java.util.List;

public class UpdatePluginDTO {

    //插件id
    private Long id;

    //插件名称
    private String pluginName;

    //插件选择的分类id
    private Long categoryId;

    //插件选择的功能列表
    private List<Long> featureIdList;

    public UpdatePluginDTO() {
    }

    public UpdatePluginDTO(Long id, String pluginName, Long categoryId, List<Long> featureIdList) {
        this.id = id;
        this.pluginName = pluginName;
        this.categoryId = categoryId;
        this.featureIdList = featureIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "UpdatePluginDTO{" +
                "id=" + id +
                ", pluginName='" + pluginName + '\'' +
                ", categoryId=" + categoryId +
                ", featureIdList=" + featureIdList +
                '}';
    }
}
