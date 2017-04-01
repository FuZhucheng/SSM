package com.fuzhu.serviceImpl;

import com.fuzhu.dao.GagDao;
import com.fuzhu.dao.ScoreDao;
import com.fuzhu.entity.Gag;
import com.fuzhu.service.GagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/1.
 */
@Service
public class GagServiceImpl implements GagService {
    @Autowired
    private GagDao gagDao;
    @Override
    public int insertGag(Gag gag) {
        int t = gagDao.insertGag(gag);
        return t;
    }

    @Override
    public List<Gag> findByUserId(Long id) {
        List<Gag> gagList=gagDao.findByUserId(id);
        return gagList;
    }
}
