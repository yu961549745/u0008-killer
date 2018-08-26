package yjt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import yjt.argparser.ArgParser4jImpl;
import yjt.argparser.Parser;
import yjt.replacer.FileUtils;
import yjt.replacer.Replacer;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Before
    public void initTestFile() throws Exception {
        FileUtils.writeFile("test.test", "UTF8", "\u0008ABC\u0008");
    }

    @Test
    public void testRun() {
        String[] args = {"-c=utf-8", "-ext", "test", "."};
        App.run(new ArgParser4jImpl(), args);
    }

    @After
    public void delTestFile() {
        File file = new File("test.test");
        file.delete();
    }
}
