package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.pojo.WorkNote;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkNoteMapper {


    void insertWorkNote(WorkNote workNote);


    List<WorkNote> selectAllWorkNote();

    @Delete("delete from trident_work_note where id = #{workNoteId}")
    void deleteWorkNoteById(Long workNoteId);

    void updateWorkNote(WorkNote workNote);

    List<WorkNote> selectNotesByArchiveId(Long workArchiveId);

    void deleteWorkNoteByWorkArchiveId(Long workArchiveId);
}
