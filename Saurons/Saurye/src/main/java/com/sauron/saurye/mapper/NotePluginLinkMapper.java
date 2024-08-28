package com.sauron.saurye.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotePluginLinkMapper {


    void addNotePluginLink(Long noteId, Long pluginId);

    void deleteNotePluginLinkByPluginId(Long PluginId);

    Long selectByPluginId(Long id);

    void deleteNotePluginLinkByNoteId(Long workNoteId);
}
