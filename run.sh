#!/bin/bash
if [ "$1" == "postgres" ]
then
echo "Deploying application with postgres"
kubectl apply -f postgressql
else
echo "Deploying application with mssql"
kubectl apply -f mssql
fi
