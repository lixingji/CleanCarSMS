/**
* lixingji on 2015/4/12
* 修改密码
**/
var flag=0;
$(function(){
	init();
});

function init(){
	$("#_id_btn_summit").click(add_btn_summit);
}

//点击提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"updatePsw.action";
	

	var oldPsw=$("#oldPsw").val();
	var newPsw=$("#newPsw").val();
	var newPsw2=$("#newPsw2").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(oldPsw==''||newPsw==''){
		myLayer("密码不能为空！");
		flag=0;
		return;
	}
	if(newPsw!=newPsw2){
		myLayer("新密码与确认密码不匹配！");
		flag=0;
		return;
	}
	var params = {
		"oldPsw":oldPsw,
		"newPsw":newPsw
		};

	var successCallback=function(params){

			if(params==0){
                  myLayer("您尚未登陆!!!");
                  localStorage.setItem("gotoHref",location.href);
                  setTimeout(function(){
                  	location.href="login.html";
                  },2000);
              }else if(params==1){
              	myLayer("密码修改成功!!!");
              	flag=0;
              	setTimeout(function(){
                  	location.reload();
                  },2000);
              }else{
              	myLayer("密码修改失败!!!");
              	flag=0;
              }
	};

	getRequestData_POST(reqUrl,params,successCallback);
}
