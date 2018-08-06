# u0008-killer
删除文件中的 \u0008 字符

## 使用
```
[usage] u0008 [-r=on/off] [-h=on/off] [-c=charset] [-ext=extensions] folder

-r=on/off       进行递归搜索, 默认 on
-h=on/off       是否搜索隐藏文件夹 '.xxx', 默认 off
-c=charset      指定字符编码
-ext            指定需要替换的文件的后缀名, 用 | 连接, 比如 'md' 或 'md|java', 默认替换所有文件
```

## 编译安装
```
mvn install
```