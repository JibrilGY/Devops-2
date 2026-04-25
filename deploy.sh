#!/bin/bash
cd ~/deploy_project
docker-compose down
docker-compose pull
docker-compose up -d