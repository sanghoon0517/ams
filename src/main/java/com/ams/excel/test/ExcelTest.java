package com.ams.excel.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public class ExcelTest {
	public static void main(String[] args) {
		List<ExcelTestData> sample1List = Arrays.asList(
                new ExcelTestData("1반","박백아","100","90", "80", "100", LocalDateTime.of(1994, 1, 1,0,0,0), BigDecimal.valueOf(1000)),
                new ExcelTestData("1반","김마리","100","90", "80", "100", LocalDateTime.of(1995, 2, 1,0,0,0), BigDecimal.valueOf(2000000)),
                new ExcelTestData("3반","김철수","100","90", "80", "100", LocalDateTime.of(1996, 3, 1,0,0,0), BigDecimal.valueOf(300)),
                new ExcelTestData("1반","김영희","100","90", "80", "100", LocalDateTime.of(1997, 4, 1,0,0,0), null)
        );
		
		ResponseEntity<Resource> responseEntity = null;
		FileOutputStream fileOutputStream = null;
        try {
        	responseEntity = ExcelTestExport.export("test",ExcelTestData.class, sample1List);
        	fileOutputStream = new FileOutputStream("test.xlsx");
			fileOutputStream.write(((ByteArrayResource)responseEntity.getBody()).getByteArray());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
