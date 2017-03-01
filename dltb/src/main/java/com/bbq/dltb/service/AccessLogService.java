package com.bbq.dltb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bbq.dltb.dal.dao.AccessLogDAO;
import com.bbq.dltb.dal.model.AccessLog;

@Service
public class AccessLogService {
	private static final Logger log = LoggerFactory.getLogger(AccessLogService.class);
	@Autowired
	private AccessLogDAO AccessLogDAO;

	public void insertAccessLog(AccessLog accessLog) {
		log.debug("插入访问日志" + JSON.toJSONString(accessLog));
		validParam(accessLog);
		AccessLogDAO.insertSelective(accessLog);
	}

	private static final int access_log_ip_length = 500;
	private static final int access_log_resuri_length = 500;
	private static final int access_log_param_length = 500;
	private static final int access_log_remark_length = 1000;
	private void validParam(AccessLog accessLog){
		if(accessLog != null ){
			if(accessLog.getIp()!=null && accessLog.getIp().length() > access_log_ip_length){
				accessLog.setIp(accessLog.getIp().substring(0,access_log_ip_length));
			}
			if(accessLog.getResUri()!=null && accessLog.getResUri().length() > access_log_resuri_length){
				accessLog.setResUri(accessLog.getResUri().substring(0,access_log_resuri_length));
			}
			if(accessLog.getParam()!=null && accessLog.getParam().length() > access_log_param_length){
				accessLog.setParam(accessLog.getParam().substring(0,access_log_param_length));
			}
			if(accessLog.getRemark()!=null && accessLog.getRemark().length() > access_log_remark_length){
				accessLog.setRemark(accessLog.getRemark().substring(0,access_log_remark_length));
			}
		}
	}
}
