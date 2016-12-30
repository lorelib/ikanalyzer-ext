package org.wltea.analyzer.sample;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;
import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by listening on 2016/12/28.
 */
public class IKAnalyzerTest {
    @Test
    public void testWithLucene() throws IOException {
        String word = "重庆江北区重庆市江北区海尔路北门新村181号兰溪小区30幢3单元6-1";
        IKAnalyzer analyzer = new IKAnalyzer(true);
        TokenStream ts = analyzer.tokenStream("content", new StringReader(word));
        //获取词元位置属性
        OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
        //获取词元文本属性
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        //获取词元文本属性
        TypeAttribute type = ts.addAttribute(TypeAttribute.class);
        KeywordAttribute keyword = ts.addAttribute(KeywordAttribute.class);
        FlagsAttribute flags = ts.addAttribute(FlagsAttribute.class);

        //重置TokenStream（重置StringReader）
        ts.reset();
        //迭代获取分词结果
        while (ts.incrementToken()) {
            System.out.print(term.toString() + "  ");
            /*System.out.println(term.toString() + "  " + offset.toString() + "  " + type.toString()
                    //+ "  " + keyword.toString() + "  " + flags.toString()
            );*/
        }
        ts.end();
        ts.close();
    }

    @Test
    public void testWithNoLucene() throws IOException {
        // 检索内容
        String word = "重庆江北区重庆市江北区海尔路北门新村181号兰溪小区30幢3单元6-1";
        StringReader reader = new StringReader(word);
        IKSegmenter ik = new IKSegmenter(reader, true);
        Lexeme lexeme = null;
        while ((lexeme = ik.next()) != null) {
            System.out.println(lexeme.getLexemeText());
        }
        reader.close();
    }
}
