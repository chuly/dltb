package com.bbq.dltb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ResService {
	private static final Logger log = LoggerFactory.getLogger(ResService.class);

	public String downloadRes(String resCode) {
		log.info("resCode:"+resCode);
		if("taobao-2600kcxsy".equals(resCode)){
			return "http://ohciv5ysw.bkt.clouddn.com/2600%E6%AC%BE%E4%BF%83%E9%94%80%E6%B0%B4%E5%8D%B0.rar";
		}
		return null;
	}


}
