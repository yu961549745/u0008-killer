package yjt.replacer;

import yjt.argparser.Config;

import java.io.File;

/**
 * @author yjt
 */
public class Replacer {
    private Config config;

    public Replacer(Config config) {
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
        return config.recursive && needReplaceHidden(file);
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
}
