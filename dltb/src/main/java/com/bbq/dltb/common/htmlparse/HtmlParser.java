package com.bbq.dltb.common.htmlparse;

import com.bbq.dltb.common.htmlparse.bean.MagnetDetail;
import com.bbq.dltb.common.htmlparse.bean.MagnetUl;

/**
 * 解析html内容
 * @author chuly
 *
 */
public interface HtmlParser {
	MagnetUl parseList(String html);
	
	MagnetDetail parseDetail(String html);
}
