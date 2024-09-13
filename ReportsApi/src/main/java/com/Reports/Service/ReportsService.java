package com.Reports.Service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Reports.Entity.EligibilityDetails;
import com.Reports.Repo.EligibilityDetailsRepo;
import com.Reports.Request.SearchRequest;
import com.Reports.Response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsService implements ReportsInterface {
	private EligibilityDetailsRepo repo;

	public ReportsService(EligibilityDetailsRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.findPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return repo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		List<SearchResponse> reponse = new ArrayList<>();
		EligibilityDetails queryBuilder = new EligibilityDetails();
		String planName = request.getPlanName();
		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if (planStatus != null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}
		LocalDate planStartDate = request.getPlanStartDate();
		if (planStartDate != null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}
		LocalDate planEndDate = request.getPlanEndDate();
		if (planEndDate != null) {
			queryBuilder.setPlanEndDate(planEndDate);
		}
		Example<EligibilityDetails> example = Example.of(queryBuilder);
		List<EligibilityDetails> list = repo.findAll(example);
		for (EligibilityDetails entity : list) {
			SearchResponse search = new SearchResponse();
			BeanUtils.copyProperties(entity, search);
			reponse.add(search);

		}

		return reponse;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		List<EligibilityDetails> all = repo.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("MobileNo");
		headerRow.createCell(4).setCellValue("SSN");

		int i = 1;
		for (EligibilityDetails entity : all) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getEmail());
			dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
			
			
			  dataRow.createCell(3).setCellValue(entity.getMobile());
			  dataRow.createCell(4).setCellValue(entity.getSsn());
			 
			 
			
				/*
				 * dataRow.createCell(3).setCellValue(String.valueOf(entity.getMobile()));
				 * dataRow.createCell(4).setCellValue(String.valueOf(entity.getSsn()));
				 */
			i++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	@Override
	public void geneartePdf(HttpServletResponse response) throws IOException {
		List<EligibilityDetails> all = repo.findAll();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("SEARCH REPORT", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Mobile No", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		for (EligibilityDetails entity : all) {
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}
		document.add(table);
		document.close();
	}

	@Override
	public boolean saveData(EligibilityDetails details) {
		try {
			repo.save(details);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
