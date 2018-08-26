package yjt.argparser;

/**
 * @author yjt
 */
public interface Parser {
    /**
     * 将命令行参数转化为 Config 对象
     *
     * @param args 命令行参数
     * @return
     */
    Config parseArgs(String[] args);

    /**
     * 输出使用帮助
     */
    void printUsage();
}
