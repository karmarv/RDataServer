/**
 * 
 */
package com.ss.humesis.test.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ss.humesis.data.service.FileService;
import com.ss.humesis.domain.File;

/**
 * @author Rahul Vishwakarma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class ServiceTest {

	protected static Logger logger = LoggerFactory.getLogger(ServiceTest.class.getName());
	
	@Autowired
    private ApplicationContext applicationContext;

	@Autowired
	private FileService fileService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ss.humesis.data.service.FileService#getAll()}.
	 */
	@Test
	public void testGetAll() {
		
		try {
			List<File> files = fileService.getAll();
			for(File f : files){
				logger.info("File: "+ f.getFileName());	
			}
			logger.info("Size files list, "+files.size());
			assertTrue(files.size()>1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.ss.humesis.data.service.FileService#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ss.humesis.data.service.FileService#add(com.ss.humesis.domain.File)}.
	 */
	@Test
	public void testAdd() {
		//Create a file object
		File file = new File();
		file.setFileId("1231");
		file.setFileName("ABC.ADA");
		file.setFilePath("/opt/maths/program");
		file.setFileSizeInKB(112342);
		
		Boolean status = fileService.add(file);
		assertTrue(status);
	}

	/**
	 * Test method for {@link com.ss.humesis.data.service.FileService#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.ss.humesis.data.service.FileService#edit(com.ss.humesis.domain.File)}.
	 */
	@Test
	public void testEdit() {
		fail("Not yet implemented"); // TODO
	}

}
