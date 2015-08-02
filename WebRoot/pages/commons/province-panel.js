/**
* lixingji on 2015/3/12
* 首页
**/

$(function(){
	init();
});

function init(){
	add_table_td_click();
	$("#_id_back").click(add_btn_back);
}

//返回
function add_btn_back(){
	location.href="../index.html";
}

//点击table的数据
/**
*cookie的使用
**/
function add_table_td_click(){
	$('td').each(function () {
       $(this).click(function(){
	      setCookie('province', $(this).html());
	      location.href="../index.html";
       });
    });
}