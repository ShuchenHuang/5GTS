package com.wta.crud.dao;

import com.wta.crud.bean.RealFrame;
import com.wta.crud.bean.RealFrameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RealFrameMapper {
    long countByExample(RealFrameExample example);

    int deleteByExample(RealFrameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RealFrame record);

    int insertSelective(RealFrame record);
    
    int selectDataCount(String columnName);

    List<RealFrame> selectByExample(RealFrameExample example);

    RealFrame selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RealFrame record, @Param("example") RealFrameExample example);

    int updateByExample(@Param("record") RealFrame record, @Param("example") RealFrameExample example);

    int updateByPrimaryKeySelective(RealFrame record);

    int updateByPrimaryKey(RealFrame record);
}