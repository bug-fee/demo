$(function(){
	$.ajax({
		url:basebath+"/selectNews.do",
		dataType:"JSON",
		success:function(msg){
			
			selectNews(msg);
			
		}
	})
});
//查询出五条信息
function selectNews(msg){
	$(msg).each(function(i,e){
		
		 $li="<a class=\"weui-cell weui-cell_access\" href=\"01repair_detailes.html?id="+e.id+"\" onClick=\"updateRepair('"+e.id+"','"+e.lookup+"')\">"+
				    "<div class=\"weui-cell__bd\">";
			if (e.lookup==0) {
				$li=$li+"<img class=\"imglef\" src=\"../icons/redlitter_icon.png\">";
			}else{
				$li=$li+"<img class=\"imglef\" src=\"../icons/redlitter_icon.png\" style=\"display:none;\">";
			}
				  
					$li=$li+"<div class=\"messright\">"+
					   	 	 "<strong class=\"serianutit\">编号："+e.seriano+"</strong>"+
					     	 "<p class=\"identcon\">";
			if (e.state==0) {
				$li=$li+"您的申请已发出请耐心等待...</p>"+
				"</div>"+
				"</div>"+
				"<div class=\"weui-cell__ft\">"+
				"</div>"+
				"</a>"
				
			}else if (e.state==1) {
				$li=$li+"维修人员已接收您的维修请求,请耐心等待...</p>"+
				"</div>"+
				"</div>"+
				"<div class=\"weui-cell__ft\">"+
				"</div>"+
				"</a>";
			}else {
				$li=$li+"已维修,请爱护学校物品...</p>"+
				"</div>"+
				"</div>"+
				"<div class=\"weui-cell__ft\">"+
				"</div>"+
				"</a>";
			}
			$("#content").append($li);
	});
}
//当看过时,将状态改为1
function updateRepair(id,lookup){
	
	if (lookup==1) {
	}else{
	
		$.ajax({
			
			url:basebath+"/updatelookupById.do",
			data:{
				"id":id,
				"lookup":1
			},
			success:function(msg){
				
			}
		});
	}
	
}