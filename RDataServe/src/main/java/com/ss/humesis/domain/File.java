package com.ss.humesis.domain;

import java.io.Serializable;

/**
 * File Info POJO Class
 * @author Rahul Vishwakarma
 *
 */
public class File implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public File() {	
	}
	
	public String fileId;
	public String fileName;
	public String filePath;
	public double fileSizeInKB;
	public String fileExtensionType;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public double getFileSizeInKB() {
		return fileSizeInKB;
	}
	public void setFileSizeInKB(double fileSizeInKB) {
		this.fileSizeInKB = fileSizeInKB;
	}
	public String getFileExtensionType() {
		return fileExtensionType;
	}
	public void setFileExtensionType(String fileExtensionType) {
		this.fileExtensionType = fileExtensionType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", fileSizeInKB=" + fileSizeInKB
				+ ", fileExtensionType=" + fileExtensionType + "]";
	}
}
