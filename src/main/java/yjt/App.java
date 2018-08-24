package yjt;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.File;

/**
 * 删除指定目录下指定文件的 \u0008 字符
 *
 * @author yjt
 */
public class App {
    private Config config;

    public App(Config config) {
        this.config = config;
    }

    private void replaceFile(File file) {
        FileReplacer replacer = new FileReplacer(file.getAbsolutePath(), config.charset);
        System.out.println(file.getPath() + " -> " + replacer.replaceFile());
    }

    private boolean needReplaceHidden(File file) {
        return config.replaceHidden || !file.getName().startsWith(".");
    }

    private boolean needReplaceFile(File file) {
        return config.replaceFileExtensions == null || file.getName().matches(
            ".*\\.(" + config.replaceFileExtensions + ")");
    }

    private boolean needSearchFolder(File file) {
        return config.searchRecursive && needReplaceHidden(file);
    }

    private void recursiveReplace(File file) {
        if (file.isDirectory()) {
            if (needSearchFolder(file)) {
                for (File f : file.listFiles()) {
                    recursiveReplace(f);
                }
            }
        } else {
            if (needReplaceFile(file) && needReplaceHidden(file)) {
                replaceFile(file);
            }
        }
    }

    public void run() {
        System.out.println("u0008 run in " + new File(".").getAbsolutePath());
        File rootFolder = new File(config.folder);
        for (File file : rootFolder.listFiles()) {
            recursiveReplace(file);
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App(parseArgs(args));
        app.run();
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
