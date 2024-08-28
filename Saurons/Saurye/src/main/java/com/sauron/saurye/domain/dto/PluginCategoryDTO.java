package com.sauron.saurye.domain.dto;

public class PluginCategoryDTO {

    // 分类id
    private Long id;

    // 分类名称
    private String categoryName;

    public PluginCategoryDTO() {
    }

    public PluginCategoryDTO(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "PluginCategoryDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
