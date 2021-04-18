#!/bin/bash

cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/config/
LOGS_DIR=$DEPLOY_DIR/logs
LIB_DIR=$DEPLOY_DIR/lib
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

#可执行jar个数
SpringBootJarCount=`find *.jar|wc -l`
#可自行jar名字
JAR_NAME=`find *.jar`

echo "BIN_DIR = $BIN_DIR, DEPLOY_DIR=$DEPLOY_DIR, CONF_DIR=$CONF_DIR, SpringBootJarCount=$SpringBootJarCount, JAR_NAME=$JAR_NAME "

if [ "$SpringBootJarCount" -gt 1 ];
then
echo -e "\033[0;31m 检测到重复的应用\n $server \033[0m"
    exit 1
fi

if [ "$JAR_NAME" = "" ];
then
    echo -e "\033[0;31m 未检测到可启动的应用 \033[0m"
    exit 1
fi

#应用程序名称
SERVER_NAME=$(grep "\sname:\s[1-9]\d*" config/application.yml)
SERVER_NAME=${SERVER_NAME/name: /}
#SERVER_NAME=$(sed -nr '/name: [0-9]+/ s/.*name: +([0-9]+).*/\1/p' config/application.yml)
if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi


#端口号
#SERVER_PORT=$(sed -nr '/port: [0-9]+/ s/.*port: +([0-9]+).*/\1/p' config/application.yml)
SERVER_PORT=$(grep "\sport:\s[1-9]\d*" config/application.yml)
SERVER_PORT=${server_port/port: /}
#SERVER_PORT=$(sed -nr '/port: [0-9]+/ s/.*port: +([0-9]+).*/\1/p' config/application.yml)
if [ -z "$SERVER_PORT" ]; then
    SERVER_PORT=`8080`
fi

echo "project name: $SERVER_NAME"
echo "server port: $SERVER_PORT"

#日志目录初始化
LOGS_DIR=$DEPLOY_DIR/logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

PIDS=`ps -f | grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ "$1" = "status" ]; then
    if [ -n "$PIDS" ]; then
        echo "The $SERVER_NAME is running...!"
        echo "PID: $PIDS"
        exit 0
    else
        echo "The $SERVER_NAME is stopped"
        exit 0
    fi
fi

if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -verbose:gc -Xloggc:/app/logs/gc.log -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xmx5440M -Xms5440M -XX:MaxMetaspaceSize=512M -XX:MetaspaceSize=512M -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+ParallelRefProcEnabled "
else
    JAVA_MEM_OPTS=" -server -Xms4g -Xmx4g -Xmn4g -XX:PermSize=256m -XX:MaxPermSize=256M -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

#CONFIG_FILES=" -Dlogging.path=$LOGS_DIR -Dlogging.config=$CONF_DIR/log4j2.xml -Dspring.config.location=$CONF_DIR "
#指定config配置文件路径
CONFIG_FILES=" -Dspring.config.location=$CONF_DIR "
#指定依赖包的路径
LIB_FILES=" -Dloader.path=.,$LIB_DIR "
#指定log日志路径
LOG_FILES=" -Dlogging.path=$LOGS_DIR -Dlogging.config=$CONF_DIR/logback-spring.xml"

echo -e "Starting the $SERVER_NAME ..."
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $CONFIG_FILES $LIB_FILES $LOG_FILES -jar $DEPLOY_DIR/$JAR_NAME &

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    if [ -n "$SERVER_PORT" ]; then
        COUNT=`netstat -an | grep $SERVER_PORT | wc -l`
    else
    	COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    fi
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

echo "OK!"
PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"