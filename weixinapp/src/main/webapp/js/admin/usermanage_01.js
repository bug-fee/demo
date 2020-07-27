/**
 * 页面初始化默认加载7个维修人员 下拉刷新
 */
loading = false;
var page = 0;
var textvalue;// 文本框的内容
var showtype;// 控制是否进行模糊查询
$(document).ready(function() {
	showtype = 0;
	page = 0;
	commons("", 1, 0, 7);

});
// 滚动加载更多
$(document.body).infinite().on("infinite", function() {
	if (loading)
		return;
	loading = true;
	page++;
	offset = (page - 1) * 7;
	setTimeout(function() {
		if (showtype == 1) {
			commons(textvalue, 0, offset, 7);
		} else {
			commons("", 0, offset, 7);
		}
		loading = false;
	}, 1500); // 模拟延迟
});
$("#servicemanInput").click(function(e) {
	showtype = 1;
	$("#servicemancommit").show();
	$("#servicemanCancel").hide();
});
/**
 * 搜索按钮的点击事件
 */
$("#servicemancommit").click(function() {
	textvalue = $.trim($("#servicemanInput").val());
	if (textvalue != "") {
		$("#type").html("");
		commons(textvalue, 0, 0, 7);
	} else {
		alert("请输入你想搜索的维修人员!!!");
	}

});

/**
 * 通用查询用户列表展示
 * 
 * @param name
 *            用户姓名
 * @param type
 *            查询用户是否通过审核
 *            0未审核(申请中状态)，1审核通过(正式维修员)，2取消授权(不能进入维修人员中心),3更改管辖区域状态(可以进入维修人员中心)
 * @param offset
 *            分页起始
 * @param limit
 *            分页结束
 */
function commons(name, type, offset, limit) {
	$("#moreserviceman").hide();
	$.ajax({
				url : basebath + "/userList.do",
				data : {
					"name" : name,
					"type" : type,
					"offset" : offset,
					"limit" : limit
				},
				dataType : "JSON",
				type : "POST",
				success : function(data) {
					var leght = 0;
					var result = data.data;
					if (data.success == true) {
						loading = true;
						$(result)
								.each(
										function(i, e) {
											leght++;
											var content = $("<div class=\"content\"></div>");
											var user = $("<div class='weui-cells notopmar'>"
													+ "<a class='weui-cell weui-cell_access manheaderline' onclick='detailid("
													+ e.id
													+ ");'>"
													+ "<div class='weui-cell__bd'>"
													+ "<img class='Manaheader' src='"
													+ e.headimgurl
													+ "'>"
													+ "<div class='userdeta'>"
													+ "<strong class='serianutit'>"
													+ e.name
													+ "</strong>"
													+ "</div>"
													+ "</div>"
													+ "<div class='weui-cell__ft'></div>"
													+ "</a>" + "</div>");
											content.append(user);
											$("#type").append(content);
										});
						if (leght % 7 == 0) {
							$("#moreserviceman").show();
						} else {
							$("#moreserviceman").hide();
						}

					} else {
						alert(data.message);
					}

					loading = false;
				}
			});
}
/**
 * 用户信息详情
 * 
 * @param id
 *            url参数用户id
 */
function detailid(id) {
	location.href = "userdetails.html?id=" + id;
}