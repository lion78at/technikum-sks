@echo off

title Kafka

call set-environment

pushd kafka

call bin\windows\kafka-server-start ^
	config\server.properties ^
	--override zookeeper.connect=localhost:%PORT_ZOOKEEPER% ^
	--override listeners=PLAINTEXT://:%PORT_KAFKA% ^
	--override log.dirs=data\kafka

popd

pause