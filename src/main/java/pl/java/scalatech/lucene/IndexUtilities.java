package pl.java.scalatech.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public final class IndexUtilities {
    public static IndexWriter getIndexWriterForDocument(String documentName, Version luceneVersion, String luceneDirectoryPrefix) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer(luceneVersion);
        Directory index = FSDirectory.open(new File(luceneDirectoryPrefix + File.separator + documentName));
        IndexWriterConfig config = new IndexWriterConfig(luceneVersion, analyzer);
        IndexWriter indexWriter = new IndexWriter(index, config);
        return indexWriter;
    }
}