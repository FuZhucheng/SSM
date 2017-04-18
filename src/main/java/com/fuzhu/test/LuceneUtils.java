package com.fuzhu.test;

import com.fuzhu.utils.Constant;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
public class LuceneUtils {
    private static Directory directory_sp = null;


    private static IndexWriterConfig config = null;

    private static Version matchVersion = null;

    private static Analyzer analyzer = null;

    private static Directory ramDirectory = null;


    static {
        try {
            directory_sp = FSDirectory.open(new File(Constant.INDEXURL_ALL));
            matchVersion = Version.LUCENE_44;
            analyzer = new IKAnalyzer();
            config = new IndexWriterConfig(matchVersion, analyzer);
            System.out.println("directory_sp    " + directory_sp);
            // 创建内存索引库
            ramDirectory = new RAMDirectory(directory_sp, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 返回用于操作索引的对象
     * */
    public static IndexWriter getIndexWriterOfSP() throws IOException {

        IndexWriter indexWriter = new IndexWriter(directory_sp, config);

        return indexWriter;
    }


    /*
     * 返回用于读取索引的对象
     * */
    public static IndexSearcher getIndexSearcherOfSP() throws IOException {

        System.out.println("directory_sp    " + directory_sp);
        IndexReader indexReader = DirectoryReader.open(ramDirectory);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        return indexSearcher;
    }

    /*
     * 获取lucene当前的版本
     * */
    public static Version getMatchVersion() {
        return matchVersion;
    }

    /*
     * 获取分词器
     * */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }

}
