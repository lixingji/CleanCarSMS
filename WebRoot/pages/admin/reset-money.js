/**
* lixingji on 2015/4/12
* test
**/
var flag=0;
$(function(){
	init();
});

function init(){
	loadCurrentMoney();
	loadCurrentInOutMoney();
	$("#_id_btn_summit").click(add_btn_summit);//外洗服务
	$("#_id_btn_summit_inout").click(add_btn_summit_inout);//内外洗服务
}

//加载当前金额
function loadCurrentMoney(){
	var reqUrl=baseUrl+"queryMoney.action";
	var params = {
		};

    var successCallback=function(params){

			if(params!=-1){
                  $("#current-money").html(params);
              }else{
              	myLayer("加载当前金额出错!请刷新重试");
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//加载当前内外洗车服务金额
function loadCurrentInOutMoney(){
	var reqUrl=baseUrl+"queryMoney.action";
	var params = {
		"type":2
		};

    var successCallback=function(params){

			if(params!=-1){
                  $("#current-inoutmoney").html(params);
              }else{
              	myLayer("加载当前金额出错!请刷新重试");
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//点击提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"updateMoney.action";

	var serMoney=$("#serMoney").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(serMoney==''){
		myLayer("用户名不能为空！");
		flag=0;
		return;
	}
	var params = {
		 "money" : serMoney
		};

	var successCallback=function(params){

			if(params==0){
                  myLayer("您尚未登陆!!!");
                  localStorage.setItem("gotoHref",location.href);
                  setTimeout(function(){
                  	location.href="login.html";
                  },2000);
              }else if(params==1){
              	myLayer("修改服务金额成功!!!");
              	flag=0;
              	setTimeout(function(){
                  	location.reload();
                  },2000);
              }else{
              	myLayer("修改服务金额失败!!!");
              	flag=0;
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//点击提交按钮
function add_btn_summit_inout(){
	var reqUrl=baseUrl+"updateInOutMoney.action";

	var serMoney=$("#inoutserMoney").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(serMoney==''){
		myLayer("用户名不能为空！");
		flag=0;
		return;
	}
	var params = {
		 "money" : serMoney
		};

	var successCallback=function(params){

			if(params==0){
                  myLayer("您尚未登陆!!!");
                  localStorage.setItem("gotoHref",location.href);
                  setTimeout(function(){
                  	location.href="login.html";
                  },2000);
              }else if(params==1){
              	myLayer("修改服务金额成功!!!");
              	flag=0;
              	setTimeout(function(){
                  	location.reload();
                  },2000);
              }else{
              	myLayer("修改服务金额失败!!!");
              	flag=0;
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}
