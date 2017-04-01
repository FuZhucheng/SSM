package com.fuzhu.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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
}
