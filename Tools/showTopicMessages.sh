#!/bin/bash

cd kafka

source ../setEnvironment.sh

bin/kafka-console-consumer.sh \
	--bootstrap-server localhost:$PORT_KAFKA \
	--topic newsletter.readers \
	--from-beginning