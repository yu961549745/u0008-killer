package yjt;

import org.junit.Before;
import org.junit.Test;
import yjt.argparser.ArgParser4jImpl;
import yjt.argparser.Args4jImpl;
import yjt.argparser.Config;
import yjt.argparser.Parser;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private String[] args = {".", "-r", "-c=utf-8", "-ext", "test"};
    private Config config;

    @Before
    public void init() {
        config = new Config();
        config.recursive = true;
        config.replaceHidden = false;
        config.charset = "utf-8";
        config.replaceFileExtensions = "test";
        config.folder = ".";
    }

    @Test
    public void testArgParse4j() {
        Parser parser = new ArgParser4jImpl();
        System.out.println(">>>>>>>>>> argparse4j");
        parser.printUsage();
        Config config = parser.parseArgs(args);
        assertEquals(this.config, config);
    }

    @Test
    public void testArgs4j() {
        Parser parser = new Args4jImpl();
        System.out.println(">>>>>>>>>> args4j");
        parser.printUsage();
        Config config = parser.parseArgs(args);
        assertEquals(this.config, config);
    }
}
