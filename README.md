# IS_Utils

> 纷享科技常用的工具类，都是自己在开发中的一些积累，欢迎指正！ 
>
> QQ交流群：661749608

## 引用方法

在pom.xml中添加 

```xml
    <repositories>
        <repository>
            <id>ideaShare-maven-snapshot-repository</id>
            <name>ideaShare-maven-snapshot-repository</name>
            <url>https://raw.github.com/ideaShareTech/maven-repo/snapshot/</url>
        </repository>
        <repository>
            <id>ideaShare-maven-release-repository</id>
            <name>ideaShare-maven-release-repository</name>
            <url>https://raw.github.com/ideaShareTech/maven-repo/release/</url>
        </repository>
    </repositories>
```



## 现有的包

### common-utils

```xml
	<dependency>
            <groupId>tech.ideashare</groupId>
            <artifactId>common-utils</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```



`一些常用的工具`

- MyBatis XML，MODE，DAO生成工具  
    要先初始化`IS_ProjectConfig` `MysqlJdbcConfig` 两个配置类  
    然后调用`IS_GenMapperUtils` `IS_GenMethodUtils` 生成xml，model , dao


- httpUtils  
    http请求的工具类
    1. getAsString/Json
    2. postAsString/Json/byte
- MailUtils  
    发送邮件的工具类，使用前需先实例化一个 IS_MailConfig 传入配置信息
    1. sendSimpleBySMTP 
- SMSUtils  
    发送短信的工具类，中国网建平台，使用前先实例化一个 IS_SMSConfig 传入配置信息
    1. sendSms
