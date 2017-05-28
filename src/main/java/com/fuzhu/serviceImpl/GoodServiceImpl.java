package com.fuzhu.serviceImpl;

import com.fuzhu.dao.GoodDetailsDao;
import com.fuzhu.dao.LuceneDao;
import com.fuzhu.entity.GoodDetails;
import com.fuzhu.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDetailsDao goodDetailsDao;

    @Override
    public List<GoodDetails> findGoodByClassifyName(String ClassifyName) throws Exception {
        return null;
    }
    @Autowired
    private LuceneDao luceneDao;
    @Override
    public List<GoodDetails> findIndex(String keyword, int start, int row) {
//        LuceneDao luceneDao = new LuceneDao();
        System.out.print("luceneDao       "+luceneDao);
        List<GoodDetails> goodDetailsList;
        try {
            long start2 = System.nanoTime();
            goodDetailsList = luceneDao.findIndex(keyword, start, row);
            long time = System.nanoTime() - start2;
            System.out.println("测试索引耗时！！！！"+time);
            return goodDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GoodDetails findGoodAllDetailsById(String goodId) {
        GoodDetails goodDetails = goodDetailsDao.findGoodDetailsById(goodId);
        return goodDetails;
    }

    @Override
    public List<GoodDetails> getGoodList(String location) {
        List<GoodDetails> goodList= goodDetailsDao.findGoods(location);
        System.out.println(goodList.toString());
        return goodList;
    }

    @Override
    public List<Integer> methodOfWarn(List<GoodDetails> goodDetailsList) throws Exception {
        return null;
    }
}
