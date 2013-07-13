RDataServer
===========

Prototype of the Mongo DB based data store service
This project utilizes the GridFS API to store large datasets in the the MongoDB server instance.

SETUP & START MONGO DB WIN x64
==============================

Download : http://www.mongodb.org/dr/downloads.mongodb.org/win32/mongodb-win32-x86_64-2.4.5.zip/download

1. Unzip the zip contents in C:\mongodb\
2. create C:\data\db
3. Execute the C:\mongodb\bin\mongod.exe


PROJECT USAGE
=============

1. Start the mongod.exe standalone server
2. Import the source as a maven project in Eclipse STS IDE
2. MVN PACKAGE
3. Deploy on a tomcat instance to see the data from mongodb
