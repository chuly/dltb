package com.bbq.dltb.common.htmlparse.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.bbq.dltb.common.htmlparse.CiliSiteType;
import com.bbq.dltb.common.htmlparse.HtmlParser;
import com.bbq.dltb.common.htmlparse.bean.DetailFile;
import com.bbq.dltb.common.htmlparse.bean.Magnet;
import com.bbq.dltb.common.htmlparse.bean.MagnetDetail;
import com.bbq.dltb.common.htmlparse.bean.MagnetUl;
import com.bbq.dltb.common.util.HttpClientUtil;
import com.google.common.collect.Lists;

/**
 * 解析http://www.btmilk.com
 * @author chuly
 *
 */
public class BTMilkHtmlParser implements HtmlParser{
	private static final Logger log = LoggerFactory.getLogger(BTMilkHtmlParser.class);
	
	public static final String guolvStr = "btmilk";
	
	public MagnetUl parseList(String html) {
		MagnetUl mu = new MagnetUl();
		List<Magnet> list = Lists.newArrayList();
		Document doc = Jsoup.parse(html);//connect("http://www.weather.com.cn/html/weather/101280101.shtml").get();
		// 获取目标HTML代码
		Elements elements1 = doc.select("[class=col-md-8]").select("[class=panel panel-default]");
		// 今天
		if(elements1 != null && elements1.size() > 0){
			for (Element element : elements1) {
				try {
					Magnet mb = new Magnet();
//				Elements h5 = element.select("h5");
//				Elements a = h5.select("a");
//				Elements h5 = element.select("h5");
					String name = element.select("h5").select("a").get(0).text();
					Elements tdElements = element.select("td");
					String dateStr = tdElements.get(0).select("span").text();
					String fileSize = tdElements.get(1).select("span").text();
					String detailCode = tdElements.get(3).select("a").attr("href").replace("/index.php/detail/", "");
					mb.setName(name);
					mb.setDateStr(dateStr);
					mb.setFileSize(fileSize);
					mb.setDetailCode(detailCode);
					mb.setOriType(CiliSiteType.type_1_btmilk);
					list.add(mb);
				} catch (Exception e) {
					log.error("磁力信息---列表解析出错", e);
				}
			}
		}
//		Pattern p = Pattern.compile("(totalPages)(.*?)(,)");
		String ss = "totalPages: ";
		String html2 = html.substring(html.indexOf(ss) + ss.length());
	    String totlePageStr = html2.substring(0, html2.indexOf(","));
	    int totlePage = 10;//默认10页
	    try {
			totlePage = Integer.parseInt(totlePageStr);
		} catch (NumberFormatException e) {
			log.error("磁力信息---总页数解析出错，totlePageStr="+totlePageStr, e);
		}
		mu.setTotlePage(totlePage);
		mu.setMagnetList(list);
		return mu;
	}

	public MagnetDetail parseDetail(String html) {
		MagnetDetail md = new MagnetDetail();
		Document doc = Jsoup.parse(html);//connect("http://www.weather.com.cn/html/weather/101280101.shtml").get();
		// 获取目标HTML代码
		Elements elements1 = doc.select("div[class=container]");
		if(elements1 != null && elements1.size() >= 4){
			String name = elements1.get(2).select("h3").text();
			Element container4 = elements1.get(3);
			Elements detailElement = container4.select("div[class=col-md-8]").select("div[class=col-xs-4]");
			String createDate = detailElement.get(0).select("span").text();
			String fileSize = detailElement.get(1).select("span").text();
			String linkSpeed = detailElement.get(2).select("span").text();
			String aliveDate = detailElement.get(3).select("span").text();
			String fileNumber = detailElement.get(4).select("span").text();
			String hrefUrl = container4.select("textarea[class=well magnet center]").text();
			md.setCreateDate(createDate);
			md.setName(name);
			md.setFileSize(fileSize);
			md.setLinkSpeed(linkSpeed);
			md.setAliveDate(aliveDate);
			md.setFileNumber(fileNumber);
			md.setHrefUrl(hrefUrl);
		}
		if(elements1 != null && elements1.size() >= 5){
			List<DetailFile> fileList = Lists.newArrayList();
			Element container5 = elements1.get(4);
			Elements fileListElement = container5.select("table[class=table table-striped]").select("tr");
			if(fileListElement != null && fileListElement.size() > 1){
				int tmpSize = fileListElement.size()-1;
				for (int i = 1; i < tmpSize; i++) {
					Element eachTr = fileListElement.get(i);
					Elements tds  = eachTr.select("td");
					if(tds != null && tds.size() >= 2){
						DetailFile df = new DetailFile();
						String fileName = tds.get(0).text();
						String fileSize = tds.get(1).text();
						df.setFileName(fileName);
						df.setFileSize(fileSize);
						fileList.add(df);
					}
				}
				DetailFile df = new DetailFile();
				df.setFileName("biubiuq影视搜索（www.biubiuq.cn）");
				df.setFileSize("biubiuq.cn");
				fileList.add(df);
			}
			md.setFileList(fileList);
		}
		return md;
	}

	public static void main(String[] args) throws Exception{
//		String html = HttpClientUtil.execGet("http://www.btmilk.com/index.php/search?keyword=%E7%A2%9F%E4%B8%AD%E8%B0%8D");
//		System.out.println(html);
//		BTMilkHtmlParser pa = new BTMilkHtmlParser();
//		List<Magnet> list = pa.parseList(html);
//		System.out.println(JSON.toJSONString(list));
		
		String html = HttpClientUtil.execGet("http://www.btmilk.com/index.php/detail/CuH6mzBbUQQUJSXi");
		System.out.println(html);
		BTMilkHtmlParser pa = new BTMilkHtmlParser();
		MagnetDetail md = pa.parseDetail(html);
		System.out.println(JSON.toJSONString(md));
	}
}
