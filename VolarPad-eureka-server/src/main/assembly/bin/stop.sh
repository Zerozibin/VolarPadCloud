#!/bin/bash

SpringBootJarCount=`find *.jar|wc -l`

server=`find *.jar`

if [ "$SpringBootJarCount" -gt 1 ];
then
echo -e "\033[0;31m 检测到重复的应用\n $server \033[0m"
    exit 1
fi

if [ "$server" = "" ];
then
    echo -e "\033[0;31m 未检测到可启动的应用 \033[0m"
    exit 1
fi

echo "Stop $server"
#    curl -X POST -i http://localhost:8080/shutdown
boot_id=`ps -ef |grep java|grep $server|grep -v grep|awk '{print $2}'`
count=`ps -ef |grep java|grep $server|grep -v grep|wc -l`

if [ $count != 0 ];then
        kill $boot_id
        count=`ps -ef |grep java|grep $server|grep -v grep|wc -l`

        boot_id=`ps -ef |grep java|grep $server|grep -v grep|awk '{print $2}'`
        kill -9 $boot_id
fi
