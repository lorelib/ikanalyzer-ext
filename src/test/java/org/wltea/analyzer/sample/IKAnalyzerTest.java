package org.wltea.analyzer.sample;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by listening on 2016/12/28.
 */
public class IKAnalyzerTest {
    @Test
    public void test1() throws IOException {
        String word = "浙江省绍兴市嵊州市北门新村26-103";
        IKAnalyzer analyzer = new IKAnalyzer(true);
        TokenStream ts = analyzer.tokenStream("content", new StringReader(word));
        //获取词元位置属性
        OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
        //获取词元文本属性
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        //获取词元文本属性
        TypeAttribute type = ts.addAttribute(TypeAttribute.class);

        //重置TokenStream（重置StringReader）
        ts.reset();
        //迭代获取分词结果
        while (ts.incrementToken()) {
            System.out.print(term.toString() + "  ");
        }
        ts.end();
        ts.close();
    }
}
