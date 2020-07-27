
var myurl="http://1w74497d58.imwork.net/repairs/";
/*var myurl="http://127.0.0.1:8080/repairs/";*/
var stateRepair={
	"0":"未受理",
	"1":"已受理",	
	"2":"已解决",
	"4":"未解决",		
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
