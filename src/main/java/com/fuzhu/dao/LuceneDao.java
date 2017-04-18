package com.fuzhu.dao;

import com.fuzhu.entity.GoodDetails;
import com.fuzhu.test.LuceneUtils;
import com.fuzhu.utils.Constant;
import com.fuzhu.utils.GoodDetailsUtils;
import com.fuzhu.utils.SynonymAnalyzerUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
/*
 * 使用Lucene来操作索引库
 * */
public class LuceneDao {

	/*
	 * 增删改索引都是通过indexWriter对象完成
	 *
	 * */


    /*
     * 建立索引
     * */
    public void addIndex(GoodDetails goodDetails) throws IOException {

        IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP();
        Document doc = GoodDetailsUtils.GoodDetailsToDocument(goodDetails);
        indexWriter.addDocument(doc);
//        		indexWriter.forceMerge(10);//合并cfs文件。比如设定1，就是自动合并成一个索引cfs文件
        indexWriter.close();
    }


    /*
     * 删除索引，根据字段对应的值进行删除
     * */
    public void delIndex(String fieldName,String fieldValue) throws IOException {

        IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP();
        //term!!!
        Term term = new Term(fieldName,fieldValue);
        //根据字段对应的值删除索引
        indexWriter.deleteDocuments(term);

        indexWriter.close();
    }

    /*
     * 先删除符合条件的记录，再创建一个符合条件的记录
     * */
    public void updateIndex(String fieldName,String fieldValue,GoodDetails goodDetails) throws IOException {

        IndexWriter indexWriter = LuceneUtils.getIndexWriterOfSP();

        Term term = new Term(fieldName,fieldValue);

        Document document = GoodDetailsUtils.GoodDetailsToDocument(goodDetails);

		/*
		 * 1.设置更新的条件
		 * 2.设置更新的内容的对象
		 * */
        indexWriter.updateDocument(term, document);

        indexWriter.close();
    }

    /*
	 * 分页：每页10条
	 * */
    public List<GoodDetails> findIndex(String keywords, int start, int rows) throws Exception {

        Directory directory = FSDirectory.open(new File(Constant.INDEXURL_ALL));//索引创建在硬盘上。
        IndexSearcher indexSearcher =  LuceneUtils.getIndexSearcherOfSP();

        /**同义词处理*/
        String result = SynonymAnalyzerUtil.displayTokens(SynonymAnalyzerUtil.convertSynonym(SynonymAnalyzerUtil.analyzeChinese(keywords, true)));
//        Analyzer analyzer4 = new IKAnalyzer(false);// 普通简陋语意分词
//        String result = keywords;
        //需要根据哪几个字段进行检索...
        String fields[] = {"goodName"};

        //查询分析程序（查询解析）
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(), fields, LuceneUtils.getAnalyzer());

        //不同的规则构造不同的子类...
        //title:keywords content:keywords
        Query query = queryParser.parse(result);

        //这里检索的是索引目录,会把整个索引目录都读取一遍
        //根据query查询，返回前N条
        TopDocs topDocs = indexSearcher.search(query, start+rows);

        System.out.println("总记录数="+topDocs.totalHits);

        ScoreDoc scoreDoc[] = topDocs.scoreDocs;

        /**添加设置文字高亮begin*/
        //htmly页面高亮显示的格式化，默认是<b></b>即加粗
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);

        //设置文字摘要（高亮的部分），此时摘要大小为10
        //int fragmentSize = 10;
        Fragmenter fragmenter = new SimpleFragmenter();
        highlighter.setTextFragmenter(fragmenter);

        /**添加设置文字高亮end*/
        List<GoodDetails> goodDetailslist = new ArrayList<GoodDetails>();
        //防止数组溢出
        int endResult = Math.min(scoreDoc.length, start+rows);
        GoodDetails goodDetails = null;

        for(int i = start;i < endResult ;i++ ){
            goodDetails = new GoodDetails();
            //docID lucene的索引库里面有很多的document，lucene为每个document定义了一个编号，唯一标识，自增长
            int docID = scoreDoc[i].doc;
            System.out.println("标识docID="+docID);
            Document document = indexSearcher.doc(docID);
            /**获取文字高亮的信息begin*/
            System.out.println("==========================");
            TokenStream tokenStream = LuceneUtils.getAnalyzer().tokenStream("goodName", new StringReader(document.get("goodName")));
            String goodName = highlighter.getBestFragment(tokenStream, document.get("goodName"));
            System.out.println("goodName="+goodName);
            System.out.println("==========================");
            /**获取文字高亮的信息end*/

            //备注：document.get("id")的返回值是String
            goodDetails.setGoodId((document.get("id")));
            goodDetails.setGoodName(goodName);
            goodDetailslist.add(goodDetails);
        }
        return goodDetailslist;
    }

}
