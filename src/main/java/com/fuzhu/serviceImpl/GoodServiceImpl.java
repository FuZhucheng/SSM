package com.fuzhu.serviceImpl;

import com.fuzhu.dao.LuceneDao;
import com.fuzhu.entity.GoodDetails;
import com.fuzhu.service.GoodService;
import com.fuzhu.test.LuceneTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Override
    public List<GoodDetails> findGoodByClassifyName(String ClassifyName) throws Exception {
        return null;
    }

    @Override
    public List<GoodDetails> findIndex(String keyword, int start, int row) {
        LuceneDao luceneDao = new LuceneDao();
        LuceneTest luceneTest =new LuceneTest();
        List<GoodDetails> goodDetailsList;
        try {
            goodDetailsList = luceneDao.findIndex(keyword, start, row);
//            luceneTest.search();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GoodDetails findGoodAllDetailsById(String realGoodid) {
        return null;
    }

    @Override
    public List<GoodDetails> getGoodList(List<String> ids) {
        return null;
    }

    @Override
    public List<Integer> methodOfWarn(List<GoodDetails> goodDetailsList) throws Exception {
        return null;
    }
}
