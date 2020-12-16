@echo off

title Kafka

pushd kafka

call ..\setEnvironment.cmd

call bin\windows\kafka-server-start.bat ^
	config\server.properties ^
	--override listeners=PLAINTEXT://:%PORT_KAFKA% ^
	--override zookeeper.connect=localhost:%PORT_ZOOKEEPER% ^
	--override log.dirs=data\kafka

popd

pause