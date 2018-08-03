package yjt;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    //@Test
    public void testRun() throws Exception {
        String[] args = {"-r", "-h=off", "-c=utf8", "-ext=md|java", "."};
        App app = new App(args);
        app.run();
    }
}
