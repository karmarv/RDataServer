package com.ss.humesis.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ss.humesis.entity.FileMeta;

/**
 * Service for processing {@link file meta} objects.
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
@Service("fileMetaService")
public class FileMetaService {

	protected static Logger logger = LoggerFactory.getLogger(FileMetaService.class.getName());

	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;

	public FileMetaService() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retrieves all Files
	 */
	public List<FileMeta> getAll() {
		logger.info("Retrieving all Files");

		// Find an entry where fileid property exists
		// Execute the query and find all matching entries
		List<FileMeta> files=null;
		try {
			Query query = new Query(Criteria.where("fileId").exists(true));
			files = mongoTemplate.find(query, FileMeta.class,"files");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return files;
	}

	/**
	 * Retrieves a single File
	 */
	public List<FileMeta> get( String id ) {
		logger.info("Retrieving an existing File");
		List<FileMeta> files=null;
		try{
			// Find an entry where pid matches the id
			Query query = new Query(Criteria.where("fileId").is(id));
			// Execute the query and find one matching entry
			files = mongoTemplate.find( query, FileMeta.class,"files");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return files;
	}

	/**
	 * Adds a new File
	 */
	public Boolean add(FileMeta file) {
		logger.info("Adding a new file, "+file.toString());

		try {
			// Set a new value to the pid property first since it's blank
			//file.setFileId(UUID.randomUUID().toString());
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
		logger.info("Deleting existing File : "+id);

		try {
			FileMeta file = new FileMeta();
			file.setFileId(id);
			// Find an entry where pid matches the id
			Query query = new Query();
			query.addCriteria(Criteria.where("fileId").exists(true).
				   orOperator(Criteria.where("fileId").is(file.getFileId()))
				   );
			// Run the query and delete the entry
			mongoTemplate.findAndRemove(query, FileMeta.class,"files");
			
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}

	/**
	 * Edits an existing File
	 */
	public Boolean edit(FileMeta file) {
		logger.info("Editing existing File");

		try {
			logger.info(file.toString());
			// Find an entry where pid matches the id
			Query query = new Query(Criteria.where("fileId").is(file.getFileId()));

			// Declare an Update object. 
			// This matches the update modifiers available in MongoDB
			Update update = new Update();

			update.set("fileName", file.getFileName());
			update.set("fileId", file.getFileId());
			update.set("filePath", file.getFilePath());
			update.set("fileSize", file.getFileSize());
			update.set("fileType", file.getFileType());
			
			mongoTemplate.updateMulti(query, update,"files");

			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing user", e);
			return false;
		}

	}

}
