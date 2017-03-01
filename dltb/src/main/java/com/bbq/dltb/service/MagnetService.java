package com.bbq.dltb.service;

import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bbq.dltb.common.htmlparse.CiliSiteType;
import com.bbq.dltb.common.htmlparse.HtmlParser;
import com.bbq.dltb.common.htmlparse.bean.Magnet;
import com.bbq.dltb.common.htmlparse.bean.MagnetDetail;
import com.bbq.dltb.common.htmlparse.bean.MagnetUl;
import com.bbq.dltb.common.htmlparse.impl.BTMilkHtmlParser;
import com.bbq.dltb.common.htmlparse.impl.CiliwanHtmlParser;
import com.bbq.dltb.common.util.HttpClientUtil;
import com.google.common.collect.Lists;

@Service
public class MagnetService {
	private static final Logger log = LoggerFactory.getLogger(MagnetService.class);

	public MagnetUl search(String keyWord, int type, int page) {
		log.info("磁力搜索 keyWord:" + keyWord + ",type=" + type + ",page=" + page);
		MagnetUl ret = new MagnetUl();
		if (type == CiliSiteType.type_1_btmilk) {
			HtmlParser hp = new BTMilkHtmlParser();
			try {
				String html = HttpClientUtil.execGet("http://www.btmilk.com/index.php/search?keyword="
						+ URLEncoder.encode(keyWord, "utf-8")+"&page="+page);
				ret = hp.parseList(html);
			} catch (Exception e) {
				log.error("访问btmilk出错", e);
				return ret;
			}
			
		} else if (type == CiliSiteType.type_2_ciliwam) {
			HtmlParser hp = new CiliwanHtmlParser();
		}
		log.info("结果页数："+ret.getTotlePage());
		return ret;
	}

	public MagnetDetail detail(String detailCode, int type) {
		log.info("磁力资源明细 detailCode:" + detailCode);
		MagnetDetail md = null;
		if (type == CiliSiteType.type_1_btmilk) {
			String html;
			try {
				html = HttpClientUtil.execGet("http://www.btmilk.com/index.php/detail/"+URLEncoder.encode(detailCode, "utf-8"));
			} catch (Exception e) {
				log.error("访问btmilk(明细)出错", e);
				return null;
			}
			System.out.println(html);
			BTMilkHtmlParser pa = new BTMilkHtmlParser();
			md = pa.parseDetail(html);
			
		} else if (type == CiliSiteType.type_2_ciliwam) {
			HtmlParser hp = new CiliwanHtmlParser();
		}
		
		return md;
	}

}
