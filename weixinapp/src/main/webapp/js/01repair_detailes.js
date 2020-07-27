//查询出维修记录的详细信息
$(document).ready(function(){

	id=location.search;
	id=id.substring(id.indexOf("=", 0)+1, id.length);
	$.ajax({
		url:basebath+"/selectNewsDetails.do",
		type:"POST",
		data:{
			"id":id
		},
		dataType:"JSON",
		success:function(msg){
			$(msg).each(function(i,e){
				$("#seriano").text(e.seriano);
				$("#facilityName").text(e.facilityName);
				$("#facilityNum").text(e.facilityNum);
				$("#faultTitle").text(e.faultTitle);
				$("#subTime").text(e.subTime);
				if (e.user!=null) {
					$("#userName").text(e.user.name);
				}else{
					$("#userName").text(e.repairName);
				}
				$("#subDeptName").text(e.subDeptName);
				$("#userTell").text(e.mobileTell);
				$("#campusName").text(e.campus.name);
				$("#buildingName").text(e.floorName);
				$("#roomName").text(e.roomName);
				if (e.state==0) {
					$("#state").text("未受理");
				}else if (e.state==1) {
					$("#state").text("已受理");
					$("#call").text("已受理").unbind("click");;
					$("#Myself").hide();
				}else if (e.state==2) {
					$("#state").text("已完成");
					$("#call").text("已完成").unbind("click");
					$("#Myself").hide();
					$(".maincontparent").css("display","block");
					$("#maintenanceContent").text(e.maintenanceContent);
				}else if(e.state==4){
					$("#state").text("未解决");
					$("#call").text("已结束").unbind("click");
					$("#Myself").hide();
					$(".defeatedparent").css("display","block");
					$("#defeatedreason").text(e.defeatedreason);
				}
				if (e.serviceman!=null) {
					$("#serviceManName").text(e.serviceman.name);
					$("#serviceManTell").text(e.serviceman.tell);
					$("#receiptTime").text(e.receiptTime);
					if (e.endTime!=null) {
						$("#endTime").text(e.endTime);
					}
					if (e.charge!=null) {
						$("#charge").text(e.charge+"￥");
					}
				}else if(e.adminbo!=null){
					$("#serviceManName").text(e.adminbo.name);
					$("#serviceManTell").text(e.adminbo.tell);
					$("#receiptTime").text(e.receiptTime);
					if (e.endTime!=null) {
						$("#endTime").text(e.endTime);
					}
					if (e.charge!=null) {
						$("#charge").text(e.charge+"￥");
					}
				}
			});
		}
	});
	
	//点击之后获取维修工人信息
	$("#call").click(function(){
		var serviceMan = "[";
		$.ajax({
			url:basebath+"/getServiceMan.do",
			type:"post",
			data:{
				'id':id,
			},
			dataType:'json',
			success:function(res){
				if(res.success==true){
					$.each(res.data,function(i,e){
						serviceMan=serviceMan+"{text:\""+e.name+"\","+
						"onClick:function(){"+
						"CallServiceMan("+e.id+");"+
						"}},";
					});
					serviceMan=serviceMan.substring(0, serviceMan.length-1);
					serviceMan=serviceMan+"]";
					$.actions({
						actions:eval(serviceMan)
					});
				}else{
					alert("该区域没有维修人员");
				}
			}
		})
	})
	

	function CallServiceMan(servicemanId){
		$.ajax({
			url:basebath+"/callServiceMan.do",
			type:"post",
			data:{
				"repairId":id,
				"servicemanId":servicemanId,
			},
			dataType:"json",
			success:function(res){
				if(res.success){
					alert("指派成功");
					window.location.reload();
				}else{
					alert("指派失败");
				}
			}
		})
	}


	$("#Myself").click(function(){
		$.ajax({
			url:basebath+"/myselfRepair.do",
			type:"post",
			data:{
				'id':id,
			},
			dataType:"json",
			success:function(res){
				if(res.success==true){
					alert("受理成功");
					window.location.reload();
				}
			}
		})
	})

	
});


