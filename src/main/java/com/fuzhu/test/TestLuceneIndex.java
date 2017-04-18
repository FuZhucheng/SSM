package com.fuzhu.test;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ${符柱成} on 2017/4/1.
 */
public class TestLuceneIndex {
    //会重复创建索引的。是一种增量索引。
    @Test
    public void testIndex(){
        LuceneTest luceneTest = new LuceneTest();
        luceneTest.index();
    }
    /*
    重新索引，执行testIndex，会增加一次的索引文件。也就是有两次索引到栈（Java）。
        栈链式结构DEMO（C）.txt[F:\lucene\example\栈链式结构DEMO（C）.txt]
        栈结构DEMO（Java）.txt[F:\lucene\example\栈结构DEMO（Java）.txt]
        栈结构DEMO（Java）.txt[F:\lucene\example\栈结构DEMO（Java）.txt]
     */
    @Test
    public void testSearch(){
        LuceneTest luceneTest = new LuceneTest();
        luceneTest.search();
    }
    @Test
    public void testQuery() throws IOException, ParseException {
        LuceneTest luceneTest = new LuceneTest();
        luceneTest.query();
    }
}
