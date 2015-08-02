/**
* lixingji on 2015/3/13
* 当前订单信息  userInfo
**/
var urlParams='';
var payParams='';
var payMoney=0;
var flag=0;
myLayer("加数据载中...");//加载中
$(function(){
	init();
});

function init(){
 
  //获取订单信息
	getOrderInfo();
	$("#_id_btn_delete").click(add_btn_delete);//取消订单
	$("#_id_btn_pay").click(add_btn_pay);//调到支付页面
  $("#_id_reset").click(add_btn_reset);//修改订单
}


//显示订单信息
function getOrderInfo(){
  
	var reqUrl=baseUrl+"queryDetail.action";
   
  var orderId=0;
  orderId=localStorage.getItem("xiche_id");

  var params = {
     "id" : orderId
    };
  
  var successCallback=function(params){
    
      if(params!=null){
        if(params.orderState==2){
          $("#mytable").html("此订单已经删除");
        }else{
          payMoney=params.money;

          //显示数据
          $("#_id_orderId").html(params.id);//
          $("#_id_userName").html(params.userName);//
          $("#_id_mobile").html(params.phoneNumber);
          $("#_id_carNum").html(params.carNumber);//
          $("#_id_brand").html(params.brand);//
          $("#_id_address").html(params.address);//
          $("#_id_servtime").html(params.serveTime);//
          $("#_id_ordertime").html(params.orderTime);
          $("#_id_money").html(params.money);
          urlParams=params.brand+";"+params.address+";"+params.carNumber+";"+params.serveTime+";"+params.userName+";"+params.remark+";"+params.id+";1";
          var userInfo=params.userName+";"+params.carNumber+";"+params.address+";"+params.managerId;
          localStorage.setItem("userInfo",userInfo);
          getPayParams(payMoney);
        }
      }else{
        console.log("加载订单出错")
      }
      
  };

  getRequestData_POST(reqUrl,params,successCallback);
  
}

//删除订单按钮
function add_btn_delete(){
  //防止重复按删除键
  if(flag>0){
    return;
  }
  $("#_id_btn_delete").attr("disabled","disabled");
  flag=1;
  var r=confirm("确定要删除订单吗？");
  if (r==true){

        var reqUrl=baseUrl+"cancelOrder.action";
   
        var id=0;
        id=localStorage.getItem("xiche_id");

        var params = {
           "id" : id
          };

        var successCallback=function(params){
          if(params!=0){
            myLayer("订单删除成功");
            setTimeout(function(){
              window.location.reload();
            },1500); 
          }else{
            myLayer("此订单已经删除");
            $("#_id_btn_delete").removeAttr("disabled");
            flag=0;
          }
        }
         getRequestData_POST(reqUrl,params,successCallback);

    }else{
      // alert("You pressed Cancel!");
      $("#_id_btn_delete").removeAttr("disabled");
      flag=0;
    }
}

//跳到支付页面
function add_btn_pay(){
	// location.href="https://open.weixin.qq.com/connect/oauth2/autorize?appid=wxe3640a62cec23aba&redirect_uri=http://glhyu.com/pages/commons/ePay.html&response_type=code&scope=snsapi_base&state=12#wechat_redirect";
  location.href="http://www.glhyu.cn/CleanCarSMS/pages/commons/beiAnPay.html?"+payParams;
  // location.href="http://glhyu.com/pages/commons/beiAnPay.html?"+payParams;
}

//修改订单
function add_btn_reset(){
  localStorage.setItem("xiche_modifyOrder",null);//如果存在已修改订单状态，将订单状态置空
  location.href="reset-order.html?"+encodeURI(urlParams);
}


//获取支付所需信息
function getPayParams(mm){
  // myLayer("支付金额："+mm);
  var code=GetQueryString("code");//获取url中的参数code
  //alert(code);//url,查看有没有code

  if(code==''||code==null){
    return;
  }
  var orderId=0;
  orderId=localStorage.getItem("xiche_id");

  //请求获取payParams
  var reqUrl=baseUrl+"getBrandWCPayRequest.action";
  //参数列表 
  var params = {
    "code":code,
    "orderId":orderId,
    "money":mm
    };
    
  var successCallback=function(params){
      if(params!=null){
        payParams="appId="+params.appId+"&timeStamp="+params.timeStamp+"&nonceStr="+params.nonceStr+"&package="+params.packageValue+"&paySign="+params.paySign+"&money="+payMoney;
        localStorage.setItem("xiche_payParams",payParams);
      }
  };

  getRequestData_POST(reqUrl,params,successCallback);

}

//解析url参数
function GetQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = window.location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}
  
  
