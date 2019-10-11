var ajaxManager = (function() {
	var addSchedule = function(obj, callback) {
		var data = JSON.stringify(obj);
		$.ajax({
			url : "/schedule/add",
			type : "post",
			data : data,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(obj.csrf.header, obj.csrf.token);
			},
			contentType : "application/json; charset=utf-8",
			success : callback
		});
	};

	var getSchedule = function(obj, callback) {
		$.getJSON('/schedule/list/' + obj.userId + '/' + obj.mid, callback);
	};

	var deleteSchedule = function(obj, callback) {
		$.ajax({
			url : "/schedule/delete/" + obj.sid,
			type : "delete",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(obj.csrf.header, obj.csrf.token);
			},
			success : callback,
			async : false
		});
	};

	return {
		addSchedule : addSchedule,
		getSchedule : getSchedule,
		deleteSchedule : deleteSchedule
	}
})();