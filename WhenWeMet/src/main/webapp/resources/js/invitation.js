var ajaxManager = (function() {
	var get = function(obj, callback) {
		$.getJSON('/invitation/list/' + obj, callback);
	};

	var getGroup = function(obj, callback) {
		$.getJSON('/invitation/group/' + obj, callback);
	};

	var invite = function(obj, callback, error) {
		$.ajax({
			url : "/invitation/invite/" + obj.mid + "/" + obj.sender + "/"
					+ obj.receiver,
			type : "post",
			success : callback,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(obj.csrf.header, obj.csrf.token);
			},
			error : error
		});
	};

	var accept = function(obj, callback) {
		$.ajax({
			url : "/invitation/accept/" + obj.mid + "/" + obj.userId + "/"
					+ obj.sender,
			type : "post",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(obj.csrf.header, obj.csrf.token);
			},
			success : callback
		});
	};

	var deny = function(obj, callback) {
		$.ajax({
			url : "/invitation/deny/" + obj.mid + "/" + obj.userId + "/"
					+ obj.sender,
			type : "delete",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(obj.csrf.header, obj.csrf.token);
			},
			success : callback
		});
	};

	return {
		get : get,
		getGroup : getGroup,
		invite : invite,
		accept : accept,
		deny : deny
	}
})();