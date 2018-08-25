package yjt.argparser;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author yjt
 */
public class Args4JImpl implements Parser {

    @Override
    public Config parseArgs(String[] args) {
        Config config = new Config();
        CmdLineParser parser = new CmdLineParser(config);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
            System.exit(0);
        }
        return config;
    }
}
