package yjt.replacer;

import java.io.*;

/**
 * 进行文件读写相关操作
 *
 * @author yjt
 * @date 2018/08/03
 */
public class FileUtils {

    public static String readFile(String path, String charset) throws IOException {
        return readFile(new FileInputStream(path), charset);
    }

    public static void writeFile(String path, String charset, String content) throws Exception {
        PrintWriter writer = new PrintWriter(new File(path), charset);
        writer.println(content);
        writer.close();
    }

    public static String readFile(InputStream inputStream, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
        StringBuffer buffer = new StringBuffer();
        boolean isFirstLine = true;
        while (reader.ready()) {
            if (isFirstLine) {
                isFirstLine = false;
            } else {
                buffer.append(System.lineSeparator());
            }
            buffer.append(reader.readLine());
        }
        reader.close();
        return buffer.toString();
    }
}
