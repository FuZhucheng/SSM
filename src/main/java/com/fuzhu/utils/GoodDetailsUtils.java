package com.fuzhu.utils;

import com.fuzhu.entity.GoodDetails;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
/*
 * GoodInfo的转换类
 * */
public class GoodDetailsUtils {

    /*
     * 将GoodDetails 转换成document 将GoodDetails 的值设置到document里面去...
     */
    public static Document GoodDetailsToDocument(GoodDetails goodDetails) {

        Document document = new Document();

        StringField idfield = new StringField("id", goodDetails.getGoodId(), Store.YES);

        TextField goodNamefield = new TextField("goodName", goodDetails.getGoodName(),Store.YES);
        document.add(idfield);
        document.add(goodNamefield);

        return document;
    }
}
