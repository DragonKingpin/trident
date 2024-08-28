package com.sauron.saurye.domain.dto;

public class PluginFeatureDTO {

    //主键id
    private Long id;

    //功能名字
    private String featureName;

    //功能描述
    private String description;

    public PluginFeatureDTO() {
    }

    public PluginFeatureDTO(Long id, String featureName, String description) {
        this.id = id;
        this.featureName = featureName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PluginFeatureDTO{" +
                "id=" + id +
                ", featureName='" + featureName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
