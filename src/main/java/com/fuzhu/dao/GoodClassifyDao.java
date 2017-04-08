package com.fuzhu.dao;

import com.fuzhu.entity.GoodDetails;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
public interface GoodClassifyDao {
    //根据商品种类名查询其所属商品类别id
    public int findClassifyIdByClassifyName(String goodClassifyName);

    //根据商品种类名查询所属类别的商品信息
    public List<GoodDetails> findGoodDetailsByClassifyName(String goodClassifyName);

    //根据商品种类ID查询所属类别的商品信息(目前用于建立索引)
    public List<GoodDetails> findGoodDetailsByClassifyID(int goodClassifyID);
}
