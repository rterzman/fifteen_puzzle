#!/bin/bash

mvn clean package

"$JAVA_HOME"/bin/java -jar target/15_puzzle-1.0-SNAPSHOT.jar