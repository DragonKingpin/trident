package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.AddPluginDTO;
import com.sauron.saurye.domain.dto.UpdatePluginDTO;
import com.sauron.saurye.domain.vo.PluginVO;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.PluginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 插件相关api
 * @author azlml
 * @date 2024/8/19
 */
@RestController
@RequestMapping("/plugin")
public class PluginController {

    @Resource
    private PluginService pluginService;

    /**
     * 获取插件列表
     * @return
     */
    @GetMapping("/list")
    public BasicServiceResponse<List<PluginVO>> listPlugin() {

        return BasicServiceResponse.success(pluginService.listPlugin());
    }


    /**
     * 创建插件
     * @param addPluginDTO
     * @return
     */
    @PostMapping("/add")
    public BasicServiceResponse<?> addPlugin(@RequestBody AddPluginDTO addPluginDTO) {

        pluginService.addPlugin(addPluginDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 更新插件
     * @param updatePluginDTO
     * @return
     */
    @PostMapping("/update")
    public BasicServiceResponse<?> updatePlugin(@RequestBody UpdatePluginDTO updatePluginDTO) {

        pluginService.updatePlugin(updatePluginDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 删除指定插件
     * @param PluginId
     * @return
     */
    @DeleteMapping("/delete/{PluginId}")
    public BasicServiceResponse<?> deletePlugin(@PathVariable Long PluginId) {

        pluginService.deletePlugin(PluginId);

        return BasicServiceResponse.success();
    }

}
