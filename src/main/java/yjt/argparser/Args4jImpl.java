package yjt.argparser;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author yjt
 */
public class Args4jImpl implements Parser {
    private Config config = new Config();
    private CmdLineParser parser = new CmdLineParser(config);

    @Override
    public Config parseArgs(String[] args) {
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            printUsage();
            System.exit(1);
        }
        if (config.folder == null) {
            printUsage();
            System.exit(1);
        }
        return config;
    }

    @Override
    public void printUsage() {
        System.out.print("usage: u0008 ");
        parser.printSingleLineUsage(System.out);
        System.out.println();
        parser.printUsage(System.out);
    }
}
