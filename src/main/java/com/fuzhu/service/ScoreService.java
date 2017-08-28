package com.fuzhu.service;

import com.fuzhu.entity.Score;
import com.fuzhu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public interface ScoreService {
    //插入积分记录
    public int insertScore(Score score);
    //查询全部积分记录.
    List<Score> queryAll();
    //修改用户积分
    void updateScore(User user, int scoreCount);
    /**
     * 限定查询用户积分记录，比如查询前10条
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return
     */
    List<Score> queryLimit(int offset, int limit);
}
