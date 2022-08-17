function enroll() {
	
/*	if(c_idx.value === "") {
		alert("특정 수업을 선택해주세요.");
		return false;
	} else if(c_idx.value !== "4") {
		alert("현재 준비중인 수업입니다.");
		return false;
	}
*/	
	//상세주소 주소에 합쳐서 보내기
	if(st_adr_dtl.value != null || st_adr_dtl.value != "") {
		st_adr.value += " "+st_adr_dtl.value;
	}

	let json = formToJson("st_form");

	$.ajax({
		type: "POST",
		url:`data/student/enroll`,
		dataType: "json",
        contentType:"application/json;charset=utf-8", //마임타입 지정
		data: json 
	}).done(res=>{
		alert("등록되었습니다.");
		location.href = `/ams/studentEnroll`; //임시채널이동
		console.log(res);
	}).fail(error=>{
		alert("등록 처리 중 에러가 발생했습니다.");
		console.log("오류", "error");
	});
}

function formToJson(formId) {
	let jsonArr = $("#"+formId).serializeArray();
	let jsonObj;
	
	if(jsonArr) {
		jsonObj = new Object();
		$.each(jsonArr, (i)=>{
			if(jsonArr[i].name == "st_bth" || jsonArr[i].name == "st_en_dt" || jsonArr[i].name == "st_dis_dt" || jsonArr[i].name == "st_pn" || jsonArr[i].name == "st_prt_pn") {
				jsonObj[jsonArr[i].name] = chkNum(jsonArr[i].value);
			} else {
				jsonObj[jsonArr[i].name] = jsonArr[i].value;				
			}
		});
		console.log("[jsh] jsonObj : "+JSON.stringify(jsonObj));
	}
	return JSON.stringify(jsonObj);
}

/*
작성자 : jsh
함수명 : chkNum(value)
파라미터 : value : String값
함수내용 : value값에 숫자 이외의 값들을 전부 삭제함
*/
function chkNum(value) {
	return value.replace(/([^0-9])/g, "")
}