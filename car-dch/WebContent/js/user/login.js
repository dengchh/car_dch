//注册跳转功能
function toReg() {
	window.location.href = "/car-dch/reg.html";
}

//登录功能
function toLogin(){
	if ($('#userName').val() == "") {
		return $.growl.warning({title: "用户名提示",message: "用户名不能为空!"});
	}
	if ($('#pwd').val() == "") {
		return $.growl.warning({title: "密码提示",message: "密码不能为空!"});
	}
	$.ajax({
		url : "/car-dch/user/login.do",
		type : 'POST',
		data : {
			userName : $('#userName').val(),
			pwd : $('#pwd').val()
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1000) {
				window.setTimeout(function() {
					window.location.href = "/car-dch/user/home.html";
				}, 1000);
			}
			if (result.code == 2000) {
				alert('账号或者密码错误');
			}
		},
		error : function() {
			alert('网络连接出错');
		}
	});
}