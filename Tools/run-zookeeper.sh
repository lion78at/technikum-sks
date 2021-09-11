#!/bin/bash

source set-environment.sh

export KAFKA_OPTS=-Dzookeeper.admin.enableServer=false

cd kafka

rm -rf data
rm -rf logs

bin/zookeeper-server-start.sh \
	$PORT_ZOOKEEPER \
	data/zookeeper