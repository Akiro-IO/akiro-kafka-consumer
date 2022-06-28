#!/bin/bash

JAVA_EXECUTABLE="`which java`"

server=$1
topic=$2
offsetConfig=$3
pollInterval=$4

$JAVA_EXECUTABLE -cp SampleKafkaConsumer.jar com.akiro.SampleKafkaConsumer $server $topic $offsetConfig $pollInterval