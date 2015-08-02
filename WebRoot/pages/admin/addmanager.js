/**
* lixingji on 2015/4/21
* 负责人列表
**/
var managerId='';
var flag=0;
var mmmProvince="广东";
var mmmCity="";
var mmmName="";
var mmmPhone="";
var myLocation=location.href;

$(function(){
	init();
});

function init(){
	loadManagerList();
	$("#_id_add_manager").click(add_btn_summit);
}


//加载负责人信息的列表信息
function loadManagerList(){

	var reqUrl=baseUrl+"loadManagerList.action";
	var params = {
		};

		var successCallback=function(params){
/**
** <tr>
         <td>广东</td>
          <td>深圳</td>
           <td>黎兴济</td>
            <td>18825111065</td>
            <td><input type="button" id="_id_update" value="修改负责人信息"><input type="button" id="_id_add_detail" value="添加具体负责地区"></td>
      </tr>
**/
			if(params!=null){
				var strHtml='';
				for(var i=0;i<params.length;i++){
					strHtml+='<tr><td>'+params[i].province+'</td><td>'+params[i].city+'</td><td>'+params[i].managerName+'</td><td>'+params[i].managerPhone+'</td><td><input type="button" style="color:red;" onclick="deleteManager('+params[i].id+')" value="删除"><input type="button" id="_id_update_'+i+'" onclick="add_btn_update('+params[i].id+');" value="修改负责人信息"><input type="button" id="_id_add_detail_'+i+'"  onclick="addDetail('+params[i].id+','+params[i].managerPhone+')" value="添加具体负责地区"></td></tr>';
				}
				$("#listDetail").html(strHtml);
              }else{
              	$("#listDetail").html("暂无负责人信息！");
              }
              flag=0;
	};

	getRequestData_POST(reqUrl,params,successCallback);
}

//删除负责人
function deleteManager(id){
    var reqUrl=baseUrl+"deleteManager.action";
	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	var params = {
		 "id" : id
		};

		var successCallback=function(params){

			if(params==1){
                  myLayer("删除成功!!!");
                  setTimeout(function(){
                  	location.reload();
                  },1500);
                  
              }else if(params==0){
              	myLayer("删除失败 ！您尚未登陆！");
              }else{
              	myLayer("删除失败 ！！！");
              }
              flag=0;
	    };

	getRequestData_POST(reqUrl,params,successCallback);

}
//点击添加负责人的提交按钮
function add_btn_summit(){
	var reqUrl=baseUrl+"addManager.action";

    var myProvince=$("#myProvince").val();
	var myCity=$("#myCity").val();
	var managerName=$("#_id_managerName").val();
	var managerPhone=$("#_id_managerPhone").val();

	if(flag>0){
    	return;
    }
    flag=1;//防止重复提交
	if(managerName==''){
		myLayer("负责人姓名不能为空！");
		flag=0;
		return;
	}
	if(managerPhone==''){
		myLayer("负责人电话不能为空！");
		flag=0;
		return;
	}
	var params = {
		 "am.province" : myProvince,
		 "am.city":myCity,
		 "am.managerName":managerName,
		 "am.managerPhone":managerPhone
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

//更新负责人信息
function add_btn_update(id){
	managerId=id;
	//赋值
	//mmmProvince=province;mmmCity=city;mmmName=managerName;mmmPhone=managerPhone;
	var page_sure_btn='<button class="closediy" style="width:10%;background-color:#00EE00;border:none;margin-top:13px;padding:10px 0;font-size:20px;">确  定</button>';

	var strHtml='<html><div style="margin-top:60px;">.</div><p>省份：（如：广东）</p><input id="_id_update_province" placeHolder="请输入省份" type="text"><p>城市：（如：深圳）</p><input id="_id_update_city" placeHolder="请输入城市" type="text"><p>负责人姓名：</p><input id="_id_update_name" placeHolder="请输入负责人姓名" type="text"><p>负责人手机号：</p><input id="_id_update_phone" placeHolder="请输入负责人手机号" type="text"><p>'+page_sure_btn+'</p></html>';
	myFullPage(strHtml);
}

//添加详情  id为manager的id
function addDetail(id,mPhone){
	// alert(id);
	location.href="addSerAddress.html?"+id+";"+mPhone;
}

//弹出覆盖整个页面层  
function myFullPage(myHtmlContent){
    var pagei=layer.open({
    type: 1, //1代表页面层
    content: myHtmlContent,
    style: 'width:100%; height:'+ document.documentElement.clientHeight +'px; padding:10px; background-color:#FFFFFF; color:#242424; border:none;position:absolute;bottom:0;left:0;',
    success: function(olayer){
    	showInitInfo();
        var cla = 'getElementsByClassName';
        olayer[cla]('closediy')[0].onclick = function(){
        	loadUpdateInfo();
            //关闭模态框
            layer.close(pagei);
        }
    }
    });
}

//显示原有信息
function showInitInfo(){
	$("#_id_update_province").html(mmmProvince);
	$("#_id_update_city").html(mmmCity);
	$("#_id_update_name").html(mmmName);
	$("#_id_update_phone").html(mmmPhone);
}
//reload更新的内容
function loadUpdateInfo(){
	var reqUrl=baseUrl+"updateManager.action";

    var myProvince=$("#_id_update_province").val();
	var myCity=$("#_id_update_city").val();
	var managerName=$("#_id_update_name").val();
	var managerPhone=$("#_id_update_phone").val();

    if(myProvince==''){
		myLayer("省份不能为空！");
		return;
	}
	if(myCity==''){
		myLayer("城市不能为空！");
		return;
	}
	if(managerName==''){
		myLayer("负责人姓名不能为空！");
		return;
	}
	if(managerPhone==''){
		myLayer("负责人电话不能为空！");
		return;
	}
	var params = {
		 "am.id" : managerId,
		 "am.province" : myProvince,
		 "am.city":myCity,
		 "am.managerName":managerName,
		 "am.managerPhone":managerPhone
		};

		var successCallback=function(params){

			if(params==1){
                  myLayer("更新成功!!!");
                  setTimeout(function(){
                  	location.reload();
                  },1500);
                  
              }else if(params==0){
              	myLayer("更新失败 ！您尚未登陆！");
              }else{
              	myLayer("更新失败 ！！！");
              }
              flag=0;
	    };

	getRequestData_POST(reqUrl,params,successCallback);
}
