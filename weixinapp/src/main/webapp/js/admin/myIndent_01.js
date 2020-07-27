
/**
 * 页面初始化默认加载5条记录，不区分维修状态
 * 下拉刷新
 */
loading=false;
var facilityName="";
var seriano="";
var faultTitle="";
$(document).ready(function(){
	commons("","","",0,5,3,"DESC");
	$(document.body).infinite(0).on("infinite", function() {
		if(loading){
			return false;
		}else{
			$(".weui-loadmore").css("display","block");
			loading = true;
			setTimeout(function() {
				var state=$(".bluer").attr("val");
				var offset=$(".item").length;
				commons(facilityName,seriano,faultTitle,offset,5,state,"DESC");
				$(".weui-loadmore").css("display","none");
			},1000);
		}
	});
});
/**
 * 通用查询方法
 * @param facilityName 设备名称
 * @param facilityModel 设备编号
 * @param faultTitle 故障标题
 * @param offset 分页起始
 * @param limit 分页结束
 * @param sate 维修状态：0未受理，1处理中，2已结束，3全部
 */
function commons(facilityName,seriano,faultTitle,offset,limit,state,order){
	$.ajax({
		url:basebath+"/selectAllrepair.do",
		
		data:{"facilityName":facilityName,"seriano":seriano,"faultTitle":faultTitle,"offset":offset,"limit":limit,"state":state,"order":order},
		dataType:"json",
		type:"POST",
		async:false,
		success:function(data){
			if(data.success==false){
				loading=true;
				return ;
			}
			if(data.success==true){
				var result=data.data;
				if(result.length>5) $("#loadmore").css("display","block");
				$(result).each(function(i,e){
					var state="";
					if(e.state==0) state="待受理";
					if(e.state==1) state="处理中";
					if(e.state==2) state="已完成";
					if(e.user==null){
						var name=e.repairName;
						var phone=e.mobileTell;
					}else{
						var name=e.user.name;
						var phone=e.user.tell;
					}
					var item=$("<div class='item'>"+
										"<div class='weui-cells notopmar hasbotop'>"+
										  "<div class='weui-cell'>"+
										    "<div class='weui-cell__bd'>"+
										      "<p>编号："+e.seriano+"</p>"+
										    "</div>"+
										    "<div class='weui-cell__ft' style='color:#FDB4A2'>"+state+"</div>"+
										  "</div>"+
										"</div>"+
										"<div class='weui-form-preview' onclick='detailid("+e.id+")'>"+
										  "<div class='weui-form-preview__bd'>"+
										    "<div class='weui-form-preview__item'>"+
										      "<label class='weui-form-preview__label'>设备名称：</label>"+
										      "<span class='weui-form-preview__value'><strong>"+e.facilityName+"</strong></span>"+
										    "</div>"+
										    "<div class='weui-form-preview__item'>"+
										      "<label class='weui-form-preview__label'>故障标题：</label>"+
										      "<span class='weui-form-preview__value'><strong>"+e.faultTitle+"</strong></span>"+
										    "</div>"+
										    "<div class='weui-form-preview__item'>"+
										      "<label class='weui-form-preview__label'>申报人：</label>"+
										      "<span class='weui-form-preview__value'><strong>"+name+"</strong></span>"+
										    "</div>"+
										    "<div class='weui-form-preview__item'>"+
										      "<label class='weui-form-preview__label'>联系电话：</label>"+
										      "<span class='weui-form-preview__value'><strong>"+phone+"</strong></span>"+
										    "</div>"+
										  "</div>"+
										"</div>"+
									"</div>");
					$(".content").append(item);
				});
				loading=false;
			}
		}
	});
}

/**
 * 搜索框
 */
var off=false;
$("#searchInput").click(function(){
	if(off){
		off=true;
		return;
	}
	$("#searchInput").attr("readonly","readonly");
	$.actions({
		  title:"请选择搜索类别",
		  actions: [{
			    text: "设备名称",
			    onClick: function() {
			    	searchtype("设备名称");
			    }
			  },
			  {
			    text: "编号",
			    onClick: function() {
			    	searchtype("编号");
			    }
				},
				{
				    text: "故障标题",
				    onClick: function() {
				    searchtype("故障标题");
				    }
			}
		  ]
		});
});


/**
 * 搜索类别
 */
function searchtype(type){
	$("#searchInput").attr("placeholder",type);
	$("#searchInput").removeAttr("readonly");
	$("#searchInput").trigger("focus");
	$("#searchInput").keyup(function(){
		var inputval=$.trim($(this).val());
		if(inputval.length>0){
			$("#searchCancel").text("搜索");
			$("#searchCancel").unbind("click").click(function(){
				$(".item").remove();
				var state=$(".bluer").attr("val");
				if(type=="设备名称"){
					facilityName=inputval;
					commons(facilityName,"","",0,5,state,"DESC");
				}else if(type=="编号"){
					seriano=inputval;
					commons("",seriano,"",0,5,state,"DESC");
				}else if(type="故障标题"){
					faultTitle=inputval;
					commons("","",faultTitle,0,5,state,"DESC");
				}
				
			});
		}
	});
}
/**
 * 切换标签页加载指定状态维修记录
 */
$("#repairing,.serianutit").click(function(){
	faultTitle="";
	seriano="";
	facilityName="";
	$(".item").remove();
	var state=$(".bluer").attr("val");
	commons("","","",0,5,state,"DESC");
});

/**
 * 查看详情页面url
 * @param id 报修记录id
 */
function detailid(id){
	location.href="01repair_detailes.html?id="+id;
}