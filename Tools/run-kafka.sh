#!/bin/bash

source set-environment.sh

cd kafka

bin/kafka-server-start.sh \
	config/server.properties \
	--override zookeeper.connect=localhost:$PORT_ZOOKEEPER \
	--override listeners=PLAINTEXT://:$PORT_KAFKA \
	--override log.dirs=data/kafka