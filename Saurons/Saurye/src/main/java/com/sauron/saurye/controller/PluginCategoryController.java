package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.PluginCategoryDTO;
import com.sauron.saurye.domain.pojo.PluginCategory;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.PluginCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 插件分类接口
 * @author azlml
 * @date 2024/8/21
 */
@RestController
@RequestMapping("/pluginCategory")
public class PluginCategoryController {

    @Resource
    private PluginCategoryService pluginCategoryService;

    /**
     * 获取插件分类列表
     * @return
     */
    @GetMapping("/list")
    public BasicServiceResponse<List<PluginCategory>> listPluginCategory() {

        return BasicServiceResponse.success(pluginCategoryService.listPluginCategory());
    }

    /**
     * 添加插件分类
     * @param pluginCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public BasicServiceResponse<?> addPluginCategory(@RequestBody PluginCategoryDTO pluginCategoryDTO) {

        pluginCategoryService.addPluginCategory(pluginCategoryDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 更新插件分类
     * @param pluginCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public BasicServiceResponse<?> updatePluginCategory(@RequestBody PluginCategoryDTO pluginCategoryDTO) {

        pluginCategoryService.updatePluginCategory(pluginCategoryDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 删除插件分类
     * @param categoryId
     * @return
     */
    @DeleteMapping("/delete/{categoryId}")
    public BasicServiceResponse<?> deletePluginCategory(@PathVariable Long categoryId) {

        pluginCategoryService.deletePluginCategory(categoryId);

        return BasicServiceResponse.success();
    }
}
