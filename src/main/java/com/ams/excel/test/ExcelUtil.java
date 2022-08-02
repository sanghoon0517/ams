package com.ams.excel.test;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ams.excel.annotation.ExcelBody;
import com.ams.excel.annotation.ExcelHeader;

public class ExcelUtil {
	
	/*
	 * 구조
	 * XSSFWorkbook(.xlsx확장자를 가지는 엑셀통합문서) - XSSFSheet(엑셀파일에 생성된 페이지 = Sheet) - XSSFRow(Sheet에 생성된 행) - XSSFCell(행에 포함되어있는 하나의 블럭)
	 * 
	 * */
	public static <T> ResponseEntity<Resource> export(String fileName, Class<T> excelClass, List<T> data) throws IllegalAccessException, IOException {

		//엑셀통합문서인 workbook 생성
        XSSFWorkbook workbook = new XSSFWorkbook(); //XSSF는 .xlsx를 의미함. 즉 .xlsx 확장자를 가지는 엑셀통합문서 깡통 생성
        XSSFSheet sheet = workbook.createSheet(fileName); //.xlsx확장자를 가지는 엑셀 통합문서에 sheet를 생성함. sheet는 페이지를 의미
        
        
        //ExcelHeader 영역 데이터 셋팅
        //ExcelHeader 어노테이션이 붙은 필드값들만 필터링하여 엑셀 시트에 행을 추가하여 그림을 그릴 수 있게 데이터들을 셋팅한 값을 Map형태로 반환.
        Map<Integer, List<ExcelHeader>> headerMap = Arrays.stream(excelClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelHeader.class)) //받아온 ExcelTestData클래스-(dto)의 field들 중에 ExcelHeader 어노테이션이 붙은 필드들만 필터링함
                .map(field -> field.getDeclaredAnnotation(ExcelHeader.class))  //필터에서 걸러진 ExcelHeader 어노테이션 붙은 field값들 map형태로 다 가져옴
                .sorted(Comparator.comparing(ExcelHeader::colIndex))		   //colIndex를 기준으로 Sorting함
                .collect(Collectors.groupingBy(ExcelHeader::rowIndex)); 	   //행별로 묶음 -> rowIndex를 key값으로 처리

        //Header를 생성 이후, Body도 생성을 해야하기 때문에 Header-Body순으로 이어질 index를 가지고 있어야 한다.
        int index = 0;
        for (Integer key : headerMap.keySet()) { //ExcelHeader값들을 순차적으로 받아옴
            XSSFRow row = sheet.createRow(index++); //.xlsx확장자를 가지는 엑셀 통합문서에 생성된 Sheet에 Header를 그리는 행을 생성함
            for (ExcelHeader excelHeader : headerMap.get(key)) { //Map형태에서 값을 하나씩 빼오면 List<ExcelHeader>가 나온다. 이를 다시 for문을 돌려서 각 행의 블록에 값을 셋팅한다.
                XSSFCell cell = row.createCell(excelHeader.colIndex()); //각 행의 블록에 해당되는 ExcelHeader값들을 불러와서 생성한다.
                XSSFCellStyle cellStyle = workbook.createCellStyle();   //각 블록의 스타일을 셋팅한다.
                
                //cell Style 설정 시작
                cell.setCellValue(excelHeader.headerName());
                if (excelHeader.headerName().contains("\n")) {
                    cellStyle.setWrapText(true);
                }
                cellStyle.setAlignment(excelHeader.headerStyle().horizontalAlignment());
                cellStyle.setVerticalAlignment(excelHeader.headerStyle().verticalAlignment());
                if (isHex(excelHeader.headerStyle().background().value())) {
                    cellStyle.setFillForegroundColor(new XSSFColor(Color.decode(excelHeader.headerStyle().background().value()), new DefaultIndexedColorMap()));
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                }
                XSSFFont font = workbook.createFont();
                font.setFontHeightInPoints((short) excelHeader.headerStyle().fontSize());
                cellStyle.setFont(font);
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cell.setCellStyle(cellStyle); //헤더블록의 스타일 적용
                //cell Style 설정 완료
                
                if (excelHeader.colSpan() > 0 || excelHeader.rowSpan() > 0) {
                    CellRangeAddress cellAddresses = new CellRangeAddress(cell.getAddress().getRow(), cell.getAddress().getRow() + excelHeader.rowSpan(), cell.getAddress().getColumn(), cell.getAddress().getColumn() + excelHeader.colSpan());
                    sheet.addMergedRegion(cellAddresses);
                }
            }
        }

        //ExcelBody영역 데이터 셋팅
        Map<Integer, List<Field>> fieldMap = Arrays.stream(excelClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelBody.class)) //ExcelBody 어노테이션이 붙어있는 필드들을 필터링함 
                .map(field -> {
                    field.setAccessible(true); //Body의 값들을 접근해야하는데 private으로 막혀있어서 해당 필드의 접근을 허용하겠다는 의미
                    return field; //ExcelBody 붙어있는 필터링된 필드값을 private을 해제하고 그 값을 리턴함
                })
                .sorted(Comparator.comparing(field -> field.getDeclaredAnnotation(ExcelBody.class).colIndex())) //colIndex를 기준으로 Sorting
                .collect(Collectors.groupingBy(field -> field.getDeclaredAnnotation(ExcelBody.class).rowIndex())); //rowIndex를 

        //data : List 데이터이기 때문에 선언된 객체를 하나씩 꺼내서 body에 적용시킴
        for (T t : data) {
            for (Integer key : fieldMap.keySet()) {
                XSSFRow row = sheet.createRow(index++); //Body의 행을 가져옴(Header를 그린 이후의 행을 가져와하므로 index값을 이용
                for (Field field : fieldMap.get(key)) { //Map형태에서 하나씩 뽑아오면 List<Field>가 나온다. 이 List에서 Field값을 하나씩 뽑아서 Body값에 데이터를 셋팅해준다.
                    ExcelBody excelBody = field.getDeclaredAnnotation(ExcelBody.class); //ExcelBody 어노테이션이 붙은 필드를 하나씩 가져온다. 
                    Object o = field.get(t); //필드의 타입을 알기 위해서 Object로 받는다. -> Number, String, Boolean, Date, null, 등등의 타입으로 파악된다.
                    XSSFCell cell = row.createCell(excelBody.colIndex()); //가장 작은 단위의 블록
                    XSSFCellStyle cellStyle = workbook.createCellStyle(); //cell Style을 생성해서 적용
                    XSSFDataFormat dataFormat = workbook.createDataFormat(); //필드의 데이터타입에 따라서 블록에 적용될 Data타입이 다르다.

                    //cell Style 적용 시작
                    cellStyle.setAlignment(excelBody.bodyStyle().horizontalAlignment());
                    cellStyle.setVerticalAlignment(excelBody.bodyStyle().verticalAlignment());
                    if (isHex(excelBody.bodyStyle().background().value())) {
                        cellStyle.setFillForegroundColor(new XSSFColor(Color.decode(excelBody.bodyStyle().background().value()), new DefaultIndexedColorMap()));
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    }
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderTop(BorderStyle.THIN);

                    if (o instanceof Number) {
                        if (StringUtils.isNoneBlank(excelBody.bodyStyle().numberFormat())) {
                            cellStyle.setDataFormat(dataFormat.getFormat(excelBody.bodyStyle().numberFormat()));
                        }
                        cell.setCellValue(((Number) o).doubleValue());
                    } else if (o instanceof String) {
                        cell.setCellValue((String) o);
                    } else if (o instanceof Date) {
                        cellStyle.setDataFormat(dataFormat.getFormat(excelBody.bodyStyle().dateFormat()));
                        cell.setCellValue((Date) o);
                    } else if (o instanceof LocalDateTime) {
                        cellStyle.setDataFormat(dataFormat.getFormat(excelBody.bodyStyle().dateFormat()));
                        cell.setCellValue((LocalDateTime) o);
                    } else if (o instanceof LocalDate) {
                        cellStyle.setDataFormat(dataFormat.getFormat(excelBody.bodyStyle().dateFormat()));
                        cell.setCellValue((LocalDate) o);
                    }
                    cell.setCellStyle(cellStyle);
                    if (excelBody.colSpan() > 0 || excelBody.rowSpan() > 0) {
                        CellRangeAddress cellAddresses = new CellRangeAddress(cell.getAddress().getRow(), cell.getAddress().getRow() + excelBody.rowSpan(), cell.getAddress().getColumn(), cell.getAddress().getColumn() + excelBody.colSpan());
                        sheet.addMergedRegion(cellAddresses);
                    }
                    //cell Style 적용 끝
                    
                    if ((excelBody.width() > 0 && excelBody.width() != 8) && sheet.getColumnWidth(excelBody.colIndex()) == 2048) {
                        sheet.setColumnWidth(excelBody.colIndex(), excelBody.width() * 256);
                    }
                }
            }
        }
        
        //그룹필드값을 받아서 하나씩 처리하겠음
        List<Field> groupField = Arrays.stream(excelClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelBody.class) && field.getDeclaredAnnotation(ExcelBody.class).rowGroup())
                .map(field -> {
                    field.setAccessible(true);
                    return field;
                })
                .sorted(Comparator.comparing(field -> field.getDeclaredAnnotation(ExcelBody.class).colIndex()))
                .collect(Collectors.toList());

        Map<Field, List<Integer>> groupMap = new HashMap<>();
        for (Field field : groupField){
            groupMap.put(field, new ArrayList<>());
            for(int i=0; i< data.size(); i++){
                Object o1 = field.get(data.get(i));

                for(int j = i+1;j < data.size(); j++){
                    Object o2 = field.get(data.get(j));
                    if(!o1.equals(o2)){
                        groupMap.get(field).add((j)* headerMap.size()+headerMap.keySet().size()-1);
                        i = j-1;
                        break;
                    }
                }
            }
            groupMap.get(field).add(sheet.getLastRowNum());
        }

        for(Field field: groupMap.keySet()){
            int dataRowIndex = headerMap.keySet().size();
            for(int i=0; i<groupMap.get(field).size(); i++){
                XSSFRow row = sheet.getRow(dataRowIndex);
                XSSFCell cell = row.getCell(field.getDeclaredAnnotation(ExcelBody.class).colIndex());
                if(!(dataRowIndex == groupMap.get(field).get(i))){
                    CellRangeAddress cellAddresses = new CellRangeAddress(dataRowIndex, groupMap.get(field).get(i), cell.getColumnIndex(), cell.getColumnIndex());
                    sheet.addMergedRegion(cellAddresses);
                }
                dataRowIndex = groupMap.get(field).get(i)+1;

            }
        }
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for(CellRangeAddress rangeAddress : mergedRegions) {
            RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        }
        

        //작업이 완료된 엑셀통합문서 객체 workbook을 ByteArray 형태로 말아서 OutputStream에 담는다.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.out.println("[jsh] byteArray 넣기 전 사이즈 : "+byteArrayOutputStream.size());
        //workbook.write(OutputStream oos)의 의미
        //헷갈리게 사용이 되어 설명첨부
        //workbook.write() 메서드는 워크북의 내용을 인자를 받는 OutputStream에 써주는 작업을 한다.
        //그래서 write()라고 써있지만 정확하게는 "~로 쓰다"의 의미인 writeTo()라고 보는게 좀 더 명확하다.
        //https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Workbook.html 페이지에서 write()메서드 설명 참조
        workbook.write(byteArrayOutputStream);
        System.out.println("[jsh] byteArray 넣은 후 사이즈 : "+byteArrayOutputStream.size());
        return ResponseEntity
                .ok()
                .header("Content-Transfer-Encoding", "binary")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8")+".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(byteArrayOutputStream.size())
                .body(new ByteArrayResource(byteArrayOutputStream.toByteArray()));
    }


    private static boolean isHex(String hexCode) {
        if (StringUtils.startsWith(hexCode, "#")) {
            for (Character c : hexCode.substring(1).toCharArray()) {
                switch (c) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                        break;
                    default:
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
