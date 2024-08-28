package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.pojo.Plugin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotePluginMapper {


    List<Plugin> selectByNoteId(Long noteId);


    void insertNotePlugin(Plugin plugin);

    Plugin selectByPluginName(String pluginName);

    List<Plugin> selectPluginsByNoteId(Long noteId);

    Plugin selectByPluginId(Long id);

    void updateNotePlugin(Plugin plugin);

    List<Plugin> selectPlugins();

    void deleteNotePluginId(Long pluginId);

    void updatePluginCategoryUncategorizedByCategoryId(Long categoryId);
}
