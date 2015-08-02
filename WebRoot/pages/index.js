/**
* lixingji on 2015/3/12
* 首页
**/
var carNum='';

$(function(){
	init();
});

function init(){
	getProvince();
	$("#_id_btn_province").click(add_btn_province);
	$("#_id_btn_sure").click(add_btn_sure);
	$("#_id_car_num").focus(function(){
		// $("#_id_car_num").val("A"); 
	    $("#_id_car_num").keyup(function(event) {   
	    	var keyCode= event.keyCode;
	    	var realkey= String.fromCharCode(keyCode);
	    	if(realkey.match(/[a-zA-Z]+/))
	    	   realkey = realkey.toUpperCase();
	    	var thisVal=$(this).val().toUpperCase();
	    	carNum=thisVal;
	    	//在css中转为大写
            // var realkey = String.fromCharCode(keyCode).toUpperCase();
	            /*var thisVal=$(this).val().toUpperCase();
	            $(this).val(thisVal);
	            carNum=thisVal;*/
	    });  
	});
}

//选择省份按钮
function add_btn_province(){
	location.href="commons/province-panel.html";
}

//预加载省份
function getProvince(){
	localStorage.setItem("xiche_id",null);//如果存在订单id，将订单id置空
	localStorage.setItem("xiche_modifyOrder",null);//如果存在已修改订单状态，将订单状态置空
	var province=localStorage.getItem("province");
	if(province!=''&&province!=null)
		$("#_id_btn_province").html(province);
	else{
		$("#_id_btn_province").html("粤");
	}
}

//点击确认按钮
function add_btn_sure(){

	if(carNum==''){
		myLayer("请填写车牌号码");
		return;
	}

	if(carNum.length<6){
		myLayer("车牌号码位数不够");
		return;
	}

	if($("#id_car_logo").val()==''){
		myLayer("请填写车辆品牌!");
		return;
	}

    //url传参
	var urlPar="?"+$("#_id_btn_province").html()+carNum+";"+$("#id_car_logo").val();
	location.href="commons/service.html"+encodeURI(urlPar);
}

//车牌号验证
function add_btn_carNum(){
	alert("hello");
	/*carNum=$("#_id_car_num").val();
	if(carNum!=''&&!carNum.match(/^[A-Z]{1}[A-Z0-9]{5}$/)){
        myLayer("车牌号格式不对，请正确输入！");
        $("#_id_car_num").val('');
    	return;
	}*/
}