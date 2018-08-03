package yjt;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    //@Test
    public void testRun() throws Exception {
        String[] args = {"-r", "-h=off", "-c=utf8", "-ext=md", "/Users/yjt/Desktop/idea-tutorial"};
        App app = new App(args);
        app.run();
    }
}
