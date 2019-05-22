//得到车辆信息
$(function(){
	$.ajax({
		url : "/car-dch/admin/getCar.do",
		type : 'POST',
		data : {
			cID : GetQueryString("cID")
		},
		dataType : "json",
		success : function(result){
			if (result.code == 1000){
				$("#carName").attr("value",result.data.cName);
				$("#carPrice").attr("value",result.data.price);
				$("#carCompany").attr("value",result.data.company);
				$("#carType").attr("value",result.data.type);
			}
		},
		error : function(){
			alert('网络错误');
		}
	})
});

//获得get提交方法Url的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
     if(r!=null){
    	 return  decodeURIComponent(r[2]);
     } else {
    	 return null;
     }
}

//修改车辆信息
$(function() {
	$("#submit").click(function() {
		$.ajax({
			url : "/car-dch/admin/updateCar.do",
			type : 'POST',
			data : {
				cID : GetQueryString("cID"),
				carName : $('#carName').val(),
				carPrice : $('#carPrice').val(),
				carCompany : $('#carCompany').val(),
				carType : $('#carType').val(),
			},
			dataType : "json",
			success : function(result) {
				if(result.code == 1000) {
					$.growl.notice({title: "添加提示",message: "修改成功"});
					window.setTimeout(function() {
						window.close();
						self.opener.location.reload();
					}, 1000);
				}else{
				    $.growl.warning({title: "添加提示",message: "添加失败"})
				    window.close();
				}
			},
			error : function() {
				alert('网络错误');
			}
		});
	});
})
