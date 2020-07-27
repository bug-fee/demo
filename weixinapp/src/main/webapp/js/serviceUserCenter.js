var servicemanid;
var servicemancampus;
$(document).ready(function(){
	$.ajax({
		url:basebath+"/service/selserviceusermsg.do",
		type:"get",
		dataType:"json",
		success:function(msg){
			if(msg[0].type=="error"){
				alert("系统繁忙,稍后再试");
			}else{
				servicemanid=msg[0].id;
				servicemancampus=msg[0].campus.name;
				$("#servicemanname").text(msg[0].name);
				$("#serviceauthorized").text(msg[0].authorizedTime);
				$("#servicemanimg").attr("src",msg[0].servicemanimg);
				$("#servicemansex").text(msg[0].gender);
				$("#servicephone").val(msg[0].tell);
				$("#modifcom").val(msg[0].campus.name);
				if(msg[0].state==3){
					$("#modifcom").val(msg[0].newcam.name);
					$("#changesermancampus").text("请耐心等待审核").addClass("weui-btn_disabled");
				}else{
					$("#changesermancampus").click(function(){
						var campustext = $("#modifcom").val();
						if($.trim(campustext)!=null&&$.trim(campustext)!=""){
							$.ajax({
								url:basebath+"/service/changesermanCampus.do",
								data:"campusname="+campustext+"&servicemanid="+servicemanid,
								type:"post",
								dataType:"text",
								success:function(msg){
									alert(msg);
									window.location.href="userCenter_01.html";
								}
							});
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
$("#servicemangender").click(function(){
	$.actions({
		  actions: [{
		    text: "男",
		    onClick: function() {
		      changegender("男");
		    }
		  },{
		    text: "女",
		    onClick: function() {
		    	changegender("女");
		    }
		  }]
		});
});

function changegender(usergender){
	$.ajax({
		url:basebath+"/service/changeservciemangender.do",
		data:"usergender="+usergender+"&userid="+servicemanid,
		dataType:"JSON",
		type:"post",
		dataType:"text",
		success:function(msg){
			//alert(msg);
			window.location.reload();
		}
	});
}

$("#changeservicemanname").click(function(){
	var usernamemsg = $("#newservicemanname").val();
	if($.trim(usernamemsg)!=null||$.trim(usernamemsg)!=""){
		if(usernamemsg.length>5||usernamemsg.length<2){
			alert("请输入有效位数");
		}else{
			$.ajax({
				url:basebath+"/service/changeservicemanname.do",
				data:"usernamemsg="+usernamemsg+"&userid="+servicemanid,
				type:"post",
				dataType:"text",
				success:function(msg){
					alert(msg);
					window.location.href="userCenter_01.html";
				}
			});
		}
	}else{
		alert("请输入姓名");
	}
});
$("#changeservicetell").click(function(){
	var servicemanphone = $("#servicephone").val();
	if($.trim(servicemanphone)!=null||$.trim(servicemanphone)!=""){
		if(servicemanphone.length!=11){
			alert("请输入有效位数");
		}else{
			$.ajax({
				url:basebath+"/service/changeservicemanphone.do",
				data:"adminphone="+servicemanphone+"&servicemanid="+servicemanid,
				type:"post",
				dataType:"text",
				success:function(msg){
					alert(msg);
					window.location.href="userCenter_01.html";
				}
			});
		}
	}else{
		alert("请输入手机号");
	}
});








