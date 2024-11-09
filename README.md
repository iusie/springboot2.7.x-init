## 快速上手

> 用 `todo`标识了修改配置的地方，便于找到修改的位置~

> tips: 如果用IDE开发，有键盘热键先把热键管理，按Shift+Ctrl+F全局搜索,Shift+Ctrl+R全局修改,实现名字修改

### MySQL 数据库
1）修改 `application.yml` 的数据库配置为自己的：
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/init #数据库名称
    username: #数据库账号
    password: #数据库密码
```

2）执行 `sql/init.sql` 中的数据库语句，自动创建库表

3）启动项目，访问 `http://localhost:9000/api/doc.html` 即可打开接口文档

### Redis 分布式登录

1）修改 `application.yml` 的 Redis 配置为你自己的：

```yml
spring:
  redis:
    database: 1 #数据库索引:可选0-15
    port: 6379
    timeout: 5000
    host: localhost
    password: #密码
```

2）修改 `application.yml` 中的 session 存储方式：

```yml
spring:
  session:
    store-type: redis
```

### Elasticsearch 搜索引擎

1）修改 `application.yml` 的 Elasticsearch 配置为自己的：

```yml
spring:
  elasticsearch:
    uris: http://localhost:9200
    username: #账号
    password: #密码
```

2）复制 `sql/post_es_mapping.json` 文件中的内容，通过调用 Elasticsearch 的接口或者 Kibana Dev Tools 来创建索引（相当于数据库建表）

```
PUT post_v1
{
 参数见 sql/post_es_mapping.json 文件
}
```

3）开启同步任务，将数据库的帖子同步到 Elasticsearch

找到 job 目录下的 `FullSyncPostToEs` 和 `IncSyncPostToEs` 文件，取消掉 `@Component` 注解的注释，再次执行程序即可触发同步：

```java
// todo 取消注释开启任务
//@Component
```

### 业务代码生成器

支持自动生成 Service、Controller、数据模型代码，配合 MyBatisX 插件，可以快速开发增删改查等实用基础功能。

找到 `generate.CodeGenerator` 类，修改生成参数和生成路径，并且支持注释掉不需要的生成逻辑，然后运行即可。

```
// 指定生成参数
String packageName = "top.cusie.springinit";
String dataName = "用户评论";
String dataKey = "userComment";
String upperDataKey = "UserComment";
```

生成代码后，可以移动到实际项目中，并且按照 `// todo` 注释的提示来针对自己的业务需求进行修改。
