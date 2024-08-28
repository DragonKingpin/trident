package com.sauron.saurye.service.impl;

import com.sauron.saurye.constant.MessageConstant;
import com.sauron.saurye.domain.dto.AddNotePluginDTO;
import com.sauron.saurye.domain.pojo.Plugin;
import com.sauron.saurye.exception.NotePluginRelationshipAlreadyExistsException;
import com.sauron.saurye.exception.NotePluginRelationshipNotFoundException;
import com.sauron.saurye.exception.PluginNotFoundException;
import com.sauron.saurye.mapper.NotePluginLinkMapper;
import com.sauron.saurye.mapper.NotePluginMapper;
import com.sauron.saurye.service.NotePluginLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotePluginLinkServiceImpl implements NotePluginLinkService {

    @Resource
    private NotePluginMapper notePluginMapper;

    @Resource
    private NotePluginLinkMapper notePluginLinkMapper;

    /**
     * 向笔记中增加插件
     * @param addNotePluginDTO
     */
    @Override
    public void addNotePlugin(AddNotePluginDTO addNotePluginDTO) {

        //从插件列表中寻找是否存在需要的插件
        Plugin plugin = notePluginMapper.selectByPluginName(addNotePluginDTO.getPluginName());
        if (plugin == null){
            throw new PluginNotFoundException(MessageConstant.PLUGIN_NOT_FOUND);
        }
        //从关联表中找是否笔记已经存在对应的插件
        Long notePluginLinkId = notePluginLinkMapper.selectByPluginId(plugin.getId());
        if (notePluginLinkId != null){
            throw new NotePluginRelationshipAlreadyExistsException(MessageConstant.NOTE_PLUGIN_RELATIONSHIP_ALREADY_EXISTS);
        }
        //根据笔记id插入笔记插件关联表
        notePluginLinkMapper.addNotePluginLink(addNotePluginDTO.getNoteId(), plugin.getId());
    }

    /**
     * 删除笔记对应的插件
     * @param PluginId
     */
    @Override
    public void deletedNotePlugin(Long PluginId) {

        //在插件表中判断是否有这个插件
        if (notePluginMapper.selectByPluginId(PluginId) == null){
            throw new PluginNotFoundException(MessageConstant.PLUGIN_NOT_FOUND);
        }

        //在关联表中判断是否有这个插件
        if (notePluginLinkMapper.selectByPluginId(PluginId) == null){
            throw new NotePluginRelationshipNotFoundException(MessageConstant.NOTE_PLUGIN_RELATIONSHIP_NOT_FOUND);
        }

        //根据id删除关联表中的插件
        notePluginLinkMapper.deleteNotePluginLinkByPluginId(PluginId);

    }

    /**
     * 获取笔记对应的插件
     * @param noteId
     * @return
     */
    @Override
    public List<Plugin> listAllPlugin(Long noteId) {

        return notePluginMapper.selectPluginsByNoteId(noteId);
    }


}
