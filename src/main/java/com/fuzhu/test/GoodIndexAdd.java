package com.fuzhu.test;

import com.fuzhu.dao.GoodClassifyDao;
import com.fuzhu.dao.LuceneDao;
import com.fuzhu.entity.GoodDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用Springtest测试框架
@ContextConfiguration("/spring/spring-*.xml") // 加载配置
public class GoodIndexAdd {
    private LuceneDao luceneDao = new LuceneDao();
    @Autowired
    private GoodClassifyDao goodClassifyDao;
//    @Test
//    public void addIndex() throws IOException {
//
//        List<GoodDetails> list = goodClassifyDao.findGoodDetailsByClassifyName("床");
//        System.out.println("junitTest:list.size()="+list.size());
//        for (int index = 0; index < list.size(); index++) {
//            luceneDao.addIndex(list.get(index));
//        }
//    }
    @Test
    public void addIndexForAll() throws IOException {
        /**
         * 8-62：商品种类ID的起始Commodity_classification
         * 建立你的商品种类索引，原因我只伪造了两个商品种类假数据，就是id=15和16的商品，所以我们只建立对他的索引咯
         * */
        for(int i = 15; i <= 16; i++){
            System.out.println("goodClassifyDao     "+goodClassifyDao);
            List<GoodDetails> list = goodClassifyDao.findGoodDetailsByClassifyID(i);
            System.out.println("junitTest:list.size()="+list.size());
            for (int index = 0; index < list.size(); index++) {
                luceneDao.addIndex(list.get(index));
                System.out.println(list.get(index).toString());
            }
        }
    }
}
