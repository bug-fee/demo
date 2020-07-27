userid="";
$(document).ready(function(){
	$.ajax({
		url:basebath+"/selusermsg.do",
		type:"get",
		dataType:"json",
		success:function(msg){
			if(msg!=""){
				$("#username").text(msg[0].name);
				$("#useridentcon").text(msg[0].createTime);
				$("#usersex").text(msg[0].gender);
				$("#userimg").attr("src", msg[0].userimgUrl);
				$("#modifcom").val(msg[0].campus.name);
				$("#userphone").val(msg[0].tell);
				$("#username2").val(msg[0].name);
				userid = msg[0].id;
			}
		}
	});
});

$("#changecampus").click(function(){
	var campustext = $("#modifcom").val();
	if($.trim(campustext)!=null||$.trim(campustext)!=""){
		$.ajax({
			url:basebath+"/changeCampus.do",
			data:{"campusname":campustext,"userid":userid},
			type:"post",
			dataType:"text",
			success:function(msg){
				alert(msg);
				window.location.href="userCenter_01.html";
			}
		});
	}
});
$("#changetell").click(function(){
	var usertell = $("#userphone").val();
	if($.trim(usertell)!=null||$.trim(usertell)!=""){
		if(usertell.length!=11){
			alert("请输入有效位数");
		}else{
			$.ajax({
				url:basebath+"/changetell.do",
				data:"usertell="+usertell+"&userid="+userid,
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
$("#changeusername").click(function(){
	var usernamemsg = $("#username2").val();
	if($.trim(usernamemsg)!=null||$.trim(usernamemsg)!=""){
		if(usernamemsg.length>5||usernamemsg.length<2){
			alert("请输入有效位数");
		}else{
			$.ajax({
				url:basebath+"/changeusername.do",
				data:"usernamemsg="+usernamemsg+"&userid="+userid,
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

/**
 * 性别选项
 */
$("#gender").click(function(){
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
		url:basebath+"/changegender.do",
		data:"usergender="+usergender+"&userid="+userid,
		dataType:"JSON",
		type:"post",
		dataType:"text",
		success:function(msg){
			//alert(msg);
			window.location.reload();
		}
	});
}







