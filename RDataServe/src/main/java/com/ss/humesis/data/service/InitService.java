package com.ss.humesis.data.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ss.humesis.entity.FileObj;

/**
 * Service for initializing MongoDB with sample data
 * <p>
 * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 * <p>
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 * 
 * @author Rahul Vishwakarma
 */
@Service("initService")
public class InitService {

	protected static Logger logger = LoggerFactory.getLogger(InitService.class.getName());
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;

	private void init() {
		// Populate our MongoDB database
		logger.info("Initialize MongoDB users");
		
		// Drop existing collection
		mongoTemplate.dropCollection("files");
		mongoTemplate.createCollection("files", new CollectionOptions(10000000,5000,false));		
		// Create new object
		String id = UUID.randomUUID().toString();
		FileObj f = new FileObj();
		f.setFileId("2324");
		f.setFileName("ABC.BMP");
		f.setFilePath("/opt/storage/maxdata");
		f.setFileSize(23412);
		f.setFileType("BMP");
		
		// Insert to db
	    mongoTemplate.insert(f,"files");
	    
	    f = new FileObj ();
		f.setFileId("3341");
		f.setFileName("DBC.JPG");
		f.setFilePath("/opt/storage/rhldata");
		f.setFileSize(23421);
		f.setFileType("JPG");
		
		// Insert to db
	    mongoTemplate.insert(f,"files");

	    logger.info("Initialization complete");
	}
}
