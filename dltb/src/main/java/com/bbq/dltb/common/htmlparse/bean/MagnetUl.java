package com.bbq.dltb.common.htmlparse.bean;

import java.util.List;

public class MagnetUl {
	private List<Magnet> magnetList; // 列表
	private int totlePage;// 总页数

	public List<Magnet> getMagnetList() {
		return magnetList;
	}

	public void setMagnetList(List<Magnet> magnetList) {
		this.magnetList = magnetList;
	}

	public int getTotlePage() {
		return totlePage;
	}

	public void setTotlePage(int totlePage) {
		this.totlePage = totlePage;
	}

}
