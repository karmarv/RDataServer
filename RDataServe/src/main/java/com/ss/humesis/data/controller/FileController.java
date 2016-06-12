package com.ss.humesis.data.controller;

/**
 * Handles and retrieves file request
 * @author Rahul Vishwakarma
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ss.humesis.data.service.FileService;
import com.ss.humesis.entity.FileObj;
import com.ss.humesis.model.Files;

@Controller
@RequestMapping("/main")
public class FileController {

	protected static Logger logger = LoggerFactory.getLogger(FileController.class.getName());
	
	@Resource(name="fileService")
	private FileService fileService;

	public FileController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles and retrieves all persons and show it in a JSP page
	 *
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/files", method = RequestMethod.GET)
	public String getFiles(Model model) {

		logger.info("Received request to show all files");

		// Retrieve all persons by delegating the call to PersonService
		List<FileObj> files = fileService.getAll();
		//List<FileObj> files = fileService.getAll();
		logger.info("Size files list, "+files);
		// Attach persons to the Model
		model.addAttribute("files", files);

		// This will resolve to /WEB-INF/jsp/filespage.jsp
		return "filespage";
	}

	/**
	 * Retrieves the add page
	 *
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/files/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		logger.info("Received request to show add page");

		// Create new Person and add to model
		// This is the formBackingOBject
		model.addAttribute("fileAttribute", new Files());

		// This will resolve to /WEB-INF/jsp/addpage.jsp
		return "addpage";
	}

	/**
	 * Adds a new person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 *
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/files/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("fileAttribute") Files fileAttribute, Model map) {

		logger.info("Received request to add new person: " + fileAttribute.getFiles().size());

		//fileMetaService.add(file);
		List<MultipartFile> files = fileAttribute.getFiles();

		List<String> fileNames = new ArrayList<String>();
	    
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) { 
                String fileName = multipartFile.getOriginalFilename();
                if(!fileName.isEmpty() && multipartFile.getSize() > 0){
	                fileNames.add(fileName);
	                String id = UUID.randomUUID().toString();
	                logger.info("Added Files: " +fileName+ ", ID: "+id);
	                //Handle file content - multipartFile.getInputStream()
	                // Add the meta info to the file meta collection
	                //fileMetaService.add(fm);
	                // Add the actual byte buffer to the files collection, Not worrying about version now
				
	                fileService.add(multipartFile,id);
                }
            }
        }     
	    map.addAttribute("files", fileNames);
		// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}

	/**
	 * Deletes an existing person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 *
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/files/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="fileId", required=true) String id,
			Model model) {
		logger.info("Received request to delete existing file");
		// Call PersonService to do the actual deleting
		//fileMetaService.delete(id);
		fileService.delete(id);
		// Add id reference to Model
		model.addAttribute("fileId", id);
		// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}

	/**
	 * Retrieves the edit page
	 *
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/files/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value="fileId", required=true) String id, 
			Model model) {
		logger.info("Received request to show edit page");
		// Retrieve existing Person and add to model
		// This is the formBackingOBject
		model.addAttribute("fileAttribute", fileService.get(id).get(0));
		// This will resolve to /WEB-INF/jsp/editpage.jsp
		return "editpage";
	}

	/**
	 * Edits an existing person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 *
	 * @return  the name of the JSP page
	 */
	@RequestMapping(value = "/files/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("fileAttribute") FileObj file,
			@RequestParam(value="fileId", required=true) String id,
			Model model) {
		logger.info("Received request to update file");
		// The "fileAttribute" model has been passed to the controller from the JSP
		// We use the name "fileAttribute" because the JSP uses that name
		// We manually assign the id because we disabled it in the JSP page
		// When a field is disabled it will not be included in the ModelAttribute
		file.setFileId(id);
		// Delegate to FileService for editing
		fileService.edit(file);
		// Add id reference to Model
		model.addAttribute("fileId", id);
		// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
}