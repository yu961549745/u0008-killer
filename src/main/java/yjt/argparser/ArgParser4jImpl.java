package yjt.argparser;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * net.sourceforge.argparse4j 实现
 *
 * @author yjt
 */
public class ArgParser4jImpl implements Parser {
    private ArgumentParser parser;

    public ArgParser4jImpl() {
        parser = ArgumentParsers.newFor("u0008").build().defaultHelp(true).description(
            "remove \\u0008 in the text file(s).");
        parser.addArgument("-r").nargs("?").type(Boolean.class).setConst(true).setDefault(false).help(
            "search file recursively");
        parser.addArgument("-rh").nargs("?").type(Boolean.class).setConst(true).setDefault(false).help(
            "replace hidden files");
        parser.addArgument("-c").setDefault("utf-8").help("charset of input file(s)");
        parser.addArgument("-ext").help("指定需要替换的文件的后缀名, 用 | 连接, 比如 'md' 或 'md|java', 默认替换所有文件");
        parser.addArgument("folder").help("root search folder");
    }

    @Override
    public Config parseArgs(String[] args) {
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            System.out.println(e.getMessage());
            printUsage();
            System.exit(1);
        }
        Config config = new Config();
        config.recursive = ns.getBoolean("r");
        config.replaceHidden = ns.getBoolean("rh");
        config.charset = ns.getString("c");
        config.replaceFileExtensions = ns.getString("ext");
        config.folder = ns.getString("folder");

        return config;
    }

    @Override
    public void printUsage() {
        parser.printHelp();
    }
}
