#!/bin/sh
#author: Yuxuan Wang
 
#Current home
CURR_HOME=$(dirname $(readlink -f $0))

#jvm options
JAVA_OPTS="-Xms1g -Xmx3g -Djava.awt.headless=true -XX:MaxPermSize=512m -server -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=85  -Xnoclassgc -Xverify:none -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled"
JAR_FILE="$CURR_HOME/../job-service-impl.jar"

#CMD="nohup java $JAVA_OPTS -jar $JAR_FILE  &"
CMD="nohup java $JAVA_OPTS -jar $JAR_FILE > /dev/null 2>&1 &"

pid=$(ps -ef | grep deploy-core-sys | grep -v grep | awk '{print $2}')

###################################
#startup
###################################
start() {
   if [ "$pid" != "" ] && [ -d "/proc/$pid" ]; then
      echo "Process has been started with pid: $pid, ignore."
   else
      eval "$CMD"
      pid=$!
      echo "Process started at $pid."
   fi
}
 
###################################
#stop
###################################
stop() {
   if [ "$pid" == "" ] || [ ! -d "/proc/$pid" ]; then
      echo "Process is not running, ignore."
   else
      echo "Trying to killing process $pid gracefully."
      kill -15 $pid > /dev/null 2>&1
      sleep 3
      if [ -d "/proc/$pid" ]; then
         echo "Process still alive, killing it in anger!"
             kill -9 $pid > /dev/null 2>&1
      fi
      echo "Process stopped"
   fi
}
 
###################################
#status
###################################
status() {
   if [ "$pid" != "" ] && [ -d "/proc/$pid" ];  then
      echo "Process is running! (pid=$pid)"
   else
      echo "Process is not running"
   fi
}
 
###################################

###################################
#Accept only 1 argument:{start|stop|restart|status}
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status}"
     exit 1
esac
exit 0
