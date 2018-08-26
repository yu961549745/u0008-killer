package yjt.argparser;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * 配置对象, 包含默认配置
 *
 * @author yjt
 * @date 2018/08/03
 */
public class Config extends GsonToString {
    @Option(name = "-r", usage = "search file recursively")
    public boolean recursive = false;
    @Option(name = "-rh", usage = "replace hidden files")
    public boolean replaceHidden = false;
    @Option(name = "-c", usage = "charset of input file(s)", metaVar = "charset")
    public String charset = "UTF8";
    @Option(name = "-ext", usage = "指定需要替换的文件的后缀名, 用 | 连接, 比如 'md' 或 'md|java', 默认替换所有文件", metaVar = "ext")
    public String replaceFileExtensions = null;
    @Argument(usage = "root search folder", metaVar = "folder")
    public String folder;
}
