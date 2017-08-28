package com.fuzhu.service;

import com.fuzhu.entity.GoodDetails;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
public interface GoodService {
    public List<GoodDetails> findGoodByClassifyName(String ClassifyName) throws Exception;

    public List<GoodDetails> findIndex(String keyword, int start, int row);

    public GoodDetails findGoodAllDetailsById(String realGoodid);


    public List<GoodDetails> getGoodList(String location);

    public List<Integer> methodOfWarn(List<GoodDetails> goodDetailsList) throws Exception;

}
