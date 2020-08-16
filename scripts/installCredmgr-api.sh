#! /bin/bash

echo "Started Install Script "

if [[ -e ./vars.sh ]];then
	source ./vars.sh
else
	echo "Cannot find vars.sh"
	exit -1
fi


alreadyRunning=$(ps -ef -u skrao| grep  "server.port=${port}" | grep -v 'grep' | awk {'print$2'})

if [[  alreadyRunning -gt 0  ]]; then
	kill -9 $alreadyRunning
	echo "Found $alreadyRunning, Killed it"
else
	echo "No Existing Processes Found "
fi


if [[ -d $apiDir ]];then
	echo "Found Existing App installation, Deleting it"
	rm -rf $apiDir
fi


echo "Cloning Repository"

cd $appDir
git clone $repoUrl
cd $apiDir
git checkout $repoBranch


echo "Building api Code"

buildRetCode=$(gradle clean build)

if [[ $? -eq 0 ]]; then
	echo "Build Succeeeded!"
else
	echo "Build Failed, check logs, exiting now"
	exit -1
fi

echo "Starting App"



nohup java -jar ${apiDir}/build/libs/credmgr.api-0.0.1-SNAPSHOT.jar --server.port=${port} &

echo "Finished Install Script"
