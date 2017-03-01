package com.bbq.dltb.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.qiniu.util.Auth;

public class QiniuDBUtil {

	private static final String accessKey = "da4WWEaoxEPTdX4-jJj9TEufZ9IqJYzlFXRalPsx";
	private static final String secretKey = "61YI05TmT-BhKfYCf6pKdWCPIJ-oXGFYy8qBPF3i";
	private static final String domainOfBucket = "http://ohciv5ysw.bkt.clouddn.com";
	
	public static String createPrivateUrllk(String fileName, long expireInSeconds){
//		String fileName = "七牛/云存储/qiniu.jpg";
		String encodedFileName = null;
		try {
			encodedFileName = URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		Auth auth = Auth.create(accessKey, secretKey);
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		return finalUrl;
	}
	
	public static String createPrivateUrllk(String fileName){
		return createPrivateUrllk(fileName, 10L);//默认10秒
	}
	
	public static String createPublicUrllk(String fileName){
		String encodedFileName = null;
		try {
			encodedFileName = URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		return publicUrl;
	}
	
}
