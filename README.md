RDataServer
===========

Hello draft of the Mongo DB based data store service


SETUP & START MONGO DB WIN x64
==============================

Download : http://www.mongodb.org/dr/downloads.mongodb.org/win32/mongodb-win32-x86_64-2.4.5.zip/download

1. Unzip the zip contents in C:\mongodb\
2. create C:\data\db
3. Execute the C:\mongodb\bin\mongod.exe

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
