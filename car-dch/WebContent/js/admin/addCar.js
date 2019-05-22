//添加车辆信息
function toAdd() {
	if ($('#carName').val() == "") {
		alert('车辆名称不能为空');
		return;
	}
	if ($('#carPrice').val() == "") {
		alert('车辆价格不能为空');
		return;
	}
	if ($('#carCompany').val() == "") {
		alert('产出公司不能为空');
		return;
	}
	if ($('#carType').val() == "") {
		alert('车辆类型不能为空');
		return;
	}
	$.ajax({
		url : "/car-dch/admin/addCar.do",
		type : 'POST',
		data : {
			carName : $('#carName').val(),
			carPrice : $('#carPrice').val(),
			carCompany : $('#carCompany').val(),
			carType : $('#carType').val()
		},
		dataType : "json",
		success : function(result) {
			if (result.code == 1000) {
				$.growl.notice({
					title : "添加提示",
					message : "添加成功"
				});
				window.setTimeout(function() {
					window.location.href = "add_car.html";
				}, 1000);
			} else {
				return $.growl.warning({
					title : "添加提示",
					message : "添加失败"
				});
			}
		},
		error : function() {
			alert('网络连接出错');
		}
	});
}