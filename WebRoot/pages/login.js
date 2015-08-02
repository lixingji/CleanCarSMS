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
	$("#_id_btn_send").click(add_btn_send);
	$("#_id_btn_summit").click(add_btn_summit);
}



//点击发送验证码按钮
function add_btn_send(){
	var reqUrl=baseUrl+"getMsgCode.action";
	mobile=$("#mobile").val();
	console.log("mobile:"+mobile);
	
    if(flag>0){
    	return;
    }
     

    //判断手机号输入是否合法
    if(!mobile.match(/^1[3|8|5|4|7]{1}[0-9]{9}$/)){
    	myLayer("请输入正确的手机号！");
    	return;
	   }

	// myLayer("默认验证码：123456");
	
	var params = {
		 "mobile" : mobile
		};

		
    //60秒内重新发送
	    $("#_id_div_background").removeClass("has-success");
	    flag=1;
		var primaryTime=new Date();
		var interval=setInterval(function(){
			var newDate=new Date();
			var value=parseInt(primaryTime.getTime()/1000+60-newDate.getTime()/1000);
            $("#_id_btn_send").html(value+"秒后重发");
			if(value<=0){
				clearInterval(interval);
				flag=0;
				$("#_id_btn_send").html("获取验证码");
				$("#_id_div_background").addClass("has-success");
			}
			
		},1000);

	var successCallback=function(params){
		myLayer("验证码已成功发送!");
		// myLayer("验证码已成功发送!");
			/*if(params!=null&&params!='null'){
				alert("验证码已成功发送!");
			}else{
				alert("验证码发送失败！");
			}*/
	};
		
	getRequestData_POST(reqUrl,params,successCallback);
}

//点击提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"sendMsgCode.action";

	var msgCode=$("#code").val();
	mobile=$("#mobile").val();
	if(msgCode.length!=6){
		myLayer("请输入6位验证码！");
		return;
	}
	var params = {
		 "msgCode" : msgCode
		};

		var successCallback=function(params){
			console.log("mobile:"+mobile);

			if(params=='1'){
		    localStorage.setItem("xiche_mobile",mobile);
		    
                  myLayer("登陆成功！欢迎您，"+mobile);
                  setTimeout(function(){
                  	var state=myLocation.split("?")[1];
                  	if(state==1){
                  		location.href="index.html";
                  	}else{
                  		location.href="commons/myOrders.html";
                  	}
                  	// history.go(1);
                  	// location.href="index.html";
                  },2000);
              }else{
              	myLayer("验证码出错！");
              }
			/*if(params=='1'){
				localStorage.setItem("xiche_mobile",mobile);
                  myLayer("登陆成功");
                  setTimeout(function(){
                  	location.href="index.html";
                  },2000);
				
			}else{
				myLayer("登陆失败");
			}*/
	};

	getRequestData_POST(reqUrl,params,successCallback);
}
