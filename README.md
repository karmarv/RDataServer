RDataServer
===========

Hello draft of the Mongo DB based file data store service


GIT USAGE 
=========

There are two more branches apart from the master branch which have the file upload functionality

1. Checkout the branch 

  * git clone https://github.com/vishwakarmarhl/RDataServer  
  
  * Branch A  
   git checkout -b FileUploadGridFSSpring origin/FileUploadGridFSSpring  
  * Branch B  
   git checkout -b MultiPartFileUpload origin/MultiPartFileUpload  

2. Pull the current branch  
   git pull  

3. After making changes in any of them index and commit  
   git add .  
   git commit -m "Updated the code and added a message"  

4. Push changes to the github repository  
   git push origin FileUploadGridFSSpring  
   git push origin MultiPartFileUpload  


SETUP & START MONGO DB WIN x64
==============================

Download : http://www.mongodb.org/dr/downloads.mongodb.org/win32/mongodb-win32-x86_64-2.4.5.zip/download

1. Unzip the zip contents in C:\mongodb\
2. create C:\data\db
3. Execute the C:\mongodb\bin\mongod.exe  --dbpath C:\data\db

CREATE SAMPLE DATA
==================

//The following command simply pre-allocates a 2 gigabyte, uncapped collection named people.
db.createCollection("files", { size: 2147483648 })
db.files.save(
{
	 fileId: '1235',
     fileName: 'V_XXX.EXE',
     filePath: '/opt/storage/rhldata',
     fileSizeInKB: 123342,
	 fileExtensionType: 'EXE'
})

PROJECT USAGE
=============

1. Start the mongod.exe standalone server
2. Import the source as a maven project in Eclipse STS IDE
2. MVN PACKAGE
3. Deploy on a tomcat instance to see the data from mongodb
4. An alternate plugin for tomcat enables maven based initialization.
	mvn tomcat7:run
5. Open up http://localhost:8088/RDataServe to checkout the grid for file data 


APPENDIX/REFERENCE
==================
Mongo Shell Cmds

show dbs
show collections

//This command creates a collection named file with a maximum size of 5 megabytes and a maximum of 5000 documents.
db.createCollection("files", { capped : true, size : 5242880, max : 5000 } )

//The following command simply pre-allocates a 2 gigabyte, uncapped collection named people.
db.createCollection("files", { size: 2147483648 })

//Drop a collection capped
db.files.drop()

//Insert
db.files.insert(
  {
     _id: 1,
	 fileId: 1234,
     fileName: 'R_XXX.EXE',
     filePath: '/opt/storage/rhldata',
     fileSizeInKB: 123412,
	 fileExtensionType: 'EXE'
	})
	
db.files.save(
	{
	 fileId: '1235',
     fileName: 'V_XXX.EXE',
     filePath: '/opt/storage/rhldata',
     fileSizeInKB: 123342,
	 fileExtensionType: 'EXE'
	})

//Query	
db.files.find({fileId:1234})
