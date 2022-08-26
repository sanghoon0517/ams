package com.ams.student.controller.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ams.classes.model.dto.ClassDto;
import com.ams.classes.service.ClassService;
import com.ams.common.model.dto.BootstrapTableDto;
import com.ams.common.model.dto.PaginationCriteriaDto;
import com.ams.common.model.dto.ResponseDto;
import com.ams.student.model.dto.StudentDto;
import com.ams.student.service.StudentService;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentApiController {
	
	private final StudentService studentService;
	private final ClassService classService;

	
	@PostMapping("data/student/enroll")
	public ResponseEntity<?> enrollStudent(@RequestBody StudentDto vo) {
		int result = studentService.enrollStudent(vo);
		if(result == 1) {
			return new ResponseEntity<>(new ResponseDto<String>(result, "학생 등록 성공", null), HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(new ResponseDto<String>(result, "학생 등록 실패", null), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/studentList/advanced/api")
	public BootstrapTableDto<?> studentListAdvanced(
			@RequestParam(required=false) String search, 
			@RequestParam(required=false) String offset,
			@RequestParam(required=false) String limit,
			@RequestParam(required=false) String filter,
			@RequestParam(required=false) String st_rm_c_cnt
	)
	{
		int intOffset = 0;
		int intLimit = 0;
		System.out.println("[jsh] limit : "+limit);
		System.out.println("[jsh] offset : "+offset);
		System.out.println("[jsh] filter : "+filter);
		System.out.println("[jsh] filter st_rm_c_cnt : "+filter);
		System.out.println("[jsh] st_rm_c_cnt : "+st_rm_c_cnt);
		System.out.println("[jsh] 컨트롤러 호출");
		if(filter != null) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject jsonObj = (JSONObject) parser.parse(filter);
				System.out.println(jsonObj);
				System.out.println("[jsh] json Object : "+jsonObj.get("st_rm_c_cnt"));
				//여러 필터 한 번에 던지기
				Iterator<String> iter = jsonObj.keySet().iterator();
				while(iter.hasNext()) {
					String key = iter.next();
					String value = jsonObj.get(key).toString();
					System.out.println("key : "+key+", value : "+value);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(offset != null && limit != null) {
			intOffset = Integer.parseInt(offset);
			intLimit = Integer.parseInt(limit);			
		}
		PaginationCriteriaDto pageObj = new PaginationCriteriaDto(intOffset,intLimit);
		int studentTotalCnt = 0;
//		if(offset != null && limit != null) {
//			pageObj.setPage(Integer.parseInt(offset));
//			pageObj.setPerPageNum(Integer.parseInt(limit));
			pageObj.setPage(intOffset);
			pageObj.setPerPageNum(intLimit);
			if(!"".equals(search)) {
				pageObj.setSearch(search);
				studentTotalCnt = studentService.getStudentListCountParam(search);
			} else {
				studentTotalCnt = studentService.getStudentListCount();
			}
			
//		}
		
		List<StudentDto> resultList = null;
		if(offset == null && limit == null) {
			resultList = studentService.getStudentList();
		} else {
			resultList = studentService.getStudentListPaging(pageObj);
		}
		
		BootstrapTableDto<List<StudentDto>> retData = new BootstrapTableDto<List<StudentDto>>();
		retData.setTotal(studentTotalCnt);
		retData.setTotalNotFiltered(studentTotalCnt);
		retData.setRows(resultList);
		
		return retData;
	}

	@GetMapping("/studentList/{st_idx}")
	public ResponseEntity<?> getStudentDetail(@PathVariable int st_idx) {
		StudentDto studentDto = studentService.getStudentInfoByIdx(st_idx);
		List<ClassDto> classList  = classService.getAllClasses();
		List<Object> resultList = new ArrayList<>();
		resultList.add(studentDto);
		resultList.add(classList);
		return new ResponseEntity<>(new ResponseDto<Object>(1, "OK", resultList), HttpStatus.OK);
	}
	
	@PostMapping("/studentList/modify/check")
	public ResponseEntity<?> chkStudentInfoCount(@RequestBody StudentDto vo) {
		System.out.println("[jsh] 수정 체크 호출됨");
		System.out.println("[jsh] vo Data출력 : "+vo.toString());
		int isModified = studentService.chkStudentInfoCount(vo);
		if(isModified == 0) {
			return new ResponseEntity<>(new ResponseDto<Object>(1, "OK", null), HttpStatus.OK);			
		} else {
			System.out.println("[jsh] 수정된 내용이 없습니다.");
//			return new ResponseEntity<>(new ResponseDto<Object>(0, "NOT MODIFIED", null), HttpStatus.NOT_MODIFIED);
			return new ResponseEntity<>(new ResponseDto<Object>(0, "NOT MODIFIED", null), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/studentList/{st_idx}/update")
	public ResponseEntity<?> modifyStudentInfo(@PathVariable int st_idx, @RequestBody StudentDto vo) {
		int success = studentService.modifyStudentInfo(vo);
		if(success == 1) {
			return new ResponseEntity<>(new ResponseDto<Object>(1, "OK", null), HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(new ResponseDto<Object>(0, "FAIL", null), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/studentList/{st_idx}/delete")
	public ResponseEntity<?> deleteStudent(@PathVariable int st_idx) {
		int success = studentService.deleteStudent(st_idx);
		if(success == 1) {
			return new ResponseEntity<>(new ResponseDto<Object>(1, "OK", null), HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(new ResponseDto<Object>(0, "FAIL", null), HttpStatus.BAD_REQUEST);
		}
	}
	

}