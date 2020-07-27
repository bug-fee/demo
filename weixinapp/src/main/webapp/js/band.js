
/**
 * 校区选项
 */
$("#bandselectCam").click(function(){
	var campus="[";
	$.ajax({
		url:basebath+"/selectAllCampus.do",
		dataType:"JSON",
		async: false,
		success:function(msg){
			$(msg).each(function(i,e){
				campus=campus+"{text:\""+e.name+"\","+
				"onClick:function(){" +
				"var val=\""+e.name+"\";" +
				"console.log(\""+e.name+"\");" +
				"$(\"#bandselectCam\").val(\""+e.name+"\");" +
				"$(\"#bandselectCam\").attr(\"cid\",\""+e.id+"\");"+
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
$(function(){
	$("#bandbtn").click(function(){
		var campus = $("#bandselectCam").val();
		var bandname = $("#bandname").val();
		var bandphone = $("#bandphone").val();
		var bandbuilding = $("#bandbuildingname").val();
		var bandroomnum = $("#bandroomnum").val();
		var bandusergender = $("#bandusergender").val();
		var bandtell = $("#workTell").val();
		var bandroomname = $("#bandroomname").val();
		if(campus==""||$.trim(bandname)==""||$.trim(bandphone)==""||$.trim(bandbuilding)==""||$.trim(bandroomnum)==""||$.trim(bandusergender)==""){
			alert("信息不能为空");
		}else{
			if(bandname.length<2||bandname.length>8){
				alert("姓名不合法");
			}else{
				if(bandphone.length!=11){
					alert("手机号不合法");
				}else{
					$.ajax({
						url:basebath+"/bandUser.do",
						type:"post",
						dataType:"text",
						async: false,
						data:{
							"campus.id":$("#bandselectCam").attr("cid"),
							"name":bandname,
							"tell":bandphone,
							"floorName":bandbuilding,
							"roomName":bandroomnum,
							"gender":bandusergender,
							"workTell":bandtell,
							"subDeptName":bandroomname
							
						},
						success:function(msg){
							alert(msg);
							if(msg=="绑定成功"){
								var type=GetQueryString("type");
								if(type==1||type=="1"){
									window.location.href=basebath+"/wx/myRepair_home_01.html";
								}else if(type==2||type=="2"){
									window.location.href=basebath+"/wx/maintain.html";
								}
								
							}
						}
					});
				}
			}
		}
	});
});

/**
 * 性别选项
 */
$("#bandusergender").click(function(){
	$.actions({
		  actions: [{
		    text: "男",
		    onClick: function() {
		     $("#bandusergender").val("男");
		    }
		  },{
		    text: "女",
		    onClick: function() {
		     $("#bandusergender").val("女");
		    }
		  }]
		});
});





