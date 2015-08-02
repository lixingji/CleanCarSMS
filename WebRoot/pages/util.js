
/**
 * lixingji on 2015/3/10
 */

// var baseUrl="http://112.74.111.200/CleanCarSMS/";
var baseUrl="http://www.glhyu.cn/CleanCarSMS/";
//var baseUrl="http://localhost:8080/CleanCarSMS/";
var getRequestData_POST=function (reqUrl,params,successCallback) {
        try {
            $.ajax({
                        url : reqUrl,
                        type : "POST",
                        data : params,
                        dataType : "json",//json请求
                        success:successCallback,
                        error:function(params){
                        	myLayer("----网络错误---");
                        	// location.href="http://112.74.111.200:8080/MaiYaServer/MaiYaAdmin/login.html";
                        }
                    });
        } catch (e) {
            // alert("get:"+e.message);
        }
};

var getXMLRequestData_POST=function (reqUrl,params,successCallback) {
        try {
            $.ajax({
                        url : reqUrl,
                        type : "POST",
                        data : params,
                        dataType : "xml",//json请求
                        success:successCallback,
                        error:function(params){
                            alert("----网络错误---");
                            // location.href="http://112.74.111.200:8080/MaiYaServer/MaiYaAdmin/login.html";
                        }
                    });
        } catch (e) {
            // alert("get:"+e.message);
        }
};

var getCookie=function(c_name){
    localStorage.getItem(c_name);
}

var setCookie=function(c_name,value)
{
    localStorage.setItem(c_name,value);
}

//弹出提示框
var myLayer=function(mcontent){
    layer.open({
            content: mcontent,
            style: 'background-color:#09C1FF; color:#fff; border:none;',
            time: 2
        });
}

//弹出页面层  
//num等于1表示选择日期，等于2表示选择时间，等于3表示选择地址
var myPage = function(myHtmlContent){
    var pagei=layer.open({
    type: 1, //1代表页面层
    content: myHtmlContent,
    style: 'width:100%; height:'+ document.documentElement.clientHeight/1.5 +'px; padding:10px; background-color:#FFFFFF; color:#242424; border:none;position:absolute;bottom:0;left:0;',
    success: function(olayer){
        var cla = 'getElementsByClassName';
        olayer[cla]('closediy')[0].onclick = function(){
           /* if(num==1){
                //xiche_date

            }else if(num==2){
                //xiche_time

            }else{
                //xiche_address

            }*/
            //关闭模态框
            layer.close(pagei);
        }
    }
    });
}

