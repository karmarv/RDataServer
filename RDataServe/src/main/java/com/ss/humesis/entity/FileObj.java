/**
 * 
 */
package com.ss.humesis.entity;


/**
 * @author Rahul Vishwakarma
 *
 */
public class FileObj {

	private String fileId;
	private int fileVersionId;
	private byte[] fileBinary;
	String fileName;
	String filePath;
	double fileSize;
	String fileType;

	public FileObj() {
		// TODO Auto-generated constructor stub
	}	
	
	/**
	 * @param fileId
	 * @param fileName
	 * @param fileSize
	 * @param fileType
	 */
	public FileObj(String fileId, String fileName, double fileSize,
			String fileType,int fileVersion) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.fileVersionId = fileVersion;
	}

	/**
	 * @param fileId
	 * @param fileVersionId
	 * @param fileBinary
	 */
	public FileObj(String fileId, int fileVersionId, byte[] fileBinary) {
		super();
		this.fileId = fileId;
		this.fileVersionId = fileVersionId;
		this.fileBinary = fileBinary;
	}

	/**
	 * @return the fileBinary
	 */
	public byte[] getFileBinary() {
		return fileBinary;
	}

	/**
	 * @param fileBinary the fileBinary to set
	 */
	public void setFileBinary(byte[] fileBinary) {
		this.fileBinary = fileBinary;
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
	 * @return the fileVersionId
	 */
	public int getFileVersionId() {
		return fileVersionId;
	}
	/**
	 * @param fileVersionId the fileVersionId to set
	 */
	public void setFileVersionId(int fileVersionId) {
		this.fileVersionId = fileVersionId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileObj [fileId=" + fileId + ", fileVersionId=" + fileVersionId
				+ ", fileName=" + fileName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + ", fileType=" + fileType + "]";
	}
	
}
