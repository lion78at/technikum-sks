@echo off

title Zookeeper

pushd kafka

call ..\setEnvironment.cmd

set KAFKA_OPTS=-Dzookeeper.admin.enableServer=false

call bin\windows\zookeeper-server-start.bat ^
	%PORT_ZOOKEEPER% ^
	data\zookeeper

popd

pause