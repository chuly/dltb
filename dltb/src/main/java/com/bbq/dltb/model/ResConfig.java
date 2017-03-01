package com.bbq.dltb.model;

public class ResConfig {

	private Long id;
	private String code;
	private String dbType;//qiniu  aliyun
	private String fileName;
	private boolean isPublic;
	public ResConfig() {
	}

	public ResConfig(String code, String dbType, String fileName,
			boolean isPublic) {
		this.code = code;
		this.dbType = dbType;
		this.fileName = fileName;
		this.isPublic = isPublic;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

}
