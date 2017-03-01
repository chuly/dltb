package com.bbq.dltb.dal.dao;

import com.bbq.dltb.dal.model.AccessLog;
import java.util.List;

public interface AccessLogDAO {
    int deleteByPrimaryKey(Long id);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(Long id);

    List<AccessLog> selectByModelLike(AccessLog record);

    List<AccessLog> selectByModel(AccessLog record);

    int updateByPrimaryKeySelective(AccessLog record);

    int updateByPrimaryKey(AccessLog record);
}