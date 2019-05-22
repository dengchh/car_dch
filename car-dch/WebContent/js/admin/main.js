//获得Session
$(function(){
//	var name = '<%=session.getAttribute("name")%>';
//	alert(name);
	$.ajax({
		url : "/car-dch/admin/getSessionName.do",
		type : 'POST',
		data : {},
		dataType : "json",
		success : function(result){
			if (result.code == 1000){
				$("#user").html(result.data);
			} else if (result.code == 2000){
				alert('session失效请重新登录');
				window.location.href = "/car-dch/adminLogin.html";
			}
		},
		error : function(){
			alert('session失效请重新登录');
			window.location.href = "/car-dch/adminLogin.html";
		}
	})
});