package com.cleancarSMS.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BrandWCPayRequestParameters {
	
	private String appId;      //公众号id
	private String timeStamp;  //时间戳
	private String nonceStr;   //随机字符串
	private String packageValue;   //订单详情扩展字
	private String signType;       //签名方式
	private String paySign;        //签名
	
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackageValue() {
		return packageValue;
	}
	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
}
