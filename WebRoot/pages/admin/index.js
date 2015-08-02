/**
**lixingji on date 2015/4/12
**/

localStorage.setItem("gotoHref","");//清空历史保存的地址记录

$(function(){
	init();
});

function init(){
	$("#exit_system").click(add_btn_exit);
	//点击导航按钮
	add_nav_click();
}

//点击退出系统
function add_btn_exit(){
	localStorage.setItem("adminName","");
	myLayer("系统退出成功！");
	setTimeout(function(){
       location.href="login.html";
	},2000);
    
}

//点击导航按钮
function add_nav_click(){
    $("#nav1").click(function(){
		add_btn_click(1);
	});
	$("#nav2").click(function(){
		add_btn_click(2);
	});
	$("#nav3").click(function(){
		add_btn_click(3);
	});
	$("#nav4").click(function(){
		add_btn_click(4);
	});
}
//选项按钮
function add_btn_click(num){
	for(var j=1;j<=4;j++){
	    var navId="#nav"+j;
		$(navId).removeClass();
	}
	var current_navId="#nav"+num;
    $(current_navId).addClass("active");
    IFrameContent(num);
}

//iframe填充的内容
function IFrameContent(num){
	if(num==1){
		$("#_id_frame").attr("src","http://glhyu.com/index.html");
	}else if(num==2){
		$("#_id_frame").attr("src","addmanager.html");
	}else if(num==3){
		$("#_id_frame").attr("src","reset-money.html");
	}else{
		$("#_id_frame").attr("src","reset-password.html");
	}
}