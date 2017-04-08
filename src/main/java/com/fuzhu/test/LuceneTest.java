package com.fuzhu.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
                doc.add(new Field("content",new FileReader(file)));
                doc.add(new Field("filename",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
                doc.add(new Field("path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
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
    public void search(){
        try {
            //1.创建Directory
            Directory directory = FSDirectory.open(new File("F:/lucene/index01"));//索引创建在硬盘上。
            //2.创建 IndexReader
            IndexReader reader = IndexReader.open(directory);
            //3.根据IndexReader创建IndexSearcher
            IndexSearcher searcher = new IndexSearcher(reader);
            //4.创建搜索的Query
            //创建parser来确定要搜索文件的内容，第二个参数表示搜索的域
            QueryParser parser =new QueryParser(Version.LUCENE_44,"content",new StandardAnalyzer(Version.LUCENE_44));
            //创建query，表示搜索域为content中包含java的文档。java是要搜索的东东
            Query query = parser.parse("java");
            //5.根据seacher搜索兵器而返回TopDosc。比如搜索10条
            TopDocs tds =searcher.search(query,10);
            //6.根据TopDocs获取ScoreDoc对象。scoreDocs为所有文档所存的id号
            ScoreDoc[] sds = tds.scoreDocs;
            for (ScoreDoc sd:sds){
                //7.根据search和scordDoc对象获取具体的Docement对象。搜索文档id得到document对象，获取那篇文档，文档id就是sd.doc
                Document d = searcher.doc(sd.doc);
                //8.根据Document对象获取需要的值。
                System.out.println(d.get("filename")+"["+d.get("path")+"]");
            }

            //9.关闭reader
            reader.close();
        }catch (CorruptIndexException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
