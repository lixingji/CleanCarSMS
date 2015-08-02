/**
* lixingji on 2015/3/12
* test
**/

//解析url参数
function GetQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = window.location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}

$(function(){
	init();
});

//填充数据
function fillData(data) {
	var orderInfo=data["carNumber"]+";"+data["brand"]+";"+data["address"]+";"+data["userName"];
	$("#address").html(data["address"]);
	$("#carNumber").html(data["carNumber"]);
	$("#orderTime").html(data["orderTime"]);
	$("#phoneNumber").html(data["phoneNumber"]);
	$("#serveTime").html(data["serveTime"]);
	var state = data["orderState"];
	if (state==0) {
		$("#orderState").html("未支付");
	} else if (state==1) {
		$("#orderState").html("已支付");
	} else {
		$("#orderState").html("已取消");
	}
	$("#userName").html(data["userName"]);
	$("#brand").html(data["brand"]);
	$("#remark").html(data["remark"]);
	$("#money").html(data["money"]);
	localStorage.setItem("xiche_orderInfo",orderInfo);
}

//请求得到数据
function successCallback(data) {
	if (data==null) {
		alert("系统出错");
	} else {
		dataTemp = data;
		fillData(data);
	}
}

//再来一单
function onceMore() {
	// var urlParams=$("#brand").html()+";"+$("#address").html()+";"+$("#carNumber").html()+";"+$("#serveTime").html()+";"+$("#userName").html()+";"+$("#remark").html()+";"+$("#id").html()+";0";
	// location.href="reset-order.html?"+encodeURI(urlParams);
	localStorage.setItem("xiche_id",'');
	location.href="service.html";
}

function init(){
	localStorage.setItem("xiche_modifyOrder",null);//如果存在已修改订单状态，将订单状态置空
	$("#onceMore").click(onceMore);
	var id = GetQueryString("id");
	$("#id").html(id);
	var reqUrl=baseUrl+"queryDetail.action";
	var data = {}; 
	data.id = id;
	getRequestData_POST(reqUrl,data,successCallback);
}