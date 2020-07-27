//初始化管理员认证页面
$(document).ready(function(){
	$.ajax({
		url:basebath+"/initAdminApprove.do",
		dataType:"JSON",
		success:function(msg){
			$(msg).each(function(i,e){
				var $li="<a class=\"weui-cell weui-cell_access manheaderline li\" onClick=\"liclick("+e.id+","+e.state+")\">"+
						
			    "<div class=\"weui-cell__bd\">"+
		      	"<img class=\"Manaheader\" src=\""+e.headimgurl+"\">"+
		      	"<div class=\"userdeta\">"+
			   	 	 "<strong class=\"serianutit\">"+e.name+"</strong>"+
			   	"</div>"+
		    "</div>"+
		    "<div class=\"weui-cell__ft\">";
				if (e.state==0) {
					
					$li=$li+"<p>维修权限</p>"+
					"</div>"+
					
					"</a>"+
					"</div>";
				}else if (e.state==3) {
					$li=$li+"<p>更改辖区</p>"+
					"</div>"+
					"</a>"+
					"</div>";
				}
				$("#list").append($li);
			});
		}
	});
});

function liclick(id,state){
	if (state==0) {
		
		$.actions({
			title:"是否同意授权",
			actions: [{
				text: "是",
				onClick: function() {
					var authorizedTime=new Date().toLocaleDateString();
					$.ajax({
						url:basebath+"/service/changesermanstate.do",
						type:"POST",
						data:{"servicemanid":id,"state":1,"authorizedTime":authorizedTime},
						success:function(msg){
							if (msg="修改成功") {
								alert(msg);
								window.location.reload();
							}else{
								alert(msg);
							}
						}
					})
				}
			},{
				text: "否",
				onClick: function() {
					$.ajax({
						url:basebath+"/service/changesermanstate.do",
						type:"POST",
						data:"servicemanid="+id+"&&state="+2,
						success:function(msg){
							if (msg="修改成功") {
								alert(msg);
								window.location.reload();
							}else{
								alert(msg);
							}
						}
					})
				}
			}]
		});
	}else if (state==3) {
		$.actions({
			title:"是否同意更改",
			actions: [{
				text: "是",
				onClick: function() {
					$.ajax({
						url:basebath+"/service/changesermancampus.do",
						type:"POST",
						data:"id="+id+"&&state="+1,
						success:function(msg){
							if (msg="修改成功") {
								alert(msg);
								window.location.reload();
							}else{
								alert(msg);
							}
							
						}
					})
				}
			},{
				text: "否",
				onClick: function() {
					$.ajax({
						url:basebath+"/service/changesermancampus.do",
						type:"POST",
						data:"id="+id+"&&state="+2,
						success:function(msg){
							if (msg="修改成功") {
								alert(msg);
								window.location.reload();
							}else{
								alert(msg);
							}
							
						}
					})
				}
			}]
		});
	}
};