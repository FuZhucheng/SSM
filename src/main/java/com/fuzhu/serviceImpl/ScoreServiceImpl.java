package com.fuzhu.serviceImpl;

import com.fuzhu.dao.ScoreDao;
import com.fuzhu.entity.Score;
import com.fuzhu.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public int insertScore(Score score) {
        int t =scoreDao.insertScore(score);
        return t;
    }
}
