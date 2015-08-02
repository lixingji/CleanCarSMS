/**
**lixingji on date 2015/4/12
**/

$(function(){
	init();
});

function init(){
  //修改订单状态
  changeOrderState();
	
	//点击完成按钮退出
    $("#_id_done").click(function(){
       	wx.closeWindow();
    });

}

//修改订单状态
function changeOrderState(){
  var reqUrl=baseUrl+"updateOrderState.action";
  //订单Id
  var orderId=0;
  orderId=localStorage.getItem("xiche_id");
  var params = {
     "id" : orderId
    };

  var successCallback=function(params){
      //返回成功!  1
      if(params!='0'){
        console.log("修改订单状态成功！");
        //发送消息给负责人
        sendManagerSMS();
      }else{
        console.log("修改订单状态失败！");
      }
  };

  getRequestData_POST(reqUrl,params,successCallback);
}

//发送消息给负责人
	
function sendManagerSMS(){
  console.log("发送消息！");
	var reqUrl=baseUrl+"sendSMStoManager.action";
  var userInfo=localStorage.getItem("userInfo");

    //临时数据
	var userName=userInfo.split(";")[0];
	var userPhone=localStorage.getItem("xiche_mobile");
	var carNum=userInfo.split(";")[1];
	var address=userInfo.split(";")[2];
  var managerId=userInfo.split(";")[3];
	// var mobile="13928433433";//负责人电话
  

  var params = {
     "userName" : userName,
     "userPhone" : userPhone,
     "carNum" : carNum,
     "address" : address,
     "managerId" : managerId
    };
  
  var successCallback=function(params){
      //返回成功！ respCode=000000
      if(params==1){
      console.log("发送消息成功！");
    }else
      console.log("发送消息失败！");
  };

  getRequestData_POST(reqUrl,params,successCallback);
  
}