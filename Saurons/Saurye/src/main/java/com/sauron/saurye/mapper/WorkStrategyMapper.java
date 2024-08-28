package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.pojo.WorkStrategy;
import com.sauron.saurye.domain.vo.WorkArchiveVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkStrategyMapper {


    WorkStrategy selectByStrategyId(Long strategyId);
}
