package yjt;

import java.io.File;

/**
 * 删除指定目录下指定文件的 \u0008 字符
 *
 * @author yjt
 */
public class App {
    private Config config = null;
    private String helpString;

    public App(String[] args) throws Exception {
        helpString = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResourceAsStream("help.txt"),
            "utf8");
        config = parseArgs(args);
    }

    private Config parseArgs(String[] args) {
        Config config = new Config();
        for (String arg : args) {
            if (arg.startsWith("-r")) {
                if (!arg.matches("-r(=(on|off))?")) {
                    return null;
                }
                config.searchRecursive = arg.matches("-r(=on)?");
            } else if (arg.startsWith("-h")) {
                if (!arg.matches("-h(=(on|off))?")) {
                    return null;
                }
                config.replaceHidden = arg.matches("-e=(on)?");
            } else if (arg.startsWith("-c")) {
                config.charset = arg.substring(3);
            } else if (arg.startsWith("-ext")) {
                String extensions = arg.substring(5);
                if (!extensions.matches("\\w+(\\|\\w+)*")) {
                    return null;
                }
                config.replaceFileExtensions = extensions;
            } else {// 只有一个冗余参数作为 folder
                if (config.folder == null) {
                    config.folder = arg;
                } else {
                    return null;
                }
            }
        }
        if (config.folder == null) {
            return null;
        }
        return config;
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
        if (config == null) {
            System.out.println(helpString);
            return;
        }
        File rootFolder = new File(config.folder);
        for (File file : rootFolder.listFiles()) {
            recursiveReplace(file);
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App(args);
        app.run();
    }
}
