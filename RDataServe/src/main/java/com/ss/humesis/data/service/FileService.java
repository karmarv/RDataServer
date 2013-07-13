package com.ss.humesis.data.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.ss.humesis.entity.FileObj;

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
	
	@Resource(name="gridFsTemplate")
	private GridFsOperations gridFsTemplate;

	 
    public GridFSDBFile getFileById(String id) {
    	Query query = new Query();
		query.addCriteria(Criteria.where("fileId").exists(true).
			   orOperator(Criteria.where("fileId").is(id))
			   );
    	return gridFsTemplate.findOne(query);
    }
 
    
    public GridFSDBFile getFileByName(String filename) {
    	Query query = new Query();
		query.addCriteria(Criteria.where("fileName").exists(true).
			   orOperator(Criteria.where("fileName").is(filename))
			   );
    	return gridFsTemplate.findOne(query);
    }
    
	/**
	 * Retrieves all Files
	 */
	public List<FileObj> getAll() {
		logger.info("Retrieving all Files");
		// Find an entry where fileid property exists
		// Execute the query and find all matching entries
		List<GridFSDBFile> result=null;
		List<FileObj> files=null;
		try {
			Query query =  new Query();
			//files = mongoTemplate.find(query, FileObj.class,"files");
			result = gridFsTemplate.find(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result != null && result.size()>0){
			files = new ArrayList<FileObj>();	 
			for (GridFSDBFile file : result) {
				logger.info(file.getMetaData().toString());
				System.out.println(file.getContentType());
				
				files.add(new FileObj(file.getId().toString(), file.getFilename(),
						file.getLength(), file.getMetaData().get("fileType").toString(),
						Integer.parseInt(file.getMetaData().get("fileVersion").toString())));
			}
		}
		
		return files;
	}

	/**
	 * Retrieves a single File
	 */
	public List<FileObj> get( String id ) {
		logger.info("Retrieving an existing File id: "+id);
		GridFSDBFile result=null;
		List<FileObj> files= new ArrayList<FileObj>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").exists(true).
				   orOperator(Criteria.where("_id").is(new ObjectId(id)))
				   );
			result = gridFsTemplate.findOne(query);
			if(result != null)	
				files.add(new FileObj(result.getId().toString(), result.getFilename(),
					result.getLength(), result.getMetaData().get("fileType").toString(),
					Integer.parseInt(result.getMetaData().get("fileVersion").toString())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	/**
	 * Adds a new File to the files collection of MongoDB
	 */
	public String add(MultipartFile file, String fileId) {
		logger.info("Adding a new file, "+file.getOriginalFilename());
		InputStream inputStream=null;
		GridFSFile gfile=null;
		DBObject metaData = new BasicDBObject();
		
		try {
			//mongoTemplate.insert(file,"files");
			//return true;
		    inputStream = file.getInputStream();
		    metaData.put("fileId", fileId);
	        metaData.put("fileType", file.getContentType());
	        metaData.put("fileName", file.getOriginalFilename());
	        metaData.put("fileSize", file.getSize());
	        metaData.put("fileVersion",1);
	        gfile = gridFsTemplate.store(inputStream,file.getOriginalFilename(), metaData);
	        
		} catch (FileNotFoundException e) {
			logger.error("An error has occurred while trying to add new file", e);
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new file", e);
			return null;
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("An error has occurred while trying close file", e);
					e.printStackTrace();
				}
			}
		}
		return gfile.getId().toString();
	} 

	/**
	 * Deletes an existing File from collection files
	 */
	public Boolean delete(String id) {
		logger.info("Deleting existing File : "+id);
		try {
			// IFind an entry where filed matches the id
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").exists(true).
				   orOperator(Criteria.where("_id").is(new ObjectId(id)))
				   );
	    	gridFsTemplate.delete(query);
	    	
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}

	/** Edits an existing File
	 * 
	 * TODO : The logic needs to see whether we really need to update
	 * or delete and create another entry 
	 * 
	 */
	public Boolean edit(FileObj file) {
		logger.info("Editing existing File");
		logger.info(file.toString());
		// Find an entry where pid matches the id
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true).
			   orOperator(Criteria.where("_id").is(new ObjectId(file.getFileId())))
			   );
		GridFSDBFile fileFS = gridFsTemplate.findOne(query);
		
		InputStream inputStream=null;
		GridFSFile gfile=null;
		DBObject metaData = new BasicDBObject();
			
		try{
			if(fileFS == null || fileFS.getFilename().isEmpty())
				return false;
			
			inputStream = fileFS.getInputStream();
			metaData.put("fileId", file.getFileId());
			metaData.put("fileType", file.getFileType());
			metaData.put("fileName", file.getFileName());
			metaData.put("fileSize", file.getFileSize());
			metaData.put("fileVersion",file.getFileVersionId()+1);
			gfile = gridFsTemplate.store(inputStream,file.getFileName(), metaData);
			//Remove this object from the files db
			delete(file.getFileId());
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new file", e);
			return false;
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("An error has occurred while trying close file", e);
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
