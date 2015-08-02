/**
* lixingji on 2015/4/21
* 负责地区列表
**/
var managerId=0;
var flag=0;
var myLocation=location.href;

$(function(){
	init();
});

function init(){
	loadBaseInfo();
	loadAddressList();
	$("#_id_sure").click(add_btn_summit);
}

//加载url上传来的基本信息
function loadBaseInfo(){
	var spiltUrl=myLocation.split("?")[1];
	managerId=spiltUrl.split(";")[0];
	// var city=spiltUrl.split(";")[1];
	var phone=spiltUrl.split(";")[1];
	$("#managerPhone").html(phone);
	// $("#city").html(city);

}

//加载负责人信息的列表信息
function loadAddressList(){

	var reqUrl=baseUrl+"getAddressListByMId.action";
	var params = {
		"managerId":managerId
		};
	var successCallback=function(params){
			if(params!=null){
				var strHtml='';
				for(var i=0;i<params.length;i++){
					strHtml+='<tr><td>'+params[i].serveAddress+'</td><td><input type="button" id="_id_delete" onclick="deleteAddress('+params[i].id+')" value="删除"></td></tr>';
				}
				$("#listDetail").html(strHtml);
              }else{
              	$("#listDetail").html("暂无负责地址信息！");
              }
              flag=0;
	};

	getRequestData_POST(reqUrl,params,successCallback);
}
//点击添加负责人的提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"addAddress.action";

    var address=$("#_id_address").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(address==''){
		myLayer("地址不能为空！");
		flag=0;
		return;
	}
	var params = {
		 "managerId" : managerId,
		 "serveAddress":address
		};

		var successCallback=function(params){

			if(params==1){
                  myLayer("添加成功!!!");
                  setTimeout(function(){
                  	location.reload();
                  },1500);
              }else if(params==0){
              	myLayer("添加失败 ！您尚未登陆！");
              }else{
              	myLayer("添加失败 ！！！");
              }
              flag=0;
	    };

	getRequestData_POST(reqUrl,params,successCallback);
}

//删除负责的地址
function deleteAddress(id){
	var reqUrl=baseUrl+"deleteAddress.action";
	var params = {
		"id":id
		};
	var successCallback=function(params){
			if(params==1){
				myLayer("删除成功！！！");
				setTimeout(function(){
                  	location.reload();
                  },1500);
              }else if(params==0){
              	myLayer("删除失败，您尚未登陆！！！");
              }else{
              	myLayer("删除失败！！！");
              }
              flag=0;
	};

	getRequestData_POST(reqUrl,params,successCallback);
}