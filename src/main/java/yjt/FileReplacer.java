package yjt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 进行文件内 u0008 字符替换和计数
 *
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

    private static Pattern pattern = Pattern.compile("\\u0008");

    public int replaceFile() {
        int count = 0;
        try {
            String content = FileUtils.readFile(path, charset);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                count++;
            }
            content = content.replaceAll("\\u0008", "");
            FileUtils.writeFile(path, charset, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
