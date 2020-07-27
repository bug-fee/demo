/**
 * 初始化报修记录信息
 */
$(document).ready(function(){
	var id=GetQueryString("id");
	$.ajax({
		url:basebath+"/service/searchRepairs.do",
		data:{"id":id},
		type:"post",
		dataType:"JSON",
		success:function(data){
			if(data.success==false){
				alert("检索失败");
			}
			if(data.success==true){
				var e=data.data[0];
				$("#seriano").text(e.seriano);
				$("#facilityName").text(e.facilityName);
				$("#faultTitle").text(e.faultTitle);
				$("#subTime").text(e.subTime);
				$("#facilityNum").text(e.facilityNum);
				if(e.user!=null) {
					$("#userName").text(e.user.name);
				}else{
					$("#userName").text(e.repairName);
				}
				if(e.subDeptName!=null) $("#subDeptName").text(e.subDeptName);
				$("#userTell").text(e.mobileTell)
				if(e.campus!=null){
					$("#campus").text(e.campus.name);
				}
				$("#floorName").text(e.floorName);
				$("#roomName").text(e.roomName);
				$("#faultDescribe").text(e.faultDescribe);
				if(e.defeatedreason!=null) $("#defeatedreason").text(e.defeatedreason);
				var state="";
				if(e.state==0) state="未受理";
				if(e.state==1) state="处理中";
				if(e.state==2) state="已完成";
				$("#state").text(state);
				if(e.serviceman!=null){
					$("#servicemanName").text(e.serviceman.name);
					$("#servicemanTell").text(e.serviceman.tell);
					$("#receiptTime").text(e.receiptTime);
				}
				else if(e.adminbo!=null){
					$("#servicemanName").text(e.adminbo.name);
					$("#servicemanTell").text(e.adminbo.tell);
					$("#receiptTime").text(e.receiptTime);
				}
				if(e.endTime!=null) $("#endTime").text(e.endTime);
				if(e.charge!=null) $("#charge").text(e.charge+"￥");
				if(e.state==0){
					$("#submm").text("受理");
					$("#submm").click(function(){
						var id=e.id;
						commons(id,1,null,"","");
					});
				}
				if(e.state==1){
					$(".upd").css("display","block");
					$("#submm").text("完成报修");
					var id=e.id;
					$("#submm").click(function(){
						var chargeinput=$("#chargeinput").val();
						var repaircontent=$("#repaircontent").val();
						commons(id,2,chargeinput,"",repaircontent);
					});
					$("#uncompleted").click(function(){
						var defenput=$("#defenput").val();
						commons(id,4,null,defenput,"");
					});
				}
				if(e.state==2||e.state==4){
					$("#submm").text("报修已结束");
					$("#submm").attr({"disabled":"disabled"});
				}
			}
		}
	});
});

/**
 * 修改维修状态
 * @param id 记录id
 * @param state 维修状态
 * @param charge 维修费用
 * @param defeatedreason 未完成维修原因
 */
function commons(id,state,charge,defeatedreason,maintenanceContent){
	$.ajax({
		url:basebath+"/service/updateRepairs.do",
		data:{"id":id,"state":state,"charge":charge,"defeatedreason":defeatedreason,"maintenanceContent":maintenanceContent},
		dataType:"JSON",
		type:"POST",
		success:function(data){
			if(data.success){
				location.href=basebath+"/wx/service/myRepair_home_01.html";
			}else{
				alert("网络繁忙请稍后重试");
			}
		}
	});
}