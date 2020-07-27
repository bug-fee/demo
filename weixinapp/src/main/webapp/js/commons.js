/**
 * 标签页切换
 */
$(".centire strong").click(function(){
	$(".bluer").removeClass("bluer").addClass("linear");
	$(this).next().removeClass("linear").addClass("bluer");
});



/**
 * 绑定页面选择校区
 */
$("#selectCam").click(function(){
	$.actions({
		  actions: [{
		    text: "北林校区",
		    onClick: function() {
		      //do something
		    }
		  },{
		    text: "龙子湖校区",
		    onClick: function() {
		      //do something
		    }
		  },{
			  text: "英才校区",
		      onClick: function() {
		      //do something
		     }
			}]
		});
});

/**
 * 校区选项
 */
$("#modifcom").click(function(){
	var campus="[";
	$.ajax({
		url:basebath+"/selectAllCampus.do",
		dataType:"JSON",
		success:function(msg){
			$(msg).each(function(i,e){
				campus=campus+"{text:\""+e.name+"\","+
				"onClick:function(){" +
				"var val=\""+e.name+"\";" +
				"console.log(\""+e.name+"\");" +
				"$(\"#modifcom\").val(\""+e.name+"\");" +
				"$(\"#modifcom\").attr(\"cid\",\""+e.id+"\");"+
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

/**
 * 搜索类别
 */
$("#searchInput").click(function(e){
	$.actions({
		  title:"请选择搜索类别",
		  actions: [{
			    text: "设备名称",
			    onClick: function() {
			      //do something
			    }
			  },
			  {
			    text: "编号",
			    onClick: function() {
			      //do something
			    }
				},
				{
				    text: "故障标题",
				    onClick: function() {
				      //do something
				    }
			}
		  ]
		});
});

/**
 * 初始化正在加载中
 */
$(document.body).infinite();


/**
 * 获得地址栏参数
 * @param name 指定参数名
 * @returns
 */
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}