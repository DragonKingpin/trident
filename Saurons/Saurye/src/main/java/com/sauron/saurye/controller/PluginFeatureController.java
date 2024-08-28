package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.PluginFeatureDTO;
import com.sauron.saurye.domain.pojo.PluginFeatureInfo;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.PluginFeatureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 插件功能接口
 * @author azlml
 * @date 2024/8/20
 */
@RestController
@RequestMapping("/pluginFeature")
public class PluginFeatureController {

    @Resource
    private PluginFeatureService pluginFeatureService;


    /**
     * 展示所有的插件功能
     *
     * @return
     */
    @GetMapping("/list")
    public BasicServiceResponse<List<PluginFeatureInfo>> listPluginFeature() {
        return BasicServiceResponse.success(pluginFeatureService.listPluginFeature());
    }

    /**
     * 添加插件功能
     *
     * @param pluginFeatureDTO
     * @return
     */
    @PostMapping("/add")
    public BasicServiceResponse<?> addPluginFeature(@RequestBody PluginFeatureDTO pluginFeatureDTO) {

        pluginFeatureService.addPluginFeature(pluginFeatureDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 更新插件功能
     *
     * @param pluginFeatureDTO
     * @return
     */
    @PostMapping("/update")
    public BasicServiceResponse<?> updatePluginFeature(@RequestBody PluginFeatureDTO pluginFeatureDTO) {

        pluginFeatureService.updatePluginFeature(pluginFeatureDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 根据指定的id删除对应的功能
     *
     * @param FeatureId
     * @return
     */
    @DeleteMapping("/delete/{FeatureId}")
    public BasicServiceResponse<?> deletePluginFeature(@PathVariable Long FeatureId) {

        pluginFeatureService.deletePluginFeature(FeatureId);

        return BasicServiceResponse.success();
    }
}
