$(function(){
	$.ajax({
		url:basebath+"/admin_selectNews.do",
		dataType:"JSON",
		success:function(msg){
			selectNews(msg);
		}
	})
});
//查询出五条信息
function selectNews(msg){
	$(msg).each(function(i,e){
		 $li="<a class=\"weui-cell weui-cell_access\" href=\"01repair_detailes.html?id="+e.id+"\" onClick=\"updatelookupById('"+e.id+"','"+e.adminLookup+"');\">"+
				    "<div class=\"weui-cell__bd\">";
			if (e.adminLookup==0) {
				$li=$li+"<img class=\"imglef\" src=\"../../icons/redlitter_icon.png\">";
			}else{
				$li=$li+"<img class=\"imglef\" src=\"../../icons/redlitter_icon.png\" style=\"display:none;\">";
			}
				  
					$li=$li+"<div class=\"messright\">"+
					   	 	 "<strong class=\"serianutit\">编号："+e.seriano+"</strong>"+
					     	 "<p class=\"identcon\">";
			if (e.state==0) {
				if (e.user!=null) {
					
					$li=$li+"用户"+e.user.name+"发布了一条关于"+e.facilityName+"的报修申请...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}else{
					$li=$li+"用户"+e.repairName+"发布了一条关于"+e.facilityName+"的报修申请...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}
				
			}else if (e.state==1) {
				if (e.serviceman!=null) {
					
					$li=$li+"维修人员"+e.serviceman.name+"受理了一个关于"+e.facilityName+"的报修申请...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}else{
					$li=$li+"维修人员"+"受理了一个关于"+e.facilityName+"的报修申请...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}
			}else if(e.state==2){
				if(e.serviceman!=null){
					
					$li=$li+"维修人员"+e.serviceman.name+"完成了一个关于"+e.facilityName+"的报修订单...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}else{
					$li=$li+"完成了一个关于"+e.facilityName+"的报修订单...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}
			}else if(e.state==4){
				if (e.serviceman!=null) {
					
					$li=$li+"维修人员"+e.serviceman.name+"未完成了一个关于"+e.facilityName+"的报修,原因是"+e.maintenanceContent+"...</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}else{
					$li=$li+"未完成了一个关于"+e.facilityName+"的报修..."+e.maintenanceContent+"</p>"+
					"</div>"+
					"</div>"+
					"<div class=\"weui-cell__ft\">"+
					"</div>"+
					"</a>";
				}
			
			}
			$("#content").append($li);
	});
}
//当看过时,将状态改为1
function updatelookupById(id,adminLookup){
	
	if (adminLookup==1) {
	}else{
	
		$.ajax({
			
			url:basebath+"/updatelookupById.do",
			data:{
				"id":id,
				"adminLookup":1
			},
			success:function(msg){
				
			}
		});
	}
	
}