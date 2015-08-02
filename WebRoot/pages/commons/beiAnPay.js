/**
* lixingji on 2015/3/13
* 当前订单信息
**/
 
$(function(){
  //直接显示服务金额
  var money=GetQueryString("money");
  $("#serMoney").html(money);
  $("#_id_pay").click(add_btn_pay);
});

//支付
function add_btn_pay(){

  if (typeof WeixinJSBridge == "undefined"){
      if( document.addEventListener ){
             document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
      }else if (document.attachEvent){
             document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
             document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
      }
  }else{
       onBridgeReady();
  } 

  //调用发起支付接口
  function onBridgeReady(){
    //获取url参数
    var appId=GetQueryString("appId");
    var timeStamp=GetQueryString("timeStamp");
    var nonceStr=GetQueryString("nonceStr");
    var myPackage="prepay_id="+GetQueryString("package");
    var paySign=GetQueryString("paySign");
   
    WeixinJSBridge.invoke(
           'getBrandWCPayRequest',{
           "appId" : appId,     //公众号名称，由商户传入     
           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
           "nonceStr" : nonceStr, //随机串     
           "package" : myPackage,     
           "signType" : "MD5",         //微信签名方式:     
           "paySign" :  paySign//微信签名 
       },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
              location.href="http://www.glhyu.cn/CleanCarSMS/pages/commons/paySuccess.html";
           }else if(res.err_msg == "get_brand_wcpay_request:cancel"){     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
              myLayer("已取消支付");
           }else{
              myLayer("支付失败");
           }
       }
   ); 
  }

}

//解析url参数
function GetQueryString(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = window.location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}