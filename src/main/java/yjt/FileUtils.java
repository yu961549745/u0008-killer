package yjt;

import java.io.*;

/**
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
        while (reader.ready()) {
            buffer.append(reader.readLine());
            buffer.append(System.lineSeparator());
        }
        reader.close();
        return buffer.toString();
    }
}
