package yjt;

import yjt.argparser.ArgParser4jImpl;
import yjt.argparser.Config;
import yjt.argparser.Parser;
import yjt.replacer.Replacer;

/**
 * 删除指定目录下指定文件的 \u0008 字符
 *
 * @author yjt
 */
public class App {

    public static void main(String[] args) {
        run(new ArgParser4jImpl(), args);
        //run(new Args4jImpl(), args);
    }

    public static void run(Parser parser, String[] args) {
        Config config = parser.parseArgs(args);
        Replacer replacer = new Replacer(config);
        replacer.run();
    }

}
