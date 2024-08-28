package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.AddNotePluginDTO;
import com.sauron.saurye.domain.pojo.Plugin;

import java.util.List;

public interface NotePluginLinkService {

    /**
     * 增加插件
     * @param addNotePluginDTO
     */
    void addNotePlugin(AddNotePluginDTO addNotePluginDTO);

    /**
     * 删除笔记对应的插件
     * @param PluginId
     */
    void deletedNotePlugin(Long PluginId);

    /**
     * 展示所有笔记相关的插件
     * @param noteId
     * @return
     */
    List<Plugin> listAllPlugin(Long noteId);
}
