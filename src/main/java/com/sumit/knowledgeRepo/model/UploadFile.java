package com.sumit.knowledgeRepo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sumit.knowledgeRepo.pojo.UploadFilePojo;

@Entity
public class UploadFile {

	@Id
	@GeneratedValue
	private Integer id;

	private String fileName;

	private String fileDownloadUri;

	private String fileType;

	private long size;

	public UploadFile(UploadFilePojo uploadFilePojo) {
		super();
		this.fileName = uploadFilePojo.getFileName();
		this.fileDownloadUri = uploadFilePojo.getFileDownloadUri();
		this.fileType = uploadFilePojo.getFileType();
		this.size = uploadFilePojo.getSize();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "UploadFile [id=" + id + ", fileName=" + fileName + ", fileDownloadUri=" + fileDownloadUri
				+ ", fileType=" + fileType + ", size=" + size + "]";
	}

}
