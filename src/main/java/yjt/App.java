package yjt;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * 删除指定目录下指定文件的 \u0008 字符
 *
 * @author yjt
 */
public class App {

    public static void main(String[] args) {
        Replacer replacer = new Replacer(parseArgs(args));
        replacer.run();
    }

    public static Config parseArgs(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("u0008").build().defaultHelp(true).description(
            "remove \\u0008 in the text file(s).");
        parser.addArgument("-r").choices("y", "n").setDefault("y").help("search file recursively");
        parser.addArgument("-rh").choices("y", "n").setDefault("n").help("replace hidden files");
        parser.addArgument("-c").setDefault("utf-8").help("charset of input file(s)");
        parser.addArgument("-ext").help("指定需要替换的文件的后缀名, 用 | 连接, 比如 'md' 或 'md|java', 默认替换所有文件");
        parser.addArgument("folder").help("root search folder");

        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.printHelp();
            System.exit(0);
        }
        Config config = new Config();
        config.searchRecursive = ns.getString("r").equals("y");
        config.replaceHidden = ns.getString("rh").equals("y");
        config.charset = ns.getString("c");
        config.replaceFileExtensions = ns.getString("ext");
        config.folder = ns.getString("folder");

        return config;
    }
}
