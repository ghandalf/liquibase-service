#!/bin/bash

command=$1
current_dir=`pwd`
working_dir=..
env=
dist_dir=~/usr/local/workspace/test

function usage() {
		echo 'usage: ./service.sh <parameter>';
		echo '  Where parameters are:';
		echo '    assembly -> will assembly the application for distribution';
		echo '    build -> will build the application without executing the test suite';
		echo '    bt -> will execute the test under business package';
		echo '    dist -> will copy from target the tar.gz to ~/usr/local/workspace/test/ unzip, ready to use';
		echo '    ds -> will give the list of containers in docker';
		echo '    dstart -> will will start the database in docker';
		echo '    eclipse -> will make the project accesible by Eclipse';
		echo '    lu -> will use mvn and liquibase to execute the sql statements need by the project';
		echo '    lus -> will print the sql execute by Liquibase under target\liquibase\migrate.sql';
		echo '    lr -> will use mvn and liquibase to return to the previous tag for the database';
		echo '    run -> will run the application';
		echo '    test -> will execute all the tests'; 
		echo '    start -> will start the application on the distribution path';
}

function businessTests() {
	echo "--------- Business tests started --------";
	cd ${working_dir}
	mvn -Dtest=com.fresche.tutorial.liquibase.business.*Test test
	cd ${current_dir}
	echo "--------- Business tests finished --------";
}

function test() {
	echo "--------- All Tests started --------";
	cd ${working_dir}
	mvn test
	cd ${current_dir}
	echo "--------- All Tests finished --------";
}

function build() {
	echo "--------- Building Liquibase Service application --------";
	cd ${working_dir}
	mvn clean install -DskipTests
	cd ${current_dir}
	echo "--------- Build done --------";
}

function assembly() {
	echo "--------- Assemble the application --------";
	cd ${working_dir}
	mvn clean 
	mvn package -DskipTests assembly:single
	cd ${current_dir}
	echo "--------- Assemble done --------";
}

function eclipse() {
echo "--------- Apply Eclipse configuration --------";
	cd ${working_dir}
	mvn eclipse:eclipse
	cd ${current_dir}
echo "--------- Configuration done --------";
}

###
# https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
##
function run() {
	echo "--------- Starting Liquibase Service application in [${env}] --------";
	if [ "$env" == "dev" ]; then
		cd ${working_dir}
		java -jar target/liquibase-service.jar
		cd ${current_dir}
	else
		java -jar liquibase-service.jar
	fi
	echo "--------- Stopping Liquibase Service application --------";
}

function distribute() {
	assembly
	if [ -d ${dist_dir}/liquibase-service ]; then
		echo "Clean up" 
		rm -rf ${dist_dir}/liquibase-service
		rm -rf ${dist_dir}/liquibase-service-distribution.tar.gz
	fi
	cp ../target/liquibase-service-distribution.tar.gz ${dist_dir}
	ls -la ${dist_dir}
	cd ${dist_dir}
	tar -xvf liquibase-service-distribution.tar.gz
	ls -la liquibase-service
	cd ${current_dir}
}

function start() {
	if [ -d ${dist_dir}/liquibase-service ]; then
		cd 	${dist_dir}/liquibase-service/bin
		java -jar liquibase-service.jar
		cd ${current_dir}
	else 
		java -jar target\liquibase-service.jar
	fi
}

## Test
function checkTarget() {
	if [ -d "../target" ]; then
		env=dev
	else
		env=prod
	fi
}

function dockerInit() {
	docker pull sath89/oracle-12c
	docker run -d -p 8021:8080 -p 1521:1521 --name rmidevdb -v /c/FrescheProjects/OracleDataDocker:/u01/app/oracle sath89/oracle-12c
	docker stop rmidevdb
	docker start -i rmidevdb
	
	# Kitematic
	# C:\Users\Ouell\usr\local\programs\kitematic\Kitematic.exe
	
}

function liquibase() {
	cd ${working_dir}
	case $1 in 
		rollback)
			mvn liquibase:rollback -Dliquibase.rollbackTag=$2
			;;
		update|updateSQL)
			mvn liquibase:$1
			;;
		*)
			usage
			;;
	esac
	cd ${current_dir}
}

checkTarget
case ${command} in
	assembly)
		assembly
		;;
	bt)
		businessTests
		;;
	build)
		build
		;;
	dist) 
		distribute 
		;;
	ds) 
		docker ps 
		;;
	dstart)
		docker start rmidevdb
		;;
	eclipse)
		eclipse
		;;
	lu)	
		liquibase update 
		;;
	lus) 
		liquibase updateSQL 
		;;
	lr)	
		liquibase rollback $2 
		;;	
	run)
		run
		;;
	start) 
		start 
		;;
	test)
		test
		;;
	*) 
		usage 
		;;
	
esac