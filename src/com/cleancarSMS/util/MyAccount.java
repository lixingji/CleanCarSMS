package com.cleancarSMS.util;

public class MyAccount {

	private final static String accountSid="d2e1e42004a3bdc4455194c57b7dfc10";
	private final static String token="32908dec908e44c26e917559a5ad4d98";
	private final static String appId="1a846c3a475842ccb63d0f4a9d9e8e39";
//	private final static String appId="b43c7c4d9f5b482bace69f734d65014e";
	private final static String templateId="5012";
//	private final static String templateId="5032";
	private final static String managerTemplateId="5014";
//	private final static String managerTemplateId="8826";
	
	public static String getAccountsid() {
		return accountSid;
	}
	public static String getToken() {
		return token;
	}
	public static String getAppid() {
		return appId;
	}
	public static String getTemplateid() {
		return templateId;
	}
	public static String getManagertemplateid() {
		return managerTemplateId;
	}

}
