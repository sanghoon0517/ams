package com.ams.classes.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.classes.model.dao.ClassDao;
import com.ams.classes.model.dto.ClassDto;
import com.ams.student.model.dto.StudentDto;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

      @Autowired
      private ClassDao dao;

      /**
       * 클래스 등록
       */
      @Override
      public int insertClass(ClassDto dto) {
            int result = dao.insertClass(dto);

            return result;
      }

      /**
       * 학생들 클래스에 등록하기
       */
      @Override
      public int updateStClass(ClassDto dto) {
            int result = dao.updateStClass(dto);
            return result;
      }

      /**
       * 모든 클래스 목록 가져오기(list)
       */
      @Override
      public List<ClassDto> getAllClasses() {
            List<ClassDto> list = dao.getAllClasses();
            return list;
      }

      @Override
      public String numToDay(int c_wkd) {
            String day = "";
            switch (c_wkd) {
                  case 2:
                        day = "월";
                        break;
                  case 3:
                        day = "화";
                        break;
                  case 4:
                        day = "수";
                        break;
                  case 5:
                        day = "목";
                        break;
                  case 6:
                        day = "금";
                        break;
                  case 7:
                        day = "토";
                        break;
                  case 1:
                        day = "일";
                        break;
                  default:
                        break;
            }

            return day;
      }

      /**
       * 해당 클래스에 등록된 원생 수 조회
       */
      @Override
      public int countStClass(int c_idx) {
            int result = dao.countStClass(c_idx);
            return result;
      }

      /**
       * 클래스 등록시 조회에 가져올 학교 정보 불러오기
       */
      @Override
      public List<StudentDto> getAllSchl() {
            List<StudentDto> list = dao.getAllSchl();
            return list;
      }

      /**
       * 클래스 등록시 조회된 학생 리스트 정보 가져오기
       */
      @Override
      public List<StudentDto> getStudent(ClassDto dto) {
            List<StudentDto> list = dao.getStudent(dto);
            return list;
      }

      /**
       * 클래스 조회
       * c_idx로 조회된 클래스 정보 가져오기
       */
      @Override
      public ClassDto getClass(int c_idx) {
            ClassDto dto = dao.getClass(c_idx);
            return dto;
      }

      /**
       * 선생 idx로 해당 클래스에 등록된 학생 리스트 가져오기
       */
      @Override
      public List<StudentDto> getClassStudent(int c_idx) {
            List<StudentDto> list = dao.getClassStudent(c_idx);
            return list;
      }

      /**
       * 선생 idx로 해당 선생님 이름 가져오기
       */
      @Override
      public String getTeachername(int c_idx) {
            String t_name = dao.getTeachername(c_idx);
            return t_name;
      }

      /**
       * 한국나이로 환산 19990911 -> 나이로
       * @param st_bth 19990911 8자리 출생연도
       * @return 14 한국나이 반환
       */
      @Override
      public String getKoreanAge(String st_bth) {
            int birth = Integer.parseInt(st_bth.substring(0, 4));
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            int nowYear = Integer.parseInt(now.format(formatter));
            String result = String.valueOf(nowYear - birth + 1);
            return result;
      }

      @Override
      public String getGender(String st_gen) {
            String result = st_gen == "M" ? "(남)" : "(여)";
            return result;
      }

      /**
       * 클래스 업데이트 기능
       */
      @Override
      public int updateClass(ClassDto vo) {
            int result = dao.updateClass(vo);
            return result;
      }

      /**
       * 클래스 삭제
       */
      @Override
      public int deleteClass(int c_idx) {
            int result = dao.resetStClass(c_idx);
            result += dao.deleteClass(c_idx);
            
            return result;
      }

}
