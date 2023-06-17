#!/bin/sh
# ./ry.sh start 启动 stop 停止 restart 重启 status 状态 bak备份 log查看日志
APP_HOME=`pwd`
AppName=personalKit-0.0.1-SNAPSHOT.jar
#获取当前时间
Suffix=".bak-"`date '+%Y%m%d_%H:%M:%S'`;
# 配置备份路径
BakName="baks";
# 日志文件名称
LogsName="logs"
#获取旧的jar包名称，当然可能是空的，也可能跟当前名称一致
# JVM参数
JVM_OPTS="-Dname=$AppName -Dfile.encoding=UTF-8 -Dspring.profiles.active=pro"

LOG_PATH=$APP_HOME/$LogsName/$AppName.log

if [ "$1" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$AppName" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi

function start()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`

	if [ x"$PID" != x"" ]; then
	    echo "$AppName is running..."
	else
		nohup java $JVM_OPTS -jar $AppName > $LOG_PATH 2>&1 &
		echo "Start $AppName success..."
	fi
}
function bak()
{
	echo "Start $AppName"
	mv $AppName $APP_HOME/$BakName/$AppName$Suffix
	echo "Bak $AppName $APP_HOME/$BakName/$AppName$Suffix successful"
}

function log()
{
	tail -f $LOG_PATH
}

function stop()
{
    echo "Stop $AppName"

	PID=""
	query(){
		PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`
	}

	query
	if [ x"$PID" != x"" ]; then
		kill -TERM $PID
		echo "$AppName (pid:$PID) exiting..."
		while [ x"$PID" != x"" ]
		do
			sleep 1
			query
		done
		echo "$AppName exited."
	else
		echo "$AppName already stopped."
	fi
}

function restart()
{
    stop
    sleep 2
    start
}

function status()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|wc -l`
    if [ $PID != 0 ];then
        echo "$AppName is running..."
    else
        echo "$AppName is not running..."
    fi
}

case $1 in
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    bak)
    bak;;
    log)
    log;;
    *)

esac