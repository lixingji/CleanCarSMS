package com.cleancarSMS.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cleancarSMS.common.Configure;
import com.cleancarSMS.common.RandomStringGenerator;
import com.cleancarSMS.common.Signature;
import com.cleancarSMS.common.Util;
import com.cleancarSMS.common.XMLParser;
import com.cleancarSMS.pojo.BrandWCPayRequestParameters;
import com.cleancarSMS.pojo.UnifiedOrderReqData;
import com.cleancarSMS.service.UnifiedOrderService;
import com.cleancarSMS.service.UserInfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class wechatPayAction extends ActionSupport {

	private BrandWCPayRequestParameters brandWCPayRequestParameters; // 参数

	private double money;//支付金额
	private String code;// 用户的code
	private String orderId;// 订单号
	// context
	private ActionContext context;
	private Map<String, Object> session;

	private static final long serialVersionUID = 1L;
	

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * 获取getBrandWCPayRequest 参数
	 */
	public String getBrandWCPayRequest() throws Exception {
		System.out.println("orderId:"+orderId+";code:"+code+";mMoney:"+money);
		// 利用code获取openid
		String openId = queryOpenId();
		if (openId == null) {
			System.out.println("openId为空");
			return ERROR; // 暂时测试先关闭
		}
		// 利用openid获取 prePayId
		String prePayId = queryPrepayIdByOpenId(openId);
		if (prePayId == null) {
			System.out.println("prePayId为空");
			return ERROR;// 暂时测试先关闭
		}

		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		int timeStamp = (int) (System.currentTimeMillis() / 1000);// 以秒为单位

		// 错误！！！签名时的字符串应为 "package" :
		brandWCPayRequestParameters = new BrandWCPayRequestParameters();
		brandWCPayRequestParameters.setAppId(Configure.getAppid());
		brandWCPayRequestParameters.setSignType("MD5");
		brandWCPayRequestParameters.setTimeStamp(String.valueOf(timeStamp));
		brandWCPayRequestParameters.setNonceStr(nonce_str);
		brandWCPayRequestParameters.setPackageValue(prePayId);
		brandWCPayRequestParameters.setPaySign(Signature
				.getSign(brandWCPayRequestParameters.toMap()));

		return SUCCESS;
	}

	// 获取openid
	private String queryOpenId() throws Exception {
		String resStr = UserInfoService.request(code);
		JSONObject jsonObject = JSONObject.fromObject(resStr);
		if (jsonObject.get("errcode") != null) {
			Util.log(jsonObject.get("errmsg"));
			return null;
		}
		String openId = String.valueOf(jsonObject.get("openid"));
		System.out.println("获取openId成功：" + openId);
		return openId;
	}

	// 获取Prepayid
	@SuppressWarnings("finally")
	private String queryPrepayIdByOpenId(String openid) {

		// 数据暂时模拟，以后再填充
		String prePayID = null;// 初始化
		int payMoney=(int)(money*100);
		/*int money2=(int) money*100;
		if(money2==0){
			money2=1;//测试用
		}*/
		UnifiedOrderReqData reportReqData = new UnifiedOrderReqData(
				"车辆清洁", "112.74.111.200",payMoney,
				"http://www.glhyu.cn/CleanCarSMS/pages/commons/paySuccess.html", "JSAPI",
				orderId, openid);

		UnifiedOrderService reportService = new UnifiedOrderService(
				reportReqData);

		Map<String, Object> map;
		try {
			map = XMLParser.getMapFromXML(reportService.request());
			prePayID = String.valueOf(map.get("prepay_id"));
			System.out.println("---prePayID---:" + prePayID);

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("############获取prepayId出错#################");
		
		} finally {
			return prePayID;
		}
	}

	// 获取客户ip
	public String getRemortIP() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	// 获取支付金额money
	public int getPayMoney() {
		double myMoney=0.01;
		this.context = ActionContext.getContext();
		this.session = this.context.getSession();
		if (session.get("xiche_Money") != null) {
			myMoney = (Double) session.get("xiche_Money");
		}else{
			myMoney=money;
		}
		System.out.println("my PAY Money:"+myMoney);
		return (int) (myMoney * 100);// 以分为单位
	}

	public BrandWCPayRequestParameters getBrandWCPayRequestParameters() {
		return brandWCPayRequestParameters;
	}

	public void setBrandWCPayRequestParameters(
			BrandWCPayRequestParameters brandWCPayRequestParameters) {
		this.brandWCPayRequestParameters = brandWCPayRequestParameters;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
