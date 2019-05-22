//获得Session
$(function(){
//	var name = '<%=session.getAttribute("name")%>';
//	alert(name);
	$.ajax({
		url : "/car-dch/user/getSessionName.do",
		type : 'POST',
		data : {},
		dataType : "json",
		success : function(result){
			if (result.code == 1000){
				$("#user").html(result.data);
			} else if (result.code == 2000){
				alert('session失效请重新登录');
				window.location.href = "/car-dch/login.html";
			}
		},
		error : function(){
			alert('session失效请重新登录');
			window.location.href = "/car-dch/login.html";
		}
	})
});