#bin/bash
mvn clean package && docker build -t caioom/pjoias . && docker-compose up
