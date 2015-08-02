/**
* lixingji on 2015/3/12
* test
**/

var attribute = ["客户姓名", "手机号码", "车牌号"];
var attribute_EN = ["userName", "phoneNumber", "carNumber"];

$(function(){
	init();
});

//解析url参数
function GetQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = window.location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}

//回调函数
function successCallback(data) {
	if (data.length==0) {
		$("#createtable").html("暂无数据");
	}
	for (var i=0; i<data.length; i++) {
		CreateTable(3,2,data[i]);
	}
}

function init(){

	var reqUrl=baseUrl+"loadAllOrders.action";
	var data = {}; 
	data.phoneNumber = localStorage.getItem("xiche_mobile");
	getRequestData_POST(reqUrl,data,successCallback);
}


//创建一个table
function CreateTable(rowCount,cellCount, temp)
{
	//新建一个链接
	var a = $("<a href=\"order.html?id="+temp["id"]+"\"></a>");
	a.appendTo($("#createtable"));
	var table=$("<table class=\"table table-bordered\"></table>");
    table.appendTo(a);
    var caption = $("<caption>订单</caption>");
    caption.appendTo(table);
    for(var i=0;i<rowCount;i++)
    {
    	var tr=$("<tr></tr>");
        tr.appendTo(table);
		var td=$("<td>"+attribute[i]+"</td>");
        td.appendTo(tr);
        var td=$("<td>"+temp[attribute_EN[i]]+"</td>");
        td.appendTo(tr);
    }
    var tr=$("<tr></tr>");
    tr.appendTo(table);
	var td=$("<td>更多</td>");
    td.appendTo(tr);
    var td=$("<td>...</td>");
    td.appendTo(tr);
}
