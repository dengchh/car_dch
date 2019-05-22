//点击登录，提交信息
function reg() {
	if ($('#userName').val() == "") {
		alert('用户名不能为空');
		return;
	}
	if ($('#pwd').val() == "") {
		alert('密码不能为空');
		return;
	}
	if ($('#name').val() == "") {
		alert('姓名不能为空');
		return;
	}
	if ($('#phone').val() == "") {
		alert('电话不能为空');
		return;
	}
	$.ajax({
		url : "/car-dch/user/reg.do",
		type : 'POST',
		data : {
			userName : $('#userName').val(),
			userPwd : $('#pwd').val(),
			name : $('#name').val(),
			phone : $('#phone').val()
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1000) {
				alert("注册成功");
				window.location.href = "/car-dch/login.html";
			} else if (result.code == 2000) {
				return $.growl.warning({
					title : "错误警告",
					message : "请刷新页面重新填写信息！"
				});
			}
		},
		error : function() {
			alert('网络连接出错');
		}
	});
}

// 检查用户名是否可用
function checkUserName() {
	$.ajax({
		url : "/car-dch/user/checkUser.do",
		type : 'POST',
		data : {
			userName : $('#userName').val()
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1000) {
				return $.growl.notice({
					title : "用户名提示",
					message : "用户名可用!"
				});
			} else if (result.code == 2000) {
				return $.growl.warning({
					title : "用户名提示",
					message : "用户名已被使用，请重新输入!"
				});
			}
		},
		error : function() {
		}
	});
}

// 跳转到登录页面
function log() {
	window.location.href = "/car-dch/login.html";
}