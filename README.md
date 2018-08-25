# u0008-killer
删除文件中的 \u0008 字符

## 使用
```
usage: u0008 [-h] [-r {y,n}] [-rh {y,n}] [-c C] [-ext EXT] folder

remove \u0008 in the text file(s).

positional arguments:
  folder                 root search folder

named arguments:
  -h, --help             show this help message and exit
  -r {y,n}               search file recursively (default: y)
  -rh {y,n}              replace hidden files (default: n)
  -c C                   charset of input file(s) (default: utf-8)
  -ext EXT               指定需要替换的文件的后缀名, 用 |  连接,  比如 'md'
                         或 'md|java', 默认替换所有文件
```

## 编译安装
```
mvn install
```

# 技术笔记
## Argparse4J 实现
1. choice 设置很棒
2. 长短名设置很棒, 同一个参数可以有多个名字
3. boolean 值体验不佳
4. 解析完之后转换为对象体验不佳

## Args4J
1. 注解体验很棒
2. printUsage 体验不佳
3. boolean 值默认为 true 时如何关闭不知