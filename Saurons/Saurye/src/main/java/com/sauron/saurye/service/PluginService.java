package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.AddPluginDTO;
import com.sauron.saurye.domain.dto.UpdatePluginDTO;
import com.sauron.saurye.domain.vo.PluginVO;

import java.util.List;

public interface PluginService {


    /**
     * 添加插件
     * @param addPluginDTO
     */
    void addPlugin(AddPluginDTO addPluginDTO);

    /**
     * 更新插件
     * @param updatePluginDTO
     */
    void updatePlugin(UpdatePluginDTO updatePluginDTO);

    /**
     * 获取插件列表
     * @return
     */
    List<PluginVO> listPlugin();

    /**
     * 删除指定插件
     * @param pluginId
     */
    void deletePlugin(Long pluginId);
}
