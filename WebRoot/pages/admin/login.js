/**
* lixingji on 2015/3/10
* test
**/
var mobile='';
var flag=0;
var myLocation=location.href;

$(function(){
	init();
});

function init(){
	$("#_id_btn_summit").click(add_btn_summit);
}

//点击提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"adminLogin.action";

	var adminName=$("#adminName").val();
	var password=$("#password").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(adminName==''){
		myLayer("用户名不能为空！");
		return;
	}
	if(password==''){
		myLayer("密码不能为空！");
		return;
	}
	var params = {
		 "adminName" : adminName,
		 "password":password
		};

		var successCallback=function(params){

			if(params==1){
                  myLayer("登陆成功!!!");
                  localStorage.setItem("adminName",adminName);
                  var myHref=localStorage.getItem("gotoHref");
                  setTimeout(function(){
                  	if(myHref==''||myHref==null){
                  	  location.href="index.html";
                    }else{
                    	location.href=myHref;
                    }
                  },1000);
              }else{
              	myLayer("用户名或密码错误！");
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}
