package com.interview.parshvaproject.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.interview.parshvaproject.dao.ParshvaRepo;
import com.interview.parshvaproject.entity.ParshvaProjectEntity;

@Service

public class ParshvaService {

	@Autowired
	private ParshvaRepo parshvaRepo;
	@Value("${excel.file.path}")
	private String excelFilePath;

	public List<Map<String, ?>> getDetails() {

		try {
			List<Map<String, ?>> dataReturn = new ArrayList<>();
			FileInputStream file = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			Row headerRow = sheet.getRow(0);
			// Extract data from the input file
			int poNo = 0;
			int supplyIndex = 0;
			int descriptionIndex = 0;
			int poNumber = 0;
			Map<String, String> newData = new HashMap<>();
			Map<String, List<String>> poDescription = new HashMap<>();
			if (headerRow != null) {
				for (int columnIndex = 0; columnIndex < headerRow.getLastCellNum(); columnIndex++) {
					Cell cell = headerRow.getCell(columnIndex);
					if (cell != null && !cell.toString().equals(" ") && cell.getStringCellValue().equals("PO Number")) {
						System.out.println("poda panni");
						System.out.println(columnIndex);
						poNo = columnIndex;
					}
					if (cell != null && !cell.toString().equals(" ") && cell.getStringCellValue().equals("Supplier")) {

						System.out.println("vada panni");
						System.out.println(columnIndex);
						supplyIndex = columnIndex;
					}

					if (cell != null && !cell.toString().equals(" ")
							&& cell.getStringCellValue().equals("Description")) {

						descriptionIndex = columnIndex;
					}
					if (cell != null && !cell.toString().equals(" ") && cell.getStringCellValue().equals("PO Number")) {

						if (poNumber == 0) {
							poNumber = columnIndex;
						}
					}
				}
			}
//			System.out.println();
//			System.out.println(poNo);
//			System.out.println(supplyIndex);
//			System.out.println();
//		        Map<String,String> newData = new HashMap<>();

			for (Row row : sheet) {
				Cell cell1 = row.getCell(poNo); // Adjust the column index
				Cell cell2 = row.getCell(supplyIndex); // Adjust the column index
				if (cell1 != null && !cell1.toString().equals(" ") && cell2 != null && !cell2.toString().equals(" ")) {

					newData.put(cell1.toString(), cell2.toString());
				}
			}
			for (Row row : sheet) {
				Cell cell1 = row.getCell(poNumber); // Adjust the column index
				Cell cell2 = row.getCell(descriptionIndex); // Adjust the column index
				if (cell1 != null && !cell1.toString().equals(" ") && cell2 != null && !cell2.toString().equals(" ")) {

					if (poDescription.containsKey(cell1.toString())) {
						// Key already exists, add the value to the existing list
						poDescription.get(cell1.toString()).add(cell2.toString());
					} else {
						// Key doesn't exist, create a new key-value pair with a list
						List<String> supplyIndexList = new ArrayList<>();
						supplyIndexList.add(cell2.toString());
						poDescription.put(cell1.toString(), supplyIndexList);
					}
				}
			}

			for (Map.Entry<String, String> entry : newData.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println("Key: " + key + ", Value: " + value);
			}
			for (Map.Entry<String, List<String>> entry : poDescription.entrySet()) {
				String key = entry.getKey();
				List<String> value = entry.getValue();
				System.out.println("Key: " + key + ", Value: " + value);
			}
			dataReturn.add(poDescription);
			dataReturn.add(newData);
			return dataReturn;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addDetailsService(ParshvaProjectEntity parshvaProjectEntity) {
		parshvaRepo.save(parshvaProjectEntity);
		return "saved";
	}

	public List<ParshvaProjectEntity> getAllDetail() {
		List<ParshvaProjectEntity> parshvaProjectEntity = parshvaRepo.findAll();
		return parshvaProjectEntity;
	}
}
