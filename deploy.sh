#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=freelec-springboot-kyoohyung-webservice

echo ">cd $PROJECT_NAME"
echo "cd $REPOSITORY/$PROJECT_NAME" >> /home/ec2-user/app/step1/deploy.log
cd $REPOSITORY/$PROJECT_NAME

echo ">Git pull"
git pull

echo ">기존 *.jar 제거"
echo "rm -f $REPOSITORY/$PROJECT_NAME/build/libs/*.jar" >> /home/ec2-user/app/step1/deploy.log
rm -f $REPOSITORY/$PROJECT_NAME/build/libs/*.jar

echo ">프로젝트 build 시작"
echo "./gradlew build"  >> /home/ec2-user/app/step1/deploy.log
./gradlew build

echo ">setp1 디렉토리로 이동"
echo "cd $REPOSITORY"  >> /home/ec2-user/app/step1/deploy.log
cd $REPOSITORY

echo "> build 파일 복사"
echo "cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/" >> /home/ec2-user/app/step1/deploy.log
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"
echo "CURRENT_PID=$(pgrep -fl ${PROJECT_NAME}*.jar)" >> /home/ec2-user/app/step1/deploy.log
CURRENT_PID=$(pgrep -fl ${PROJECT_NAME}*.jar)

echo "현재 구동중인 애플리케이션 pid : $CURRENT_PID"
echo "현재 구동중인 애플리케이션 pid : $CURRENT_PID" >> /home/ec2-user/app/step1/deploy.log

if [ -z "$CURRENT_PID"] ; then
        echo "> 현재 구동 중인 애플리케이션이 없음으로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi


echo "> 새 어플리케이션 배포"
echo "새 어플리케이션 배포" >> /home/ec2-user/app/step1/deploy.log

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)" >> /home/ec2-user/app/step1/deploy.log

echo "> JAR Name: $JAR_NAME"
echo " JAR Name: $JAR_NAME"  >> /home/ec2-user/app/step1/deploy.log

echo "> $JAR_NAME 에 실행권한 추가"
echo " chmod +x $JAR_NAME" >> /home/ec2-user/app/step1/deploy.log
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
echo " nohup java -jar $JAR_NAME 2>&1 &" >>  /home/ec2-user/app/step1/deploy.log

# oauth를 사용하지 않을때 사용
#nohup java -jar $JAR_NAME 2>&1 &

# 8.3절
nohup java -jar -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties $JAR_NAME 2>&1 &

#
#nohup java -jar \ -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
#    -Dspring.profiles.active=real \
#    $REPOSITORY/$JAR_NAME  2>&1 &

