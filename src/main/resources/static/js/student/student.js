function enroll() {
	
	if(c_idx.value === "") {
		alert("특정 수업을 선택해주세요.");
		return false;
	} else if(c_idx.value !== "1") {
		alert("현재 준비중인 수업입니다.");
		return false;
	}
	
	//상세주소 주소에 합쳐서 보내기
	if(st_adr_dtl.value != null || st_adr_dtl.value != "") {
		st_adr.value += " "+st_adr_dtl.value;
	}
	let jsonArr = $("#st_form").serializeArray();
	
	//console.log(jsonArr);
	if(jsonArr) {
		let jsonObj = new Object();
		$.each(jsonArr, (i)=>{
			jsonObj[jsonArr[i].name] = jsonArr[i].value;
		});
		console.log("[jsh] jsonObj : "+JSON.stringify(jsonObj));
		$.ajax({
			type: "POST",
			url:`data/student/enroll`,
			dataType: "json",
            contentType:"application/json;charset=utf-8", //마임타입 지정
			data: JSON.stringify(jsonObj) 
		}).done(res=>{
			alert("등록되었습니다.");
			console.log(res);
		}).fail(error=>{
			alert("등록 처리 중 에러가 발생했습니다.");
			console.log("오류", "error");
		});

	}
}

/*
작성자 : jsh
함수명 : comChkNum(value)
파라미터 : value : String값
함수내용 : value값에 숫자 이외의 값들을 전부 삭제함
*/
function comChkNum(value) {
	return value.replace(/([^0-9])/g, "")
}