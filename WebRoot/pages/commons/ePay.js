/**
* lixingji on 2015/3/13
* 当前订单信息
**/

$(function(){
	init();
});

function init(){
     getOpenID();
     $("#_id_pay").click(add_btn_pay);
}

//获取OpenID
function getOpenID(){

  var urlParams=location.href;
  var p=urlParams.split("?")[1];//第二个参数 
  var codes=p.split("&")[0];//第0个参数
  var code=codes.split("=")[1];//第1个参数 得到url转发过来的code值
  alert(urlParams);//url,查看有没有code

  //请求获取openID
  var reqUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe3640a62cec23aba&secret=d4985afccee56fb347fcc14782f83af7&code="+code+"&grant_type=authorization_code";
  //参数列表 
  var params = {
    };
    
    alert("获取OpenID:"+code);
    var successCallback=function(params){
      alert(JSON.stringify(params));
      openID=params.openid;
      alert(openID);//成功获得openid
  };

  getRequestData_POST(reqUrl,params,successCallback);

}

//生成预支付订单
function createPreOrder(){

}

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
    var myPackage=GetQueryString("package");
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
              location.href="paySuccess.html";
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