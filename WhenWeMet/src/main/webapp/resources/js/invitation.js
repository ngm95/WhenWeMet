var ajaxManager = (function(){
	var get = function(obj, callback) {
		$.getJSON('/invitation/list/'+obj, callback);
	};
	
	var getGroup = function(obj, callback) {
		$.getJSON('/invitation/group/'+obj, callback);
	}
	
	var accept = function(obj, callback) {
		$.ajax({
			url : "/invitation/accept/"+obj.mid+"/"+obj.userId+"/"+obj.sender,
			type : "post",
			success : callback
		});
	};
	
	var deny = function(obj, callback) {
		$.ajax({
			url : "/invitation/deny/"+obj.mid+"/"+obj.userId+"/"+obj.sender,
			type : "delete",
			success : callback
		});
	};
	
	return {
		get : get,
		getGroup : getGroup,
		accept : accept,
		deny : deny
	}
})();