# 相关链接
[gremlin图查询插件_gremlin安装-CSDN博客](https://blog.csdn.net/shlhhy/article/details/124940031)
# 版本选择
gremlin版本选择并没有特殊要求，建议直接安装最新版本。
由于gremlin对neo4j的支持版本有限制，建议安装3.4.0以下版本（我个人安装的是3.3.9版本）

# 安装步骤
安装neo4j和gremlin时需要注意先安装相应的java版本（以gremlin 3.7.0和neo4j 3.3.9为例，需要下载安装jdk1.8.0）
以下是安装中的常见问题
### 如何安装neo4j 3.x版本

```shell
wget -O - https://debian.neo4j.com/neotechnology.gpg.key | sudo apt-key add -
echo 'deb https://debian.neo4j.com stable legacy' | sudo tee /etc/apt/sources.list.d/neo4j.list
sudo apt-get update
apt list -a neo4j#这时候会列出可以安装的neo4j的名字，安装即可
apt install neo4j=1:3.3.9
```
*注：该命令使用时需要有服务器root权限，没有的情况下可以使用docker。若使用docker，请暴漏如下端口：7474，7687，8182，7688（备用，可以修改）*

## 如何安装gremlin -server

```shell
wget https://dlcdn.apache.org/tinkerpop/3.7.0/apache-tinkerpop-gremlin-server-3.7.0-bin.zip
```

然后解压即可

## 嵌入步骤
整个嵌入步骤可以参考[gremlin图查询插件_gremlin安装-CSDN博客](https://blog.csdn.net/shlhhy/article/details/124940031)
- 首先，进入解压后的server文件夹执行以下命令：
```shell
bin/gremlin-server.sh install org.apache.tinkerpop neo4j-gremlin 3.7.0#可以修改为相应版本
```

- 第二步 配置gremlin-server-neo4j.yaml和neo4j-empty.properties
先在gremlin-server-neo4j.yaml配置（二者都在一个服务器上配置时无需修改）
```bash
host: 0.0.0.0#允许所有地址链接（可以自行配置允许的地址）
port: 7687
```
然后在neo4j-empty.properties配置需要连接的neo4j的数据库

```bash
gremlin.neo4j.directory=/opt/neo4j/data/databases/graph.db#neo4j所在的数据库的文件
```
*（注，如何查看neo4j的数据库文件所在地址：
执行`neo4j console`会显示出相应的文件位置，例如下图：![](./Pasted%20image%2020230917133650.png)
数据库所在地址为：/var/lib/neo4j/data/databases/graph.db
）*

## 嵌入后启动：
执行`bin/gremlin-server.sh conf/gremlin-server-neo4j.yaml`命令即可验证嵌入后是否可以执行。注意**若无法执行，请检查neo4j是否还在启动状态，两者中先启动的会对数据库加锁，目前暂时无法同时启动**
当成功启动后，修改`bin/gremlin-server.sh`如下
```shell
#修改前
  
......
if [[ -z "$GREMLIN_YAML" ]]; then

  GREMLIN_YAML=$GREMLIN_HOME/conf/gremlin-server.yaml

fi
......

#修改后

......

if [[ -z "$GREMLIN_YAML" ]]; then

  GREMLIN_YAML=$GREMLIN_HOME/conf/gremlin-server-neo4j.yaml

fi
......

```
修改后使用`bin/gremlin-server.sh start`即可启动服务。

