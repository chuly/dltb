package com.bbq.dltb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class DemoService {
	private static final Logger log = LoggerFactory.getLogger(DemoService.class);

	public List<String> queryAllUser() {
		log.info("查询所有用户");
		List<String> list = Lists.newArrayList();
		list.add("demo test ");
		return list;
	}


}
