package com.fuzhu.dao;

import com.fuzhu.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public interface ScoreDao {
    //插入积分记录
    int insertScore(Score score);
    //查询全部积分记录.
    List<Score> queryAll();
    /**
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return
     */
    List<Score> queryLimit(@Param("offset") int offset, @Param("limit") int limit);
}
