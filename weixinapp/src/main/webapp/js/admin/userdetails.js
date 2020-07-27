$(document).ready(function(){
var id=	GetQueryString("id");
$.ajax({
	url:basebath+"/selectServiceman.do",
	data:"id="+id,
	dataType:"JSON",
	type:"POST",
	success:function(msg){
		$(msg).each(function(i,e){
			$("#userImg").attr("src",e.headimgurl);
			$("#name p").text("姓名:"+e.name);
			$("#phone p").text("电话:"+e.tell);
			$("#date p").text("授权日期:"+e.authorizedTime);
			$("#area p").text("管辖区域:"+e.campus.name);
		});
	}
});
});
$("#cancle").bind("click",function(){
	var id=	GetQueryString("id");
	 var authou= window.confirm("确认取消授权该维修人员吗");
	 if (authou) {
		 $.ajax({
				url:basebath+"/updateServiceman.do",
				data:"id="+id+"&state="+2,
				dataType:"JSON",
				type:"POST",
				success:function(msg){
					$(msg).each(function(i,e){
						var infor=e.state;
						if (infor=="success") {
							alert("取消成功!!!");
							window.location.href="usermanage_01.html";
						}
					});
				}
			});
	}
});
//获取请求中的参数值
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
