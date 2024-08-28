package com.sauron.saurye.service.impl;

import com.sauron.saurye.constant.MessageConstant;
import com.sauron.saurye.context.UserContext;
import com.sauron.saurye.domain.dto.PluginFeatureDTO;
import com.sauron.saurye.domain.pojo.PluginFeatureInfo;
import com.sauron.saurye.exception.PluginFeatureNameAlreadyExistsException;
import com.sauron.saurye.exception.PluginFeatureNotFoundException;
import com.sauron.saurye.exception.PluginNotFoundException;
import com.sauron.saurye.mapper.PluginFeatureInfoMapper;
import com.sauron.saurye.mapper.PluginFeatureLinkMapper;
import com.sauron.saurye.mapper.PluginFeatureMapper;
import com.sauron.saurye.service.PluginFeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PluginFeatureServiceImpl implements PluginFeatureService {

    @Resource
    private PluginFeatureInfoMapper pluginFeatureInfoMapper;

    @Resource
    private PluginFeatureMapper pluginFeatureMapper;

    @Resource
    private PluginFeatureLinkMapper pluginFeatureLinkMapper;

    /**
     * 展示所有插件功能
     * @return
     */
    @Override
    public List<PluginFeatureInfo> listPluginFeature() {
        return pluginFeatureInfoMapper.selectAllFeature();
    }

    /**
     * 添加插件功能
     * @param pluginFeatureDTO
     */
    @Override
    public void addPluginFeature(PluginFeatureDTO pluginFeatureDTO) {

        //前提名字不能重复
        if (pluginFeatureInfoMapper.selectByFeatureName(pluginFeatureDTO.getFeatureName()) != null){
            throw new PluginFeatureNameAlreadyExistsException(MessageConstant.PLUGIN_FEATURE_NAME_ALREADY_EXISTS);
        }

        PluginFeatureInfo pluginFeatureInfo = new PluginFeatureInfo();

        BeanUtils.copyProperties(pluginFeatureDTO, pluginFeatureInfo);

        pluginFeatureInfo.setUserId(UserContext.getCurrentId());
        pluginFeatureInfo.setCreateDateTime(LocalDateTime.now());

        pluginFeatureInfoMapper.insertPluginFeature(pluginFeatureInfo);

        pluginFeatureMapper.insertPluginFeature(pluginFeatureInfo.getId());
    }

    /**
     * 更新插件功能
     * @param pluginFeatureDTO
     */
    @Override
    public void updatePluginFeature(PluginFeatureDTO pluginFeatureDTO) {

        //根据id查找对应的插件，如果没有直接退出
        if (pluginFeatureInfoMapper.selectByFeatureId(pluginFeatureDTO.getId()) == null){
            throw new PluginFeatureNotFoundException(MessageConstant.PLUGIN_FEATURE_NOT_FOUND);
        }

        PluginFeatureInfo pluginFeatureInfo = new PluginFeatureInfo();

        BeanUtils.copyProperties(pluginFeatureDTO, pluginFeatureInfo);

        pluginFeatureInfoMapper.updatePluginFeature(pluginFeatureInfo);
    }


    /**
     * 删除插件功能
     * @param featureId
     */
    @Override
    public void deletePluginFeature(Long featureId) {

        //先判断是否存在
        if (pluginFeatureInfoMapper.selectByFeatureId(featureId) == null){
            throw new PluginFeatureNotFoundException(MessageConstant.PLUGIN_FEATURE_NOT_FOUND);
        }

        //删除插件与功能关系列表对应数据
        pluginFeatureLinkMapper.deletePluginAndFeatureRelationshipByFeatureId(featureId);

        //删除关联功能表
        pluginFeatureMapper.deletePluginFeatureByInfoId(featureId);

        //删除信息表
        pluginFeatureInfoMapper.deletePluginFeature(featureId);

    }


}
