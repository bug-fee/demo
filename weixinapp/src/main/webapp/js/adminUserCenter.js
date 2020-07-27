var adminid;
$(document).ready(function(){
	$.ajax({
		url:basebath+"/seladminmsg.do",
		type:"get",
		dataType:"json",
		success:function(msg){
			if(msg!=""){
				//alert(msg);
				adminid=msg[0].id;
				$("#username").text(msg[0].name);
				$("#userimg").attr("src",msg[0].adminimg);
				$("#usersex").text(msg[0].gender);
				$("#adminphone").val(msg[0].tell);
				/*
				$("#modifcom").val(msg[0].campus.name);
				
				$("#username2").val(msg[0].name);
				*/
			}
			
		}
	});
});

/**
 * 更改姓名
 */
$("#changeadminname").click(function(){
	var newadminname = $("#newadminname").val();
	if (newadminname.length > 1 && newadminname.length < 6) {
		$.ajax({
			url : basebath + "/updateadminname.do",
			data : "adminid=" + adminid + "&newadminname=" + newadminname,
			type : "post",
			dataType : "text",
			success : function(msg) {
				alert(msg);
				window.location.href="userCenter_01.html";
			}
		});
	} else {
		alert("请输入有效位数");
	}
});
/**
 * 性别选项
 */
$("#admingender").click(function(){
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

function changegender(admingender){
	$.ajax({
		url:basebath+"/changeadmingender.do",
		data:"admingender="+admingender+"&adminid="+adminid,
		dataType:"JSON",
		type:"post",
		dataType:"text",
		success:function(msg){
			//alert(msg);
			window.location.reload();
		}
	});
}

$("#changeadminphone").click(function(){
	var adminphone = $("#adminphone").val();
	if($.trim(adminphone)!=null||$.trim(adminphone)!=""){
		if(adminphone.length!=11){
			alert("请输入有效位数");
		}else{
			$.ajax({
				url:basebath+"/changeadminphone.do",
				data:"adminphone="+adminphone+"&adminid="+adminid,
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
$("#changeadminaccount").click(function(){
	var adminaccount = $("#adminaccount").val();
	var adminaccountpsd1 = $("#adminaccountpsd1").val();
	var adminaccountpsd2 = $("#adminaccountpsd2").val();
	if(adminaccount==""||adminaccountpsd1==""||adminaccountpsd2==""){
		alert("信息不能为空");
	}else{
		if(adminaccount.length>10){
			alert("账号不能过长");
		}else{
			if(adminaccountpsd1!=adminaccountpsd2){
				alert("密码输入不一致");
			}else{
				$.ajax({
					url:basebath+"/changeadminaccount.do",
					data:"adminaccount="+adminaccount+"&adminid="+adminid+"&adminaccountpsd="+adminaccountpsd1,
					type:"post",
					dataType:"text",
					success:function(msg){
						alert(msg);
						window.location.href="userCenter_01.html";
					}
				});
			}
		}
		
	}
});















