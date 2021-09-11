@echo off

title Kafka Console Consumer

call set-environment

pushd kafka

call bin\windows\kafka-console-consumer ^
	--bootstrap-server localhost:%PORT_KAFKA% ^
	--topic newsletter.readers ^
	--from-beginning

popd

pause