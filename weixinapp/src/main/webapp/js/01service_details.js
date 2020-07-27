//动态添加校区
$("#aaplifcom").click(function(){
	var campus="[";
	$.ajax({
		url:basebath+"/selectAllCampus.do",
		dataType:"JSON",
		success:function(msg){
			$(msg).each(function(i,e){
				campus=campus+"{text:\""+e.name+"\","+
				"onClick:function(){" +
				"var val=\""+e.name+"\";" +
				"$(\"#aaplifcom\").val(\""+e.name+"\");" +
				"$(\"#aaplifcom\").attr(\"cid\",\""+e.id+"\");"+
				"}},";
			});
			campus=campus.substring(0, campus.length-1);
			campus=campus+"]";
			
			$.actions({
				actions:eval(campus)
			});
			
		}
	});
	
});
//提交申请,如果成功,前往首页
$(".namebutton").click(function() {
	$.ajax({
		url:basebath+"/service/addServiceMan.do",
		type:"POST",
		data:"campusId="+$("#aaplifcom").attr("cid"),
		success:function(msg){
			
			if (msg=="success") {
				alert("申请成功,请等待管理员审核");
				window.location.href=basebath+"/wx/myRepair_home_01.html";
			}else if (msg=="unUser") {
				alert("系统繁忙,请退出后重试");
			}else{
				alert("抱歉,申请失败,请稍后再试");
			}
		}
	})
})