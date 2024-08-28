package com.sauron.saurye.service.impl;

import com.mysql.cj.protocol.x.XpluginStatementCommand;
import com.sauron.saurye.constant.MessageConstant;
import com.sauron.saurye.domain.dto.AddPluginDTO;
import com.sauron.saurye.domain.dto.UpdatePluginDTO;
import com.sauron.saurye.domain.pojo.Plugin;
import com.sauron.saurye.domain.vo.PluginVO;
import com.sauron.saurye.exception.NotePluginNameAlreadyExistsException;
import com.sauron.saurye.exception.PluginNotFoundException;
import com.sauron.saurye.mapper.NotePluginLinkMapper;
import com.sauron.saurye.mapper.NotePluginMapper;
import com.sauron.saurye.mapper.PluginCategoryMapper;
import com.sauron.saurye.mapper.PluginFeatureLinkMapper;
import com.sauron.saurye.service.PluginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PluginServiceImpl implements PluginService {

    @Resource
    private NotePluginMapper notePluginMapper;

    @Resource
    private PluginFeatureLinkMapper pluginFeatureLinkMapper;

    @Resource
    private PluginCategoryMapper pluginCategoryMapper;

    @Resource
    private NotePluginLinkMapper notePluginLinkMapper;

    /**
     * 添加插件
     * @param addPluginDTO
     */
    @Override
    public void addPlugin(AddPluginDTO addPluginDTO) {

        //根据插件名字寻找,是否已经存在插件
        if (notePluginMapper.selectByPluginName(addPluginDTO.getPluginName()) != null ){
            throw new NotePluginNameAlreadyExistsException(MessageConstant.NOTE_PLUGIN_NAME_ALREADY_EXISTS);
        }

        Plugin plugin = new Plugin();

        BeanUtils.copyProperties(addPluginDTO, plugin);
        plugin.setCreateDateTime(LocalDateTime.now());
        //插入插件表
        notePluginMapper.insertNotePlugin(plugin);
        List<Long> featureIdList = addPluginDTO.getFeatureIdList();

        //插入插件和功能关系表
        for (Long featureId : featureIdList) {
            pluginFeatureLinkMapper.addPluginAndFeatureRelationship(plugin.getId(), featureId);
        }
    }

    /**
     * 更新插件
     *
     * @param updatePluginDTO
     */
    @Override
    public void updatePlugin(UpdatePluginDTO updatePluginDTO) {

        //查找是否存在这个插件
        Plugin plugin = notePluginMapper.selectByPluginId(updatePluginDTO.getId());
        if (plugin == null) {
            throw new PluginNotFoundException(MessageConstant.PLUGIN_NOT_FOUND);
        }

        BeanUtils.copyProperties(updatePluginDTO, plugin);
        //更新插件表
        notePluginMapper.updateNotePlugin(plugin);
        List<Long> featureIdList = updatePluginDTO.getFeatureIdList();

        //先清空原来关系
        pluginFeatureLinkMapper.deletePluginAndFeatureRelationshipByPluginId(plugin.getId());

        //插入插件和功能关系表
        for (Long featureId : featureIdList) {
            pluginFeatureLinkMapper.addPluginAndFeatureRelationship(plugin.getId(), featureId);
        }
    }

    /**
     * 获取所有插件
     * @return
     */
    @Override
    public List<PluginVO> listPlugin() {

        //获取所有插件
        List<Plugin> plugins = notePluginMapper.selectPlugins();

        //封装vo对象
        List<PluginVO> pluginVOS = plugins.stream().map(plugin -> {
            PluginVO pluginVO = new PluginVO();
            BeanUtils.copyProperties(plugin, pluginVO);

            //获取分类名称
            pluginVO.setCategoryName(pluginCategoryMapper.selectByCategoryId(plugin.getCategoryId()).getCategoryName());

            //获取插件对应的功能
            List<String> featureNameList = pluginFeatureLinkMapper.selectFeatureNameByPluginId(plugin.getId());
            pluginVO.setFeatureName(featureNameList);
            return pluginVO;
        }).collect(Collectors.toList());

        return pluginVOS;
    }

    /**
     * 删除指定插件
     * @param pluginId
     */
    @Override
    public void deletePlugin(Long pluginId) {

        //判断是否存在这个插件
        if (notePluginMapper.selectByPluginId(pluginId) == null){
            throw new PluginNotFoundException(MessageConstant.PLUGIN_NOT_FOUND);
        }

        //根据插件id删除功能插件关联表的数据
        pluginFeatureLinkMapper.deletePluginAndFeatureRelationshipByPluginId(pluginId);

        //根据插件id删除插件与笔记的关系
        notePluginLinkMapper.deleteNotePluginLinkByPluginId(pluginId);

        //根据插件id删除对应插件
        notePluginMapper.deleteNotePluginId(pluginId);
    }



}
