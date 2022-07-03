function getAllUser() {
	$.ajax({
		url:`/api/user/all`,
		dataType: "json"
	}).done(res=>{
		console.log(res);
	}).fail(error=>{
		console.log("오류", "error");
	});

}