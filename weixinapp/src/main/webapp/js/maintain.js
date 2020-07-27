//动态添加校区
$("#campus").click(function(){
	var campus="[";
	$.ajax({
		url:basebath+"/selectAllCampus.do",
		dataType:"JSON",
		success:function(msg){
			$(msg).each(function(i,e){
				campus=campus+"{text:\""+e.name+"\","+
				"onClick:function(){" +
				"var val=\""+e.name+"\";" +
				"console.log(\""+e.name+"\");" +
				"$(\"#campus\").val(\""+e.name+"\");" +
				"$(\"#campus\").attr(\"cid\",\""+e.id+"\");"+
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

function tosubmit(){
	var uri=window.location.pathname;
		uri=uri.substring(uri.lastIndexOf("/", uri.length-1)+1, uri.length);  
		if ($("#faultTitle").val()==null||$("#faultTitle").val()=="") {
			alert("故障标题不能为空!!");
		}else if ($("#facilityName").val()==null||$("#facilityName").val()=="") {
			alert("设备名称不能为空!!");
		}else if ($("#facilityNum").val()==null||$("#facilityNum").val()=="") {
			alert("设备数量不能为空!!");
		}else if (isNaN($("#facilityNum").val())) {
			alert("设备数量必须为数字!!");
		}else if ($("#faultDescribe").val()==null||$("#faultDescribe").val()=="") {
			alert("故障描述不能为空!!");
		}else if (uri=="other_maintain.html"&&($("#campus").val()==null||$("#campus").val()=="")) {
			alert("校区不能为空!!");
		}else if (uri=="other_maintain.html"&&($("#floorName").val()==null||$("#floorName").val()=="")) {
			alert("楼名不能为空!!");
		}else if (uri=="other_maintain.html"&&($("#roomName").val()==null||$("#roomName").val()=="")) {
			alert("房间号不能为空!!");
		}else if (uri=="other_maintain.html"&&($("#mobileTell").val()==null||$("#mobileTell").val()=="")) {
			alert("联系电话不能为空!!");
		}else if (uri=="other_maintain.html"&&($("#repairName").val()==null||$("#repairName").val()=="")) {
			alert("报修人姓名不能为空!!");
		}else {
			$.ajax({
				url:basebath+"/insertRepair.do",
				type:"POST",
				data:{
					"repairName":$("#repairName").val(),
					"faultTitle":$("#faultTitle").val(),
					"facilityName":$("#facilityName").val(),
					"facilityModel":$("#facilityModel").val(),
					"facilityNum":$("#facilityNum").val(),
					"faultDescribe":$("#faultDescribe").val(),
					"floorName":$("#floorName").val(),
					"roomName":$("#roomName").val(),
					"workTell":$("#workTell").val(),
					"mobileTell":$("#mobileTell").val(),
					"campus.id":$("#campus").attr("cid"),
					"subDeptName":$("#subDeptName").val()
					
				},
				success:function(msg){
					if (msg=="success") {
						alert("报修成功,请耐心等待维修人员受理...");
						WeixinJSBridge.call('closeWindow');
					}else{
						alert("服务器出错了,请稍后再试...");
					}
				}
			});
		}
}
