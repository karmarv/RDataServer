package com.ss.humesis.data.controller;

/**
 * Handles and retrieves file request
 * @author Rahul Vishwakarma
 *
 */

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.humesis.data.service.FileService;
import com.ss.humesis.domain.File;

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
	public String getPersons(Model model) {

		logger.info("Received request to show all files");

		// Retrieve all persons by delegating the call to PersonService
		List<File> files = fileService.getAll();

		logger.info("Size files list, "+files.size());
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
		model.addAttribute("fileAttribute", new File());

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
	public String add(@ModelAttribute("fileAttribute") File file) {

		logger.info("Received request to add new person: " + file.toString());

		// The "fileAttribute" model has been passed to the controller from the JSP
		// We use the name "fileAttribute" because the JSP uses that name

		// Call FileService to do the actual adding
		fileService.add(file);

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
	public String saveEdit(@ModelAttribute("fileAttribute") File file,
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