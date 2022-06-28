#!/bin/bash

PIDS=$(ps ax | grep -i 'SampleKafkaConsumer' | grep java | grep -v grep | awk '{print $1}')

if [ -z "$PIDS" ]; then
  echo "No Kafka Consumer to stop"
  exit 1
else
  kill -s TERM $PIDS
  echo "Stopped Kafka Consumer"
fi
