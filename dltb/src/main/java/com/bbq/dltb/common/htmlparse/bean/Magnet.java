package com.bbq.dltb.common.htmlparse.bean;

public class Magnet {
	private String detailCode; //明细代码
	private String name;//资源名
	private String fileSize;//文件大小
	private String dateStr;//日期
	private int oriType;//资源来源

	public int getOriType() {
		return oriType;
	}

	public void setOriType(int oriType) {
		this.oriType = oriType;
	}

	public String getName() {
		return name;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
