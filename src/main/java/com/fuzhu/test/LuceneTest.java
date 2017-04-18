package com.fuzhu.test;

import com.alibaba.druid.VERSION;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ${符柱成} on 2017/4/1.
 */
public class LuceneTest {
    private static Version matchVersion = Version.LUCENE_44;
    private static Analyzer analyzer = new IKAnalyzer();

    /*
        建立索引
     */
    public void index() {
        IndexWriter writer = null;
        try {
            //1.创建Directory（是建立在内存中还是硬盘中）
//            Directory directory = new RAMDirectory();//索引建立在内存中！！！
            Directory directory = FSDirectory.open(new File("F:/lucene/index01"));//索引创建在硬盘上。
            //2.创建IndexWriter，用它来写索引
            IndexWriterConfig iwc = new IndexWriterConfig(matchVersion, analyzer);
            writer = new IndexWriter(directory, iwc);
            //3.创建Document对象
            Document doc = null;
            //4.为Document添加Field（Field是Document的一个子元素）（Field是那些大小、内容、标题）
            File f = new File("F:/lucene/example");
            for (File file : f.listFiles()) {
                doc = new Document();
                doc.add(new Field("content", new FileReader(file)));
                doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                //5.通过IndexWriter添加文档到索引中
                writer.addDocument(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
        搜索
     */
    public void search() {
        try {
            //1.创建Directory
            Directory directory = FSDirectory.open(new File("F:/lucene/index01"));//索引创建在硬盘上。
            //2.创建 IndexReader
            IndexReader reader = IndexReader.open(directory);
            //3.根据IndexReader创建IndexSearcher
            IndexSearcher searcher = new IndexSearcher(reader);
            //4.创建搜索的Query
            //创建parser来确定要搜索文件的内容，第二个参数表示搜索的域
            QueryParser parser = new QueryParser(Version.LUCENE_44, "content", new StandardAnalyzer(Version.LUCENE_44));
            //创建query，表示搜索域为content中包含java的文档。java是要搜索的东东
            Query query = parser.parse("java");
            //5.根据seacher搜索兵器而返回TopDosc。比如搜索10条
            TopDocs tds = searcher.search(query, 10);
            //6.根据TopDocs获取ScoreDoc对象。scoreDocs为所有文档所存的id号
            ScoreDoc[] sds = tds.scoreDocs;
            for (ScoreDoc sd : sds) {
                //7.根据search和scordDoc对象获取具体的Docement对象。搜索文档id得到document对象，获取那篇文档，文档id就是sd.doc
                Document d = searcher.doc(sd.doc);
                //8.根据Document对象获取需要的值。
                System.out.println(d.get("filename") + "[" + d.get("path") + "]");
            }

            //9.关闭reader
            reader.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //分词器：
    @Test
    public void analyzer() throws IOException {
        String txt = "我在-*学习java";
//        Analyzer analyzer1 = new StandardAnalyzer(Version.LUCENE_44);// 标准分词器
//         Analyzer analyzer2 = new SimpleAnalyzer(Version.LUCENE_44);// 简单分词器
//         Analyzer analyzer3 = new CJKAnalyzer(Version.LUCENE_44);// 二元切分
        Analyzer analyzer4 = new IKAnalyzer(false);// 语意分词
//        TokenStream tokenstream = analyzer1.tokenStream("content", new StringReader(txt));// 生成一个分词流
//         TokenStream tokenstream = analyzer2.tokenStream("content", new StringReader(txt));
//         TokenStream tokenstream = analyzer3.tokenStream("content", new StringReader(txt));
        TokenStream tokenstream = analyzer4.tokenStream("content", new StringReader(txt));
        CharTermAttribute termAttribute = tokenstream.addAttribute(CharTermAttribute.class);// 为token设置属性类
        tokenstream.reset();// 重新设置
        while (tokenstream.incrementToken()) {// 遍历得到token
            System.out.print(new String(termAttribute.buffer(), 0, termAttribute.length()) + "  ");
        }
    }

    public void query() throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("F:/lucene/index01")));// 索引读取类
        IndexSearcher search = new IndexSearcher(reader);// 搜索入口工具类
        String queryStr1 = "java";// 搜索关键字
        BooleanQuery booleanQuery = new BooleanQuery();
        // 条件一内容中必须要有life内容
        QueryParser queryParser = new QueryParser(Version.LUCENE_44, "content", new StandardAnalyzer(Version.LUCENE_44));// 实例查询条件类
        Query query1 = queryParser.parse(queryStr1);
        // 条件二评分大于等于80
        Query query2 = NumericRangeQuery.newIntRange("score", 60, null, true, false);
        booleanQuery.add(query1, BooleanClause.Occur.MUST);
        booleanQuery.add(query2, BooleanClause.Occur.MUST);
        TopDocs topdocs = search.search(booleanQuery, 100);// 查询前100条
        System.out.println("查询结果总数---" + topdocs.totalHits);
        ScoreDoc scores[] = topdocs.scoreDocs;// 得到所有结果集
        for (int i = 0; i < scores.length; i++) {
            int num = scores[i].doc;// 得到文档id
            Document document = search.doc(num);// 拿到指定的文档
            System.out.println("内容====" + document.get("content"));// 由于内容没有存储所以执行结果为null
            System.out.println("id--" + num + "---scors--" + scores[i].score + "---index--" + scores[i].shardIndex);
        }
    }

    @Test
    public void defaultSortTest() throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("F:/lucene/index01")));// 索引读取类
        IndexSearcher search = new IndexSearcher(reader);// 搜索入口工具类
        String queryStr = "Stack";// 搜索关键字
        QueryParser queryParser = new QueryParser(Version.LUCENE_44, "content", new StandardAnalyzer(Version.LUCENE_44));// 实例查询条件类
        Query query = queryParser.parse(queryStr);
//        Sort sort = new Sort(new SortField("score", SortField.Type.INT, true));// false升序true降序
//        TopDocs topdocs = search.search(query, 100, sort);// 查询前100条
        TopDocs topdocs = search.search(query, 100);// 查询前100条
        System.out.println("查询结果总数---" + topdocs.totalHits);
        ScoreDoc scores[] = topdocs.scoreDocs;// 得到所有结果集
        for (int i = 0; i < scores.length; i++) {
            int num = scores[i].doc;// 得到文档id
            Document document = search.doc(num);// 拿到指定的文档
            System.out.println("内容====" + document.get("content"));// 由于内容没有存储所以执行结果为null
            System.out.println("id--" + num + "---scors--" + scores[i].score + "---index--" + scores[i].shardIndex);
        }
    }

    @Test
    public void highlighter() throws IOException, ParseException, InvalidTokenOffsetsException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("F:/lucene/index01")));// 索引读取类
        IndexSearcher search = new IndexSearcher(reader);// 搜索入口工具类
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);// 分词器
        QueryParser qp = new QueryParser(Version.LUCENE_44, "content", analyzer);// 实例查询条件类
        Query query = qp.parse("Java");
        TopDocs topDocs = search.search(query, 100);// 查询前100条
        System.out.println("共查询出:" + topDocs.totalHits + "条数据");
        ScoreDoc scoreDoc[] = topDocs.scoreDocs;// 结果集
        // 高亮
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");// 高亮html格式
        Scorer score = new QueryScorer(query);// 检索评份
        Fragmenter fragmenter = new SimpleFragmenter(100);// 设置最大片断为100
        Highlighter highlighter = new Highlighter(formatter, score);// 高亮显示类
        highlighter.setTextFragmenter(fragmenter);// 设置格式
        for (int i = 0; i < scoreDoc.length; i++) {// 遍历结果集
            int docnum = scoreDoc[i].doc;
            Document doc = search.doc(docnum);
            String content = doc.get("content");
            System.out.println(content);// 原内容
            System.out.println(scoreDoc[i]);
            if (content != null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String str = highlighter.getBestFragment(tokenStream, content);// 得到高亮显示后的内容
                System.out.println(str);
            }
        }
    }

    /**
     * 分页
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void pageTest() throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("F:/lucene/index01")));// 索引读取类
        IndexSearcher search = new IndexSearcher(reader);// 搜索入口工具类
        String queryStr = "Java";// 搜索关键字
        QueryParser queryParser = new QueryParser(Version.LUCENE_44, "content", new StandardAnalyzer(Version.LUCENE_44.LUCENE_44));// 实例查询条件类
        Query query = queryParser.parse(queryStr);// 查询
        TopScoreDocCollector results = TopScoreDocCollector.create(100, false);// 结果集
        search.search(query, results);// 查询前100条
        TopDocs topdocs = results.topDocs(1, 2);// 从结果集中第1条开始取2条
        ScoreDoc scores[] = topdocs.scoreDocs;// 得到所有结果集
        for (int i = 0; i < scores.length; i++) {
            int num = scores[i].doc;// 得到文档id
            Document document = search.doc(num);// 拿到指定的文档
            System.out.println("内容====" + document.get("content"));// 由于内容没有存储所以执行结果为null
            System.out.println("id--" + num + "---scors--" + scores[i].score + "---index--" + scores[i].shardIndex);
        }
    }
}
