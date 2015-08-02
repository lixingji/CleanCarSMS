package com.cleancarSMS.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cleancarSMS.common.Configure;
import com.cleancarSMS.common.HttpsRequest;
import com.cleancarSMS.common.Util;
import com.cleancarSMS.pojo.UnifiedOrderReqData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class UnifiedOrderService {

    private UnifiedOrderReqData reqData ;

    /**
     * 统一下单 API
     * @param reportReqData 这个数据对象里面包含了API要求提交的各种数据字段
     */
    public UnifiedOrderService(UnifiedOrderReqData reportReqData){
        this.reqData = reportReqData;
    }

    public String request() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
    	
    	//解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(reqData);
    	
    	String responseString = HttpsRequest.httpsRequest(Configure.ORDER_API, "POST", postDataXML);

        Util.log("   report返回的数据：" + responseString);

        return responseString;
    }

    /**
     * 统一下单 API
     * @param reportReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String request(UnifiedOrderReqData reportReqData) throws Exception {
    	
    	//解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(reportReqData);

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = HttpsRequest.httpsRequest(Configure.ORDER_API, "POST", postDataXML);

        Util.log("report返回的数据：" + responseString);

        return responseString;
    }

    /**
     * 获取time:统计发送时间，格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。
     * @return 订单生成时间
     */
    private static String getTime(){
        //订单生成时间自然就是当前服务器系统时间咯
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

}
