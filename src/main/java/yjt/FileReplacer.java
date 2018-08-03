package yjt;

import java.io.*;

/**
 * @author yjt
 * @date 2018/08/03
 */
public class FileReplacer {
    private String path;
    private String charset;

    public FileReplacer(String path, String charset) {
        this.path = path;
        this.charset = charset;
    }

    public void replaceFile() {
        try {
            String content = FileUtils.readFile(path, charset);
            content = content.replaceAll("\\u0008", "");
            FileUtils.writeFile(path, charset, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
