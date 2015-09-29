[護理站點管理Demo頁面](http://ec2-52-26-138-212.us-west-2.compute.amazonaws.com:8080/nurse-station-api/)
# 建置說明
1. create schema
2. deploy 

# Server配置
**Servlet Container: Tomcat 7**
```sh
$ java -cp catalina.jar org.apache.catalina.util.ServerInfo
Server version: Apache Tomcat/7.0.54
Server built:   Mar 24 2015 07:49:05
Server number:  7.0.54.0
OS Name:        Linux
OS Version:     3.10.0-229.4.2.el7.x86_64
Architecture:   amd64
JVM Version:    1.8.0_45-b13
JVM Vendor:     Oracle Corporation
```

**Database: MariaDB 5.5**
```sh
MariaDB [(none)]> status
--------------
mysql  Ver 15.1 Distrib 5.5.41-MariaDB, for Linux (x86_64) using readline 5.1

Connection id:		12
Current database:
Current user:		root@localhost
SSL:			Not in use
Current pager:		stdout
Using outfile:		''
Using delimiter:	;
Server:			MariaDB
Server version:		5.5.41-MariaDB MariaDB Server
Protocol version:	10
Connection:		Localhost via UNIX socket
Server characterset:	utf8
Db     characterset:	utf8
Client characterset:	utf8
Conn.  characterset:	utf8
UNIX socket:		/var/lib/mysql/mysql.sock
Uptime:			4 hours 30 min 49 sec

Threads: 2  Questions: 198  Slow queries: 0  Opens: 3  Flush tables: 2  Open tables: 29  Queries per second avg: 0.012
--------------
```
# 後端實作
使用Jersey建構Restful API供前端存取資料

|Resource|GET|PUT|POST|DELETE|
|--------|---|---|----|------|
|{site}/rest/nurse|取得護理人員清單|N/A|新增護理人員|N/A|
|{site}/rest/nurse/{id}|N/A|更新護理人員資料|N/A|刪除護理人員|
|{site}/rest/station|取得護理站點清單|N/A|新增護理站點|N/A|
|{site}/rest/station/{id}|N/A|更新護理站點資料|N/A|刪除護理站點|
|{site}/rest/station/{id}/nurse|取得護理站點負責人員|N/A|新增護理站點負責人員|刪除護理站點負責人員|
|{site}/rest/station/{id}/nurse-not-in|取得尚未指派至該護理站點之人員清單|N/A|N/A|N/A|





```sh
$ gradle d
:dependencies

------------------------------------------------------------
Root project
------------------------------------------------------------

archives - Configuration for archive artifacts.
No dependencies

compile - Compile classpath for source set 'main'.
+--- mysql:mysql-connector-java:5.1.36
+--- com.google.code.gson:gson:2.3.1
+--- org.glassfish.jersey.bundles:jaxrs-ri:2.22
+--- org.glassfish.jersey.media:jersey-media-moxy:2.22
```
# 前端實作
使用jqGrid建構表格供user操作CRUD
```sh
14:17 $ bower list
bower check-new     Checking for new versions of the project dependencies...
WebContent /Users/Hamn/Workspace/eclipse-workspace/nurse-station-api/WebContent
└─┬ jqGrid#5.0.0 extraneous
  ├── jquery#2.1.4 (3.0.0-alpha1+compat available)
  └─┬ jquery-ui#1.11.4
    └── jquery#2.1.4 (3.0.0-alpha1+compat available)
```
# 網站架構
```sh
nurse-station-api
├── index.html <-- 護理站點管理頁面
├── js
│   ├── jqGrid <-- 產生前端表格的JQuery plugin
│   ├── jquery
│   └── jquery-ui
└── WEB-INF
    ├── classes
    │   ├── com
    │   │   └── henry
    │   │       ├── app
    │   │       │   └── RestApp.class <-- Resource Config
    │   │       ├── controller
    │   │       │   ├── CORSResponseFilter.class <-- 開放 跨來源資源共享(Cross-Origin Resource Sharing, CORS)
    │   │       │   ├── NurseRS.class <-- 護理人員Resource CRUD
    │   │       │   ├── StationRS.class <-- 站點人員Resource CRUD
    │   │       │   └── StationRS$Result.class <-- 回response code
    │   │       ├── dao
    │   │       │   └── AppDao.class <-- 所有的Data Access
    │   │       ├── model
    │   │       │   ├── Assignment.class <-- 護理站點所分派的負責人員
    │   │       │   ├── Nurse.class <-- 護理人員
    │   │       │   └── Station.class <-- 護理站點
    │   │       └── util
    │   │           └── DbUtil.class <-- 連線Database
    │   └── db.properties <-- 連線Database的參數
    ├── lib
    │   ├── aopalliance-repackaged-2.4.0-b31.jar
    │   ├── gson-2.3.1.jar
    │   ├── hk2-api-2.4.0-b31.jar
    │   ├── hk2-locator-2.4.0-b31.jar
    │   ├── hk2-utils-2.4.0-b31.jar
    │   ├── javassist-3.18.1-GA.jar
    │   ├── javax.annotation-api-1.2.jar
    │   ├── javax.inject-1.jar
    │   ├── javax.inject-2.4.0-b31.jar
    │   ├── javax.json-1.0.4.jar
    │   ├── javax.ws.rs-api-2.0.1.jar
    │   ├── jaxrs-ri-2.22.jar
    │   ├── jersey-client-2.22.jar
    │   ├── jersey-common-2.22.jar
    │   ├── jersey-container-servlet-2.22.jar
    │   ├── jersey-container-servlet-core-2.22.jar
    │   ├── jersey-entity-filtering-2.22.jar
    │   ├── jersey-guava-2.22.jar
    │   ├── jersey-media-jaxb-2.22.jar
    │   ├── jersey-media-moxy-2.22.jar
    │   ├── jersey-server-2.22.jar
    │   ├── mysql-connector-java-5.1.36.jar
    │   ├── org.eclipse.persistence.asm-2.6.0.jar
    │   ├── org.eclipse.persistence.core-2.6.0.jar
    │   ├── org.eclipse.persistence.moxy-2.6.0.jar
    │   ├── osgi-resource-locator-1.0.1.jar
    │   └── validation-api-1.1.0.Final.jar
    └── web.xml

193 directories, 1929 files

```
