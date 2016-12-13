package com.bbq.dltb.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgUtil {
	private static final Logger log = LoggerFactory.getLogger(MsgUtil.class);

	public static void sendMsg(String phone, String msg) {
		log.info("发送短信，phone={},msg={}", phone, msg);
		String getURL = "http://222.73.117.169/msg/HttpBatchSendSM?account=N9778371&pswd=Ps03262e&mobile=" + phone +
                "&msg="+msg+ "&needstatus=true";
        HttpURLConnection connection = null;
		try {
			URL getUrl = new URL(getURL);
			connection = (HttpURLConnection)getUrl.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
			    lines += lines.toString();
			}
			reader.close();
		} catch (Exception e) {
			log.error("",e);
		} finally{
			if(connection != null){
				connection.disconnect();
			}
		}
        
	}
}
