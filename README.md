# 本系统时一套完整的基于SSM框架的网上商城<br>
### 效果展示：<br>
前台登录：
![Image text](https://raw.github.com/yourName/repositpry/master/yourprojectName/img-folder/test.jpg)

启动项目准备：
##### （1）启动zookeeper     [root@localhost bin]# ./zkServer.sh start<br>
##### （2）启动nginx：进入sbin目录  [root@localhost sbin]# ./nginx <br>
##### （3）* redis的启动：后台启动：把/root/redis-3.0.0/redis.conf复制到/usr/local/redis/bin目录下<br>
#####  [root@localhost redis-3.0.0]# cp redis.conf /usr/local/redis/bin/<br>
##### （4）启动Solr服务器：启动tomcat
##### （6）启动数据库、图片服务器


### 涉及技术：<br>
###### （1）Zookeeper（服务中间件dubbo)<br>
> 发布服务：<br>
`<!-- 和本地服务一样实现远程服务 -->`<br>
`<bean id="xxxService" class="com.xxx.XxxServiceImpl" />`<br>
`<!-- 增加暴露远程服务配置 -->`<br>
`<dubbo:service interface="com.xxx.XxxService" ref="xxxService" />`<br><br>

> 调用服务：<br>
`<!-- 和本地服务一样实现远程服务 -->					          `<br>
`<bean id="xxxService" class="com.xxx.XxxServiceImpl" />          `<br>
`<!-- 增加暴露远程服务配置 -->							          `<br>
`<dubbo:service interface="com.xxx.XxxService" ref="xxxService" />`<br>
`<!-- 增加引用远程服务配置 -->`<br>
`<dubbo:reference id="xxxService" interface="com.xxx.XxxService" />`<br>
`<!-- 和本地服务一样使用远程服务 -->`<br>
`<bean id="xxxAction" class="com.xxx.XxxAction">`<br>
`<property name="xxxService" ref="xxxService" />`<br>
`</bean>`<br>

* 2.3.2.	Zookeeper的安装<br>
安装环境：<br>
Linux：centos6.4<br>
Jdk:1.7以上版本<br>

Zookeeper是java开发的可以运行在windows、linux环境。需要先安装jdk。<br>
安装步骤：<br>
1. ：安装jdk<br>
2. ：把zookeeper的压缩包上传到linux系统。<br>
3. ：解压缩压缩包tar -zxvf zookeeper-3.4.6.tar.gz<br>
4. ：进入zookeeper-3.4.6目录，创建data文件夹。<br>
5. ：把zoo_sample.cfg改名为zoo.cfg   [root@localhost conf]# mv zoo_sample.cfg zoo.cfg<br>
6. ：修改data属性：dataDir=/root/zookeeper-3.4.6/data<br>
7. ：启动zookeeper     [root@localhost bin]# ./zkServer.sh start<br>
  关闭：[root@localhost bin]# ./zkServer.sh stop<br>
  查看状态：[root@localhost bin]# ./zkServer.sh status<br>

* Dubbo监控中心<br>
1. 部署监控中心：<br>
[root@localhost ~]# cp dubbo-admin-2.5.4.war apache-tomcat-7.0.47/webapps/dubbo-admin.war <br>
2. 启动tomcat<br>
3. 访问http://192.168.25.167:8080/dubbo-admin/   用户名：root  密码：root<br>
  如果监控中心和注册中心在同一台服务器上，可以不需要任何配置。 br>
 如果不在同一台服务器，需要修改配置文件： /root/apache-tomcat-7.0.47/webapps/dubbo-admin/WEB-INF/dubbo.properties <br>
 

###### （2）分页插件Pagehelper<br>
* 使用方法<br>
第一步：把PageHelper依赖的jar包添加到工程中。官方提供的代码对逆向工程支持的不好，使用参考资料中的pagehelper-fix。<br>
第二步：在Mybatis配置xml中配置拦截器插件:<br>
`<plugins>`<br>
    `<!-- com.github.pagehelper为PageHelper类所在包名 -->`<br>
    `<plugin interceptor="com.github.pagehelper.PageHelper">`<br>
        `<!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库-->        `<br>
        `<property name="dialect" value="mysql"/>`<br>
   ` </plugin>`<br>
`</plugins>`<br>
第三步：在代码中使用<br>
1、设置分页信息：<br>
    //获取第1页，10条内容，默认查询总数count<br>
   ` PageHelper.startPage(1, 10);`<br>
    //紧跟着的第一个select方法会被分页<br>
`List<Country> list = countryMapper.selectIf(1);`<br>
2、取分页信息<br>
//分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，<br>
`Page<Country> listCountry = (Page<Country>)list;`<br>
`listCountry.getTotal();`<br>

##### （3）nginx<br>
* nginx安装<br>
 下载nginx：http://nginx.org/   使用的版本是1.8.0版本。<br>
 1、需要安装gcc的环境。yum install gcc-c++<br>
2、第三方的开发包。<br>
	PCRE：	PCRE(Perl Compatible Regular Expressions)是一个Perl库，包括 perl 兼容的正则表达式库。nginx的http模块使用pcre来解析正则表达式，所以需要在linux上安装pcre库。<br>
yum install -y pcre pcre-devel<br>
注：pcre-devel是使用pcre开发的一个二次开发库。nginx也需要此库。<br>
zlib：	zlib库提供了很多种压缩和解压缩的方式，nginx使用zlib对http包的内容进行gzip，所以需要在linux上安装zlib库。<br>
yum install -y zlib zlib-devel<br>
	openssl：	OpenSSL 是一个强大的安全套接字层密码库，囊括主要的密码算法、常用的密钥和证书封装管理功能及SSL协议，并提供丰富的应用程序供测试或其它目的使用。	nginx不仅支持http协议，还支持https（即在ssl协议上传输http），所以需要在linux安装openssl库。<br>
  yum install -y openssl openssl-deve<br>
<br>
* 	安装步骤<br>

第一步：把nginx的源码包上传到linux系统<br>
第二步：解压缩<br>
[root@localhost ~]# tar zxf nginx-1.8.0.tar.gz <br>
第三步：使用configure命令创建一makeFile文件。<br>
./configure \<br>
--prefix=/usr/local/nginx \<br>
--pid-path=/var/run/nginx/nginx.pid \<br>
--lock-path=/var/lock/nginx.lock \<br>
--error-log-path=/var/log/nginx/error.log \<br>
--http-log-path=/var/log/nginx/access.log \<br>
--with-http_gzip_static_module \<br>
--http-client-body-temp-path=/var/temp/nginx/client \<br>
--http-proxy-temp-path=/var/temp/nginx/proxy \<br>
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \<br>
--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \<br>
--http-scgi-temp-path=/var/temp/nginx/scgi<br>
注意：启动nginx之前，上边将临时文件目录指定为/var/temp/nginx，需要在/var下创建temp及nginx目录<br>
[root@localhost sbin]# mkdir /var/temp/nginx/client -p<br>
第四步：make<br>
第五步：make install<br>

*  启动nginx<br>
进入sbin目录  [root@localhost sbin]# ./nginx <br><br>
关闭nginx：[root@localhost sbin]# ./nginx -s stop<br>
推荐使用：[root@localhost sbin]# ./nginx -s quit<br>
重启nginx：<br>
1、先关闭后启动。<br>
2、刷新配置文件：<br>
[root@localhost sbin]# ./nginx -s reload<br>

##### （4）FastDFS分布式文件系统<br>
* 上传步骤<br>
1、加载配置文件，配置文件中的内容就是tracker服务的地址。<br>
配置文件内容：tracker_server=192.168.25.133:22122<br>
2、创建一个TrackerClient对象。直接new一个。<br>
3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。<br>
4、创建一个StorageServer的引用，值为null<br>
5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用<br>
6、使用StorageClient对象上传图片。<br>
7、返回数组。包含组名和图片的路径<br>

##### （5）Redis服务器<br>
* Redis的安装
Redis是c语言开发的。
安装redis需要c语言的编译环境。如果没有gcc需要在线安装。yum install gcc-c++
安装步骤：<br>
第一步：redis的源码包上传<br>到linux系统。<br>
第二步：解压缩redis。<br>
第三步：编译。进入redis源码目录。make <br>
第四步：安装。make install PREFIX=/usr/local/redis<br>
PREFIX参数指定redis的安装目录。一般软件安装到/usr目录下<br>

* 连接redis<br>
 * redis的启动：<br>
前端启动：在redis的安装目录下直接启动redis-server<br>
[root@localhost bin]# ./redis-server <br>
后台启动：把/root/redis-3.0.0/redis.conf复制到/usr/local/redis/bin目录下<br>
[root@localhost redis-3.0.0]# cp redis.conf /usr/local/redis/bin/<br>
修改配置文件：[root@localhost bin]# ./redis-server redis.conf<br>
查看redis进程：[root@localhost bin]# ps aux|grep redis<br>

* Redis-cli<br>
[root@localhost bin]# ./redis-cli <br>
默认连接localhost运行在6379端口的redis服务。<br>
[root@localhost bin]# ./redis-cli -h 192.168.25.153 -p 6379<br>
-h：连接的服务器的地址<br>
-p：服务的端口号<br>


##### （6）Solr服务器<br>
####### 搭建步骤<br>
 * 第一步：把solr 的压缩包上传到Linux系统<br>
 * 第二步：解压solr。<br>
 * 第三步：安装Tomcat，解压缩即可。<br>
 * 第四步：把solr部署到Tomcat下。<br><br>
 * 第五步：解压缩war包。启动Tomcat解压。<br>
 * 第六步：把/root/solr-4.10.3/example/lib/ext目录下的所有的jar包，添加到solr工程中。<br>
 [root@localhost ext]# pwd<br>
 /root/solr-4.10.3/example/lib/ext<br>
 [root@localhost ext]# cp * /usr/local/solr/tomcat/webapps/solr/WEB-INF/lib/<br>
 * 第七步：创建一个solrhome。/example/solr目录就是一个solrhome。复制此目录到/usr/local/solr/solrhome<br>
 [root@localhost example]# cp -r solr /usr/local/solr/solrhome<br>
 * 第八步：关联solr及solrhome。需要修改solr工程的web.xml文件。<br>
 * 第九步：启动Tomcat: http://192.168.25.154:8080/solr/ 和 windows 下的配置完全一样。
<br>
##### （7）ActiveMQ整合spring<br>
####### Activemq整合spring<br>
* 第一步：引用相关的jar包。<br>
* 第二步：配置Activemq整合spring。配置ConnectionFactory<br>
* 第三步：配置生产者。 使用JMSTemplate对象。发送消息。<br>
* 第四步：在spring容器中配置Destination。<br>

##### （8）sso注册登录功能实现<br>
##### （9）Ajax跨域请求（jsonp）<br>
