var searchstate=0;//0 表示按照选择的搜索类型进行数据展示，1表示不按照搜索类别进行展示
var flage;
var state;//维修状态
var page;//页码
var textvalue;//文本框的内容
var showtype;//显示不同类型的数据
var loading = false;  //状态标记
var offset;//分页的开始位置
$(document).ready(function(){
	showtype=3;
	flage=null;
	page=1;
	init(showtype,page);
});

//滚动加载更多
$(document.body).infinite().on("infinite", function() {
  if(loading) return;
  loading = true;
  page++;
  setTimeout(function() {
	  offset=(page-1)*5;
	  selectBytext(flage, textvalue, offset,showtype,1);
  }, 1500);   //模拟延迟
});
/**
 * 访问该界面进行数据的初始化
 */
function init(showtype,page) {
	offset=(page-1)*5;
	$.ajax({
		url:basebath+"/initRepair.do",
		type:"POST",
		data:{"showtype":showtype,"offset":offset},
		dataType:"json",
		success:function(msg){
			console.log(msg);
			var lengh=0;
			$(msg).each(function(i,e){
				if (e.infor!=false) {
					lengh++;
					commons(e);
					if (lengh%5==0) {
						$("#add").show();
					}else {
						$("#add").hide();
					}
				}else{
					alert("系统繁忙，请稍后再试!!!");
				}
				
			});
			
		}
	});
}
/**
 * 标签页切换
 */
$(".centire strong").click(function(){
	$(".bluer").removeClass("bluer").addClass("linear");
	$(this).next().removeClass("linear").addClass("bluer");
});
/**
 * 搜索类别
 */
$("#searchInput").parent().click(function(e){
	searchstate=1;
	page=1;
	$("#searchCancel").show();
	$("#searchcommit").hide();
	$.actions({
		  title:"请选择搜索类别",
		  actions: [{
			    text: "设备名称",
			    onClick: function() {
			    	flage=1;
			      $("#searchInput").removeAttr("readonly").attr("placeholder","请输入设备名称");	
			      $("#searchInput").focus();
			      $("#searchCancel").hide();
			      $("#searchcommit").show();
			      
			    }
			  },
			  {
			    text: "编号",
			    onClick: function() {
			    	flage=2;
			      $("#searchInput").removeAttr("readonly").attr("placeholder","请输入设备");
			      $("#searchInput").focus();
			      $("#searchCancel").hide();
			      $("#searchcommit").show();
			      
			    }
				},
				{
				    text: "故障标题",
				    onClick: function() {
				    	flage=3;
				       $("#searchInput").removeAttr("readonly").attr("placeholder","请输入故障标题");;	
				       $("#searchInput").focus();
				       $("#searchCancel").hide();
					   $("#searchcommit").show();
					
				    }
			}
		  ]
		});
});


/**
 * 搜索按钮的点击事件
 */
$("#searchcommit").click(function(){
	textvalue=$.trim($("#searchInput").val());
	if (textvalue!="") {
		selectBytext(flage, textvalue,0,showtype);
	}
});


/**
 * 显示不同状态的数据
 */
$("#all").bind("click", function(){
	searchstate=0;	
	showtype=3;
	page=1;
		$("#add").hide();
		$("#content_repair").html("");
		init(showtype,page);
});
$("#apply").bind("click", function(){
	searchstate=0;	
	showtype=0;
	page=1;
		$("#add").hide();
		 $("#content_repair").html("");
		init(showtype, page);
});
$("#acccepted").bind("click", function(){
	searchstate=0;
	showtype=1;
	page=1;
		$("#add").hide();
		 $("#content_repair").html("");
		init(showtype, page);
});
$("#end").bind("click", function(){
	searchstate=0;
	showtype=2;
	page=1;
		$("#add").hide();
		 $("#content_repair").html("");
		init(showtype, page);
});
/**
 * 根据搜索框内容进行模糊查询
 */
function selectBytext(flage,textvalue,page,show,style){
	offset=$(".item").length;
	if(!style||style!=1){
		$("#content_repair").html("");
	}
    $("#add").hide();
	$.ajax({
		url:basebath+"/selectBytext.do",
		type:"POST",
		async:false,
		data:{"flage":flage,"textvalue":textvalue,"offset":page,"showtype":show},
		dataType:"json",
		success:function(msg){
			var lengh=0;
			$(msg).each(function(i,e){
				if (e.infor!="false") {
					lengh++;
					commons(e);
					if (lengh%5==0) {
						$("#add").show();
					}else {
						$("#add").hide();
					}
				}
			});
			
		}
	});
}
/**
 * 公用的方法，进行数据的动态拼接
 */
function commons(e) {
	$("#add").hide();
		//根据不同的状态调用不同的拼接方法
		var state=e.state;
		if (state==0) {
			applay(e);
		}
		if (state==1) {
			noapplay(e);
		}
		if (state==2) {
			noapplay(e);
		}
}
//处于申请状态的动态拼接方法
function applay(e) {
	var state=e.state;
	var $itme=$("<div class=\"item\" onClick=\"window.location.href='01repair_detailes.html?id="+e.id+"'\"></div>");
	var $title=$("<div class=\"weui-cells notopmar hasbotop\"></div>");
	var $item_box=$("<div class=\"weui-form-preview\"></div>");
	var $item_box_1=$("<div class=\"weui-form-preview__bd\"><div>");
	var $bottom_box=$("<div class=\"weui-cells notopmar hasbottom\"></div>");
	var $bottom_box_1=$("<div class=\"weui-cell\"></div>");
	var $facilitynumber_div=$("<div class=\"weui-cell\"><div class=\"weui-cell__bd\">"+"<p>"+"编号:"+e.seriano+"</p></div></div>");
	var $state_div=$("<div class=\"weui-cell__ft\" style=\"color:#FDB4A2\"></div>");
	$state_div.text("申请中");
	var $facilityName_div=$("<div class=\"weui-form-preview__item\" id=\"facilityName\">"+
			"<label class=\"weui-form-preview__label\">"+"设备名称:"+"</label>"+"<span class=\"weui-form-preview__value\">"+
			"<strong>"+e.facilityName+"</strong>"+"</span></div>");
	var $faultTitle_div=$("<div class=\"weui-form-preview__item\" id=\"faultTitle\">"+
			"<label class=\"weui-form-preview__label\">"+"故障标题:"+"</label>"+"<span class=\"weui-form-preview__value\">"+
			"<strong>"+e.faultTitle+"</strong>"+"</span></div>");
	var $bootm_div1=$("<div class=\"weui-cell__bd\"><p></p></div>");
	$facilitynumber_div.append($state_div);
	$title.append($facilitynumber_div);
	$item_box_1.append($facilityName_div);
	$item_box_1.append($faultTitle_div);
	$item_box.append($item_box_1);
	$bottom_box_1.append($bootm_div1);
	$bottom_box.append($bottom_box_1);
	$itme.append($title);
	$itme.append($item_box);
	$itme.append($bottom_box);
	$("#content_repair").append($itme);
	loading = false;
}
//处于已受理和已结束状态的动态拼接方法
function noapplay(e) {
	var state=e.state;
	var $itme=$("<div class=\"item\" onClick=\"window.location.href='01repair_detailes.html?id="+e.id+"'\"></div>");
	var $title=$("<div class=\"weui-cells notopmar hasbotop\"></div>");
	var $item_box=$("<div class=\"weui-form-preview\"></div>");
	var $item_box_1=$("<div class=\"weui-form-preview__bd\"><div>");
	var $bottom_box=$("<div class=\"weui-cells notopmar hasbottom\"></div>");
	var $bottom_box_1=$("<div class=\"weui-cell\"></div>");
	var $facilitynumber_div=$("<div class=\"weui-cell\"><div class=\"weui-cell__bd\">"+"<p>"+"编号:"+e.seriano+"</p></div></div>");
	var $state_div=$("<div class=\"weui-cell__ft\" style=\"color:#FDB4A2\"></div>");
	if (state==1) {
		$state_div.text("已受理");
	}
	if (state==2) {
		$state_div.text("已结束");
	}
	if(e.serviceman!=null){
		name=e.serviceman.name;
		tell=e.serviceman.tell
	}else{
		name=e.repairName;
		tell=e.mobileTell;
	}
	var $facilityName_div=$("<div class=\"weui-form-preview__item\" id=\"facilityName\">"+
			"<label class=\"weui-form-preview__label\">"+"设备名称:"+"</label>"+"<span class=\"weui-form-preview__value\">"+
			"<strong>"+e.facilityName+"</strong>"+"</span></div>");
	var $faultTitle_div=$("<div class=\"weui-form-preview__item\" id=\"faultTitle\">"+
			"<label class=\"weui-form-preview__label\">"+"故障标题:"+"</label>"+"<span class=\"weui-form-preview__value\">"+
			"<strong>"+e.faultTitle+"</strong>"+"</span></div>");
	var $serviceman_div=$("<div class=\"weui-form-preview__item\" id=\"serviceman\">"+"<label class=\"weui-form-preview__label\">"+"维修人员:"+
			"</label>"+"<span class=\"weui-form-preview__value\">"+"<strong>"+name+"</strong>"+"</span></div>");
	var $servicemantell_div=$("<div class=\"weui-form-preview__item\" id=\"servicemantell\">"+"<label class=\"weui-form-preview__label\">"+"联系电话:"+"</label>"+
			"<span class=\"weui-form-preview__value\">"+"<strong>"+tell+"</strong>"+"</span></div>");
	var $bootm_div1=$("<div class=\"weui-cell__bd\"><p></p></div>");
	$facilitynumber_div.append($state_div);
	$title.append($facilitynumber_div);
	$item_box_1.append($facilityName_div);
	$item_box_1.append($faultTitle_div);
	$item_box_1.append($serviceman_div);
	$item_box_1.append($servicemantell_div);
	$item_box.append($item_box_1);
	$bottom_box_1.append($bootm_div1);
	$bottom_box.append($bottom_box_1);
	$itme.append($title);
	$itme.append($item_box);
	$itme.append($bottom_box);
	$("#content_repair").append($itme);
	 loading = false;
}