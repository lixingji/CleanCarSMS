package com.cleancarSMS.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.cleancarSMS.common.Configure;
import com.cleancarSMS.common.RandomStringGenerator;
import com.cleancarSMS.common.Signature;

public class UnifiedOrderReqData {

    //每个字段具体的意思请查看API文档
    private String appid;//微信分配的公众账号ID 
    private String mch_id;//微信支付分配的商户号 
    private String nonce_str;//随机字符串，不长于32位
    private String sign;//签名
    private String body;//商品或支付单简要描述
    private int total_fee;//订单总金额，只能为整数，以分为单位
    private String spbill_create_ip;//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 
    private String notify_url;//接收微信支付异步通知回调地址 
    private String trade_type;//取值如下：JSAPI，NATIVE，APP
    private String out_trade_no;//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
    private String openid;//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识

    /**
     * 请求统计上报API
     * @param deviceInfo 微信支付分配的终端设备号，商户自定义
     * @param interfaceUrl 上报对应的接口的完整URL，类似： https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param executeTimeCost 接口耗时情况，单位为毫秒
     * @param returnCode API返回的对应字段
     * @param returnMsg API返回的对应字段
     * @param resultCode API返回的对应字段
     * @param errCode API返回的对应字段
     * @param errCodeDes API返回的对应字段
     * @param outTradeNo API返回的对应字段
     * @param userIp 发起接口调用时的机器IP
     */
    public UnifiedOrderReqData(String body,String spbill_create_ip,int total_fee, String notify_url,String trade_type, String out_trade_no, String openid){
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //商户系统自己生成的唯一的订单号
        setOut_trade_no(out_trade_no);

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        setBody(body);
        setNotify_url(notify_url);
        setSpbill_create_ip(spbill_create_ip);
        setTotal_fee(total_fee);
        setTrade_type(trade_type);
        setOpenid(openid);
        
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
    }
    
    public UnifiedOrderReqData() {
    	
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                    System.out.println(obj.toString());
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
