package com.bbq.dltb.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bbq.dltb.common.util.QiniuDBUtil;
import com.bbq.dltb.model.ResConfig;

@Service
public class ResService {
	private static final Logger log = LoggerFactory.getLogger(ResService.class);
	
	private static final Map<String, ResConfig> resMap = new HashMap();
	static {
		resMap.put("taobao-2600kcxsy", new ResConfig("taobao-2600kcxsy", "qiniu","2600款促销水印.rar", false));
		resMap.put("120x80_png", new ResConfig("120x80_png", "qiniu","120成80.png", false));
		resMap.put("test120x80_png", new ResConfig("test120x80_png", "qiniu","test120成80.png", false));
	}

	public String downloadRes(String resCode) {
		log.info("下载资源resCode:"+resCode);
		String url = null;
		ResConfig rc = resMap.get(resCode);
//		if("taobao-2600kcxsy".equals(resCode)){
//			return "http://ohciv5ysw.bkt.clouddn.com/2600%E6%AC%BE%E4%BF%83%E9%94%80%E6%B0%B4%E5%8D%B0.rar";
//		}
		if(rc != null){
			if("qiniu".equals(rc.getDbType())){
				if(rc.isPublic()){
					url = QiniuDBUtil.createPublicUrllk(rc.getFileName());
				}else{
					url = QiniuDBUtil.createPrivateUrllk(rc.getFileName(), 10L);
				}
			}
		}
		log.debug("url:"+url);
		return url;
	}
	
	public String getResName(String resCode){
		log.info("资源文件名 resCode:"+resCode);
		String fileName = "";
		ResConfig rc = resMap.get(resCode);
		if(rc != null){
			fileName = rc.getFileName();
		}
		return fileName;
	}


}
