package com.fuzhu.service;

import com.fuzhu.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public interface JedisClient {

    public String get(String key);
    public String set(String key, String value);
    public String hget(String hkey, String key);
    public long hset(String hkey, String key, String value);
    public long incr(String key);
    public long expire(String key, int second);
    public long ttl(String key);
    public long del(String key);
    public long hdel(String hkey, String key);
    public long zadd(String key, double score, User user);
    public Set<String> zgetAll(String key, long start, long end);
    //拿整个榜单
    public long zaddList(String key, List<User> userList);
    //拿去榜单最后一名，比如前50名，这里就拿到第50名的User。积分变化时就拿这个值去判断，大于就丢user进redis，小于则不管
    public Set<String> getTopLast(String key, long start, long end);
}
