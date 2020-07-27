$(function(){
	getPrintInfo(1,10);	
})
	

function getPrintInfo(curr, pageSize) {
		$("#printContent").html("");
		var offset = (curr - 1) * pageSize;
		var state = 2;
		$.ajax({
			type : "post",
			url : myurl + "selectRepairPage.do",
			data : {
				'offset' : offset,
				'limit' : pageSize,
				'order' : 'desc',
				'state' : state,
			},
			dataType : 'json',
			success : function(res) {
				if (res.success) {
					$.each(res.data,function(index,item){
				 		var $option = "";
				 		if(item.chargeSource==null||item.signatureDepartment==null){
				 			$option = "<tr><td><a href='repair_user_detail.html?id="+item.id+"'>"+item.faultTitle+"</a></td><td align='center'>"+item.repairName+"</td><td align='center'>"+item.mobileTell+"</td><td align='center'>个人报修</td><td align='center'>"+stateRepair[item.state]+"</td><td align='center'><a href='repair_print_detail.html?id="+item.id+"'><i class='layui-icon' style='color: #009688;' title='编辑'>&#xe642;<i></a></td></tr>"
				 		}else{
				 			$option = "<tr><td><a href='repair_user_detail.html?id="+item.id+"'>"+item.faultTitle+"</a></td><td align='center'>"+item.repairName+"</td><td align='center'>"+item.mobileTell+"</td><td align='center'>个人报修</td><td align='center'>"+stateRepair[item.state]+"</td><td align='center'><a href='repair_user_detail.html?id="+item.id+"' target='_blank'><i class='layui-icon' style='color: #009688;' title='打印'>&#xe601;<i></a></td></tr>"
				 		}
				 		$("#printContent").append($option);
				 	});
				}else{
					$("#printContent").append("<tr><td colspan='6' align='center'>无数据</td></tr>");
				}
				
				layui.use([ 'laypage', 'layer' ], function() {
					var laypage = layui.laypage, layer = layui.layer;
					laypage({
						cont : 'pagePrint',
						pages : res.total,
						groups: 3,
						skip : true,
						curr:curr||1,
						jump : function(obj, first) {//触发分页后的回调
						if (!first) {//点击跳页触发函数自身，并传递当前页：obj.curr
							getPrintInfo(obj.curr,pageSize);
						 }
						}
					});
				});
			}
		})
	}

