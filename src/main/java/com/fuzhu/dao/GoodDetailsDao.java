package com.fuzhu.dao;

import com.fuzhu.entity.GoodDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/4/8.
 */
public interface GoodDetailsDao {
    //根据商品种类id查询对应该类别的商品信息
    public List<GoodDetails> findGoodDetailsByClassifyId(int goodClassifyId);

    //根据商品id查询该商品的详细商品信息(包括该商品所属种类信息，商品图片信息)
    public GoodDetails findGoodAllDetailsByDetailId(String goodId);
    public GoodDetails findGoodDetailsById(String id);

    public List<GoodDetails> fingGoodsByFeatureIds(List<String> ids);

    public List<GoodDetails> findGoods(@Param("location") String location);
}
