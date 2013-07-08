package com.ss.humesis.entity;

import java.io.Serializable;

/**
 * File Info POJO Class
 * @author Rahul Vishwakarma
 *
 */
public class FileMeta implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public FileMeta() {	
	}
	
	/**
	 * @param fileId
	 * @param fileName
	 * @param filePath
	 * @param fileSize
	 * @param fileType
	 */
	public FileMeta(String fileId, String fileName, String filePath,
			double fileSize, String fileType) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}	
	String fileId;
	String fileName;
	String filePath;
	double fileSize;
	String fileType;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", fileSizeInKB=" + fileSize
				+ ", fileExtensionType=" + fileType + "]";
	}
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileSize
	 */
	public double getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}	
}
