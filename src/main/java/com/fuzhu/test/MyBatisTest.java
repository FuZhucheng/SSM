package com.fuzhu.test;

import com.fuzhu.dao.GagDao;
import com.fuzhu.dao.ScoreDao;
import com.fuzhu.entity.Gag;
import com.fuzhu.entity.Score;
import com.fuzhu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Date;


/**
 * Created by ${符柱成} on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用Springtest测试框架
@ContextConfiguration("/spring/spring-*.xml") // 加载配置
public class MyBatisTest {
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private GagDao gagDao;
    private ShardedJedisPool shardedJedisPool;
    private ShardedJedis jedis;

    @Test
    public void testAddScore() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setAccount(String.valueOf(89564));
        Score score = new Score();
        score.setChangeType("充钱钱");
        score.setScore(10);
        score.setUser(user);
        int insert = scoreDao.insertScore(score);
        System.out.print("insert :" + insert);
    }

    @Test
    public void testRedisData() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setAccount(String.valueOf(89564));
        Gag gag = new Gag();
        gag.setGagTime(new Date());
        gag.setUser(user);
        int insertSuccess = gagDao.insertGag(gag);
        System.out.print("insert :" + insertSuccess);
    }
    @Test
    public void testRedisRank(){
    }
}
