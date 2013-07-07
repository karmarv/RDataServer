package com.ss.humesis.data.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.humesis.domain.File;

/**
 * Service for processing {@link file} objects.
 * Uses Spring's {@link MongoTemplate} to perform CRUD operations.
 * <p>
 * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 * <p>
 * For a complete reference to Spring Data MongoDB
 * see http://www.springsource.org/spring-data
 *
 * @author Rahul Vishwakarma
 */
@Service("fileService")
public class FileService {

	public FileService() {
		// TODO Auto-generated constructor stub
	}

	protected static Logger logger = LoggerFactory.getLogger(FileService.class.getName());

	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;

	/**
	 * Retrieves all Files
	 */
	public List<File> getAll() {
		logger.info("Retrieving all Files");

		// Find an entry where fileid property exists
		// Execute the query and find all matching entries
		List<File> files=null;
		try {
			Query query = new Query(Criteria.where("fileId").exists(true));
			files = mongoTemplate.find(query, File.class,"files");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return files;
	}

	/**
	 * Retrieves a single File
	 */
	public List<File> get( String id ) {
		logger.info("Retrieving an existing File");
		List<File> files=null;
		try{
			// Find an entry where pid matches the id
			Query query = new Query(Criteria.where("fileId").is(id));
			// Execute the query and find one matching entry
			files = mongoTemplate.find( query, File.class,"files");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return files;
	}

	/**
	 * Adds a new File
	 */
	public Boolean add(File file) {
		logger.info("Adding a new user");

		try {
			// Set a new value to the pid property first since it's blank
			file.setFileId(UUID.randomUUID().toString());
			// Insert to db
			mongoTemplate.insert(file,"files");

			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new file", e);
			return false;
		}
	}

	/**
	 * Deletes an existing File
	 */
	public Boolean delete(String id) {
		logger.info("Deleting existing File");

		try {
			File file = new File();
			file.setFileId(id);
			// Find an entry where pid matches the id
			Query query = new Query(Criteria.where("fileId").is(file.getFileId()));
			// Run the query and delete the entry
			mongoTemplate.remove(query);
			
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}

	/**
	 * Edits an existing File
	 */
	public Boolean edit(File file) {
		logger.info("Editing existing File");

		try {
			// Find an entry where pid matches the id
			Query query = new Query(Criteria.where("fileId").is(file.getFileId()));

			// Declare an Update object. 
			// This matches the update modifiers available in MongoDB
			Update update = new Update();

			update.set("fileName", file.getFileName());
			update.set("fileId", file.getFileId());
			update.set("filePath", file.getFilePath());
			update.set("fileSizeInKB", file.getFileSizeInKB());
			update.set("fileExtensionType", file.getFileExtensionType());
			
			mongoTemplate.updateMulti(query, update,"files");

			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing user", e);
			return false;
		}

	}

}
