#!/bin/bash

cd kafka

source ../setEnvironment.sh

export KAFKA_OPTS=-Dzookeeper.admin.enableServer=false

bin/zookeeper-server-start.sh \
	$PORT_ZOOKEEPER \
	data/zookeeper