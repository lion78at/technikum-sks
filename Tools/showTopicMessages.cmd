@echo off

title Kafka Console Consumer

pushd kafka

call ..\setEnvironment.cmd

call bin\windows\kafka-console-consumer.bat ^
	--bootstrap-server localhost:%PORT_KAFKA% ^
	--topic newsletter.readers ^
	--from-beginning

popd

pause