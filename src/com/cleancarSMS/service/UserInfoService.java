package com.cleancarSMS.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import com.cleancarSMS.common.Configure;
import com.cleancarSMS.common.HttpsRequest;
import com.cleancarSMS.common.Util;

public class UserInfoService {
		
	private final static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	private String code;

    /**
     * 
     * @param code 用户code
     */
    public UserInfoService(String code){
        this.code = code;
    }

    public String request() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
    	    	
    	String requestUrl = access_token_url.replace("APPID", Configure.getAppid()).replace("SECRET", Configure.getAppSecret()).replace("CODE", code);
    	Util.log(requestUrl);
        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = HttpsRequest.httpsRequest(requestUrl, "GET", null);

        Util.log("report返回的数据：" + responseString);

        return responseString;
    }

    /**
     * 统一下单 API
     * @param code 用户code
     * @return API返回的数据
     * @throws Exception
     */
    public static String request(String code) throws Exception {
    	
    	String requestUrl = access_token_url.replace("APPID", Configure.getAppid()).replace("SECRET", Configure.getAppSecret()).replace("CODE", code);
    	Util.log(requestUrl);
        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = HttpsRequest.httpsRequest(requestUrl, "GET", null);

        Util.log("report返回的数据：" + responseString);

        return responseString;
    }

}
