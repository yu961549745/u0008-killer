package yjt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void testRun() throws Exception {
        String[] args = {"-r", "-h=off", "-c=utf8", "-ext=test", "."};
        App app = new App(args);
        app.run();
    }

    @After
    public void delTestFile() {
        File file = new File("test.test");
        file.delete();
    }
}
