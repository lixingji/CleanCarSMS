/**
* lixingji on 2015/3/13
* 修改订单
**/
var id='';
var state='';

$(function(){
	init();
});

function init(){
	hadOrderModify();//判断订单是否修改过
	getOrderInfo();
	$("#_id_btn_sure").click(add_btn_sure);
}


//判断订单是否修改过
function hadOrderModify(){
	var xmodify=localStorage.getItem("xiche_modifyOrder");
    if(xmodify!=null&&xmodify!='null'&&xmodify!=''){
      wx.closeWindow();
    }
}

//获取订单信息
function getOrderInfo(){
	var url=location.href;

	var urlParams=url.split("?")[1];
	if(urlParams!=''&&urlParams!=null&&urlParams!=undefined){
		var urlDate=decodeURI(urlParams);
		var brand=urlDate.split(";")[0];
		var address=urlDate.split(";")[1];
		var carNumber=urlDate.split(";")[2];
		var serveTime=urlDate.split(";")[3];
		var userName=urlDate.split(";")[4];
		var remark=urlDate.split(";")[5];
		id=urlDate.split(";")[6];
		state=urlDate.split(";")[7];

		$("#_id_time").val(serveTime);
		$("#_id_address").val(address);
		$("#_id_username").val(userName);
		$("#_id_carNum").val(carNumber);
		$("#_id_brand").val(brand);
		$("#_id_note").val(remark);


	} 
}

//确定按钮
function add_btn_sure(){
    var reqUrl='';
    var phoneNumber=localStorage.getItem("xiche_mobile");
	if(state==1)
	    reqUrl=baseUrl+"updateOrder.action";
	else
		reqUrl=baseUrl+"addOrder.action";
   
		   if($("#_id_time").val()==''){
		   	myLayer("服务时间不能为空");
		   	return;
		   }
		   if($("#_id_address").val()==''){
		   	myLayer("服务地址不能为空");
		   	return;
		   }
		   if($("#_id_username").val()==''){
		   	myLayer("姓名不能为空");
		   	return;
		   }
		   if($("#_id_carNum").val()==''){
		   	myLayer("车牌号不能为空");
		   	return;
		   }
		   if($("#_id_brand").val()==''){
		   	myLayer("车型不能为空");
		   	return;
		   }
        var params = {
           "brand" : $("#_id_brand").val(),
           "address" : $("#_id_address").val(),
           "carNumber" : $("#_id_carNum").val(),
           "serveTime" : $("#_id_time").val(),
           "userName" : $("#_id_username").val(),
           "remark" : $("#_id_note").val(),
           "id" : id,
           "phoneNumber":phoneNumber
          };

        var successCallback=function(params){
          if(params!=0){
            
            if(state==1){
            	myLayer("订单修改成功");
            	localStorage.setItem("xiche_id",id);//修改订单用原id
            }else{
            	myLayer("订单添加成功");
            	localStorage.setItem("xiche_id",params);//添加订单用新id
            }
            localStorage.setItem("xiche_modifyOrder","OK");//添加值
            setTimeout(function(){
            	location.href="http://glhyu.com/pages/commons/beiAnAuth2.html";
            },2000); 
          }else{
            myLayer("订单提交失败");
            
          }
        }
         getRequestData_POST(reqUrl,params,successCallback);

}