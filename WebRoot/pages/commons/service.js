/**
* lixingji on 2015/3/12
* 服务
**/
var mobile='';
var carNum='';
var brand='';
var address='';
var month='1月';
var day='1日';
var time='';
var flag=0;
var lastId='#1';//上一个选择的id
var lastMonthId='#1';//上一个选择的月份id
var lastDayId='#1';//上一个选择的天数id
var managerId=0;//负责人id
var serType='1';//服务类型 及 声明
var type=1;//类型  1为外洗  2外内外洗
var myDeclare='';//声明

//日期默认选到今天
var md = new Date();
var mMon = md.getMonth() + 1;
var mDay = md.getDate();
var hours=md.getHours();
$("#_id_date").val(mMon+"月"+mDay+"日");

$(function(){

	getUrlParamsData();
	init();
});

//初始化
function init(){
	
	localStorage.setItem("xiche_payParams",'');//清除支付url参数数据
	$("#_id_btn_sure").click(add_btn_sure);
	$("#_id_address").click(add_address_click);
	$("#_id_date").click(add_date_click);
	$("#_id_time").click(add_time_click);
	$("#_id_type").click(add_type_click);

}

//点击确认按钮
function add_btn_sure(){
	var reqUrl=baseUrl+"addOrder.action";

    var servDate=$("#_id_date").val();
	var servTime=$("#_id_time").val();
	var servAddress=$("#_id_address").val();
	var userName=$("#_id_username").val();
	var remark=$("#_id_note").val();
	var declareType=$("#_id_type").val();
	var detailAddr=$("#_id_detailAddr").val();

    if(flag>0){
    	return;
    }
    if(mobile==null||mobile==''||mobile=='null'){
    	myLayer("您尚未登陆，请先登录！");
    	setTimeout(function(){
    		location.href="../login.html";
    	},2000);
    }
    if(servDate=='请选择服务日期（必填）'){
    	myLayer("服务日期不能为空");
    	return;
    }
    if(servTime=='请选择服务时间段（必填）'){
    	myLayer("服务时间不能为空");
    	return;
    }
    if(servAddress=='请选择服务地址（洗车地址，必填）'){
    	myLayer("服务地址不能为空");
    	return;
    }
    if(declareType=='请选择服务类型（必填）'){
    	myLayer("服务类型不能为空");
    	return;
    }
    if(detailAddr==''){
    	myLayer("服务详细地址不能为空！");
    	return;
    }
    if(userName==''){
    	myLayer("用户名不能为空");
    	return;
    }
	if(remark.length>150){
		myLayer("备注字数超过限制");
		return;
	}
    $("#_id_btn_sure").attr("disabled","disabled");
	flag=1;
	var params = {
		 "address" : servAddress+"-"+detailAddr,
		 "carNumber" : carNum,
		 "phoneNumber" : mobile,
		 "serveTime" : servDate+servTime,
		 "userName" : userName,
		 "brand" : brand,
		 "remark" : remark,
		 "managerId":managerId,
		 "type":type,
		 "myDeclare":myDeclare
		};

	var successCallback=function(params){
		
			if(params!=0){
				myLayer("订单提交成功!");
				localStorage.setItem("xiche_id",params);
				setTimeout(function(){
					location.href="http://glhyu.com/pages/commons/beiAnAuth2.html";
					// location.href="current-order.html";
				},2000);
			}else{
				myLayer("订单提交失败!");
			}
			$("#_id_btn_sure").removeAttr("disabled");
			flag=0;
			
	};

	getRequestData_POST(reqUrl,params,successCallback);
	
}

//获取url传过来的参数
function getUrlParamsData(){

	if(localStorage.getItem("myAddress")!=null){
		$("#_id_address").val(localStorage.getItem("myAddress"));
	}

	var url=location.href;
	var urlPar=decodeURI(url.split("?")[1]);
	// alert("urlPar:"+urlPar);
	if(urlPar!=null&&urlPar!=''&&urlPar!="undefined"){
		carNum=urlPar.split(";")[0];
		brand=urlPar.split(";")[1];
	}else{
		var orderInfo=localStorage.getItem("xiche_orderInfo");
		// alert("orderInfo:"+orderInfo);
		if(orderInfo!=null){
			//var orderInfo=data["carNumber"]+";"+data["brand"]+";"+data["address"]+";"+data["userName"];
			carNum=orderInfo.split(";")[0];
		    brand=orderInfo.split(";")[1];
		    var orderAddress=orderInfo.split(";")[2];
		    var orderUserName=orderInfo.split(";")[3];
		    //填充数据
		    $("#_id_username").val(orderUserName);
		    $("#_id_address").val(orderAddress.split("-")[0]);
		    $("#_id_detailAddr").val(orderAddress.split("-")[1]);
		}
	}
	mobile=localStorage.getItem("xiche_mobile");
}

//关闭窗口
function CloseWebPage(){
	alert("请点击微信左上角的 X 退出应用");
	history.go(1);
}

var page_sure_btn='<button class="closediy" style="position:absolute;left:15px;bottom:15px;width:90%;background-color:#00EE00;border:none;margin-top:13px;padding:10px 0;font-size:20px;">确  定</button>';
//address获得焦点
function add_address_click(){
	//下拉选择省份和城市
	var provinceCity='<table><tr><td>省份：<select id="myProvince"><option>广东</option></select></td><td>&nbsp;&nbsp;城市：<select id="myCity"><option>深圳</option><option>广州</option></select></td></tr></table>';
	
	var strHtml='<html>'+provinceCity+'<div class="address-div"> <ul id="_ul_address">';
	/*for(var i=0;i<5;i++){
		strHtml+='<li id="address'+i+'" onmouseup="addressSelected('+i+');">地址'+i+'</li>';
	}*/
	strHtml+='</ul></div>';

	myPage(strHtml+page_sure_btn+'</html>');
	//初始化
	add_city_change("广东","深圳");
	$("#myCity").change(function(){
		add_city_change($("#myProvince").val(),$("#myCity").val());
	});
}

//date获得焦点
function add_date_click(){
	var strHtml='';
	strHtml+='<html><div class="month-div"><ul id="_ul_month">';
	var d = new Date();
	var vMon = d.getMonth() + 1;
    var vDay = d.getDate();
    var dayArr=[];
    month=vMon;
    day=vDay;
    if(vMon==2){
    	dayArr=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28];
    }else if(vMon==1||vMon==3||vMon==5||vMon==7||vMon==8||vMon==10||vMon==12){
    	dayArr=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
    }else{
    	dayArr=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
    }
	//月份开始 12个月
	for (var i = mMon; i <= 12; i++) {
		strHtml+='<li id="month'+i+'" onmouseup="monthSelected('+i+');">'+i+' 月</li><hr>';
	};
	strHtml+='</ul></div><div class="day-div"><ul>';//月份结束
    //日开始 31天
	for (var i = mDay-1; i < dayArr.length; i++) {
		strHtml+='<li id="day'+i+'" onmouseup="daySelected('+i+');">'+dayArr[i]+' 日</li><hr>';
	};
	strHtml+='</ul></div>';
	myPage(strHtml+page_sure_btn+'</html>');
	monthSelected(mMon);
	daySelected(mDay-1);
}

//time获得焦点
function add_time_click(){
	//定义时间段数组
	var timeArr=["10:00 - 11:00","11:00 - 12:00","12:00 - 13:00","13:00 - 14:00","14:00 - 15:00","15:00 - 16:00","16:00 - 17:00","17:00 - 18:00","18:00 - 19:00","19:00 - 20:00","20:00 - 21:00"];
	var strHtml='<html><div class="address-div"> <ul id="_ul_time">'; 
	
	var start=0;//起始点
	if(hours>10&&hours<20)
		start=hours-9;
	if(hours>21)
		start=0;
	for(var i=start;i<timeArr.length;i++){
		strHtml+='<li id="time'+i+'" onmouseup="timeSelected('+i+');">'+timeArr[i]+'</li>';
	}
	strHtml+='</ul></div>';
	myPage(strHtml+page_sure_btn+'</html>');
	timeSelected(start);
}

//选择洗车服务类型
function add_type_click(){
    var page_sure_btn2='<button class="closediy" style="width:100%;background-color:#00EE00;border:none;margin-top:13px;padding:10px 0;font-size:20px;">确  定</button>';
	var strHtml='<html>';
	strHtml+='<div id="type_content" style="margin:10px 10px;"><h4 style="color:blue;padding:10px 0px;">服务类型</h4><input type="radio" name="type" value="单洗外面 20元" checked="checked" />单洗外面 <span id="_id_price1">--</span>元<br><input type="radio" name="type" value="内外洗 30元" />内外洗 <span id="_id_price2">--</span>元';
	strHtml+='<br><h4 style="color:blue;padding:20px 0px;">划痕及损伤声明</h4><input type="radio" name="declare" value="无划痕损伤" checked="checked" />无划痕损伤<br><input type="radio" name="declare" value="免责" />我知道车身有划痕、损伤，我对此并不在意，具体位置说不清<h5 style="padding:10px 0px;">本人车辆以下位置有：</h5><input type="radio" name="declare" value="划痕" />划痕<input type="radio" name="declare" value="损伤" />损伤<hr style="padding:10px 0px;">';
	strHtml+='<table><tr><td><input type="checkbox" name="position" value="前保险杠" />前保险杠</td><td><input type="checkbox" name="position" value="后保险杠" />后保险杠</td></tr><tr><td><input type="checkbox" name="position" value="左前门" />左前门</td>';
	strHtml+='<td><input type="checkbox" name="position" value="右前门" />右前门</td></tr><tr>';
	strHtml+='<td><input type="checkbox" name="position" value="左后门" />左后门</td>';
	strHtml+='<td><input type="checkbox" name="position" value="右后门" />右后门</td></tr><tr>';
	strHtml+='<td><input type="checkbox" name="position" value="发动机盖" />发动机盖</td>';
	strHtml+='<td><input type="checkbox" name="position" value="后备箱门" />后备箱门</td></tr><tr>';
	strHtml+='<td><input type="checkbox" name="position" value="左前轮上部" />左前轮上部</td>';
	strHtml+='<td><input type="checkbox" name="position" value="右前轮上部" />右前轮上部</td></tr><tr>';
	strHtml+='<td><input type="checkbox" name="position" value="左后轮上部" />左后轮上部</td>';
	strHtml+='<td><input type="checkbox" name="position" value="右后轮上部" />右后轮上部</td></tr><tr>';
	strHtml+='<td><input type="checkbox" name="position" value="左后视镜" />左后视镜</td>';
	strHtml+='<td><input type="checkbox" name="position" value="右后视镜" />右后视镜</td>';

	strHtml+='</tr></table>';

	myFullPage(strHtml+page_sure_btn2+'<br><br></div><div align="center" style="color:red;margin-top:35px;"><h2>向上滑动查看其他选项</h2></div></html>');
	loadPrice();
}

//地址被选中
function addressSelected(num){
	//记录当前被选中的地址值
	var li_id="#address"+num;

	address=$(li_id).html();

	$("#_id_address").val(address);
	$(lastId).attr("style","background-color:#fff;");
	managerId=$(li_id).attr("mId");
	lastId=li_id;

	$(li_id).attr("style","background-color:#EEB422;");
}

//月份被选中
function monthSelected(num){
	//记录当前被选中的月份值
	var li_id="#month"+num;
	month=$(li_id).html();
    
    //非本月时，日数为31天
	if(num>mMon){
		var strHtml='';
	    //日开始 31天
		for (var i = 0; i < 31; i++) {
			strHtml+='<li id="day'+i+'" onmouseup="daySelected('+i+');">'+(i+1)+' 日</li><hr>';
		};
		$(".day-div ul").html(strHtml);
	}

    $("#_id_date").val(month+day);
	$(lastMonthId).attr("style","background-color:#fff;");
	lastMonthId=li_id;

	$(li_id).attr("style","background-color:#EEB422;");
}

//日期天被选中
function daySelected(num){
	//记录当前被选中的天数
	var li_id="#day"+num;
	day=$(li_id).html();

	var d = new Date();
	var vMon = d.getMonth() + 1;
    var vDay = d.getDate();
    if((month.split(" 月")[0]==vMon&&num+1<vDay)){
    	alert("日期不能早于今天");
    	return;
    }

	$("#_id_date").val(month+day);
	$(lastDayId).attr("style","background-color:#fff;");
	lastDayId=li_id;

	$(li_id).attr("style","background-color:#EEB422;");
}


//时间被选中
function timeSelected(num){
	//记录当前被选中的时间值
	var li_id="#time"+num;
	time=$(li_id).html();

	$("#_id_time").val(time);
	$(lastId).attr("style","background-color:#fff;");
	lastId=li_id;

	$(li_id).attr("style","background-color:#EEB422;");
}

//城市改变值后
function add_city_change(province,city){
	var reqUrl=baseUrl+"getAddressList.action";

	var params = {
		 "province" : province,
		 "city":city
		};

	var successCallback=function(params){
		if(params!=null){
			var strHtml='';
			for(var i=0;i<params.length;i++)
				strHtml+='<li id="address'+i+'" mId="'+params[i].managerId+'" onmouseup="addressSelected('+i+');">'+params[i].serveAddress+'</li>';

			$("#_ul_address").html(strHtml);
		}else{
			$("#_ul_address").html('暂无数据');
		}

	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//弹出覆盖整个页面层  
function myFullPage(myHtmlContent){
    var pagei=layer.open({
    type: 1, //1代表页面层
    content: myHtmlContent,
    style: 'width:100%; height:'+ document.documentElement.clientHeight +'px; padding:10px; background-color:#FFFFFF; color:#242424; border:none;position:absolute;bottom:0;left:0;',
    success: function(olayer){
        var cla = 'getElementsByClassName';
        olayer[cla]('closediy')[0].onclick = function(){
        	var typeClick=$('input[name="type"]:checked').val();
        	// alert(typeClick);
        	var declareClick=$('input[name="declare"]:checked').val();
        	// alert(declareClick);
        	var positionClick="";
  
        	$('input[name="position"]:checked').each(function(){
        		positionClick+=$(this).val()+" ";
        	});
        	//服务类型 外洗或内外洗
        	if(typeClick=="单洗外面 20元"){
        		type=1;
        		typeClick="单洗外面";
        	}else{
        		type=2;
        		typeClick="内外洗";
        	}

        	myDeclare=positionClick+declareClick;
        	if(positionClick.length>6){
        		positionClick=positionClick.substring(0, 6)+"...";
        	}

        	// alert(positionClick);
        	if(declareClick=="免责"){
        		serType=typeClick+" "+declareClick;
        		myDeclare="免责";
        	}else if(declareClick=="无划痕损伤"){
        		serType=typeClick+" "+declareClick;
        		myDeclare="无划痕损伤";
        	}else
        	   serType=typeClick+" "+positionClick+declareClick;
        	$("#_id_type").val(serType);
        	// alert(serType);
            //关闭模态框
            layer.close(pagei);
        }
    }
    });
}

//加载价钱1
function loadPrice(){
	var reqUrl=baseUrl+"queryMoney.action";

	var params = {
		'type':1
		};

	var successCallback=function(params){
		if(params!=null){
			$("#_id_price1").html(params);
			loadPrice2();
		}else{
			myLayer("加载价钱出错");
			$("#_id_price1").html('<a style="color:red;">出错</a>');
			$("#_id_price2").html('<a style="color:red;">出错</a>');
		}

	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//加载价钱2
function loadPrice2(){
	var reqUrl=baseUrl+"queryMoney.action";

	var params = {
		'type':2
		};

	var successCallback=function(params){
		if(params!=null){
			$("#_id_price2").html(params);
		}else{
			$("#_id_price1").html('<a style="color:red;">出错</a>');
			$("#_id_price2").html('<a style="color:red;">出错</a>');
		}

	};

	getRequestData_POST(reqUrl,params,successCallback);
}