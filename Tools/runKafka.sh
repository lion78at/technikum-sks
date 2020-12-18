#!/bin/bash

cd kafka

source ../setEnvironment.sh

bin/kafka-server-start.sh \
	config/server.properties \
	--override listeners=PLAINTEXT://:$PORT_KAFKA \
	--override zookeeper.connect=localhost:$PORT_ZOOKEEPER \
	--override log.dirs=data/kafka