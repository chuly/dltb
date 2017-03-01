package com.bbq.dltb.common.htmlparse.bean;

public class MagnetDetail {
	private String name;// 资源名
	private String createDate;//创建一起
	private String fileSize;//文件大小
	private String linkSpeed;//链接速度
	private String aliveDate;//活跃日期
	private String fileNumber;//文件数
	private String seedHash;//种子哈希
	private String hrefUrl;//下载地址

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getLinkSpeed() {
		return linkSpeed;
	}

	public void setLinkSpeed(String linkSpeed) {
		this.linkSpeed = linkSpeed;
	}

	public String getAliveDate() {
		return aliveDate;
	}

	public void setAliveDate(String aliveDate) {
		this.aliveDate = aliveDate;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getSeedHash() {
		return seedHash;
	}

	public void setSeedHash(String seedHash) {
		this.seedHash = seedHash;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

}
