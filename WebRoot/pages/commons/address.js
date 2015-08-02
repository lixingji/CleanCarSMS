// 废弃
/**
*lixingji on 2015/3/21
*服务地址
**/

$(function(){
	init();
});

function init(){
	getAddressInfo();
	
}

//获取地址数据
function getAddressInfo(){
	var reqUrl=baseUrl+"getAddressList.action";


	var params = {

		};

	var successCallback=function(params){
			var htmlStr='';
			for(var i=0;i<params.length;i++){
				htmlStr+='<li class="list-group-item">'+params[i].serveAddress+'</li>';
			}
			$(".list-group").html(htmlStr);
			add_list_click();
	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//点击list
function add_list_click(){
	$('li').each(function () {
       $(this).click(function(){
	      var myAddress=$(this).html();
	      localStorage.setItem("myAddress",myAddress);
	      location.href="service.html";
       });
    });
}