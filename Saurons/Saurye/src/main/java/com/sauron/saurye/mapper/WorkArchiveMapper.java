package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.pojo.WorkArchive;
import com.sauron.saurye.domain.pojo.WorkNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkArchiveMapper {


    List<WorkArchive> getAllWorkArchive(long guid);

    void addWorkArchive(WorkArchive workArchive);

    void updateWorkArchive(WorkArchive workArchive);

    void deleteWorkArchiveById(Long workArchiveId);

    @Select("select * from trident_work_archive where id = #{workArchiveId}")
    WorkArchive selectByWorkArchiveId(Long workArchiveId);

    WorkArchive selectByWorkArchiveName(String archiveName);
}
