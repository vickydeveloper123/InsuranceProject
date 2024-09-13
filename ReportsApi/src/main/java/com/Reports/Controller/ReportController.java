package com.Reports.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Reports.Entity.EligibilityDetails;
import com.Reports.Request.SearchRequest;
import com.Reports.Response.SearchResponse;
import com.Reports.Service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportController 
{
	private ReportsService service;

	public ReportController(ReportsService service) {
		super();
		this.service = service;
	}
	@GetMapping("/getplan")
	public ResponseEntity<List<String>> getUniquePlans()
	{
		List<String> uniquePlanNames = service.getUniquePlanNames();
		return new ResponseEntity<List<String>>(uniquePlanNames,HttpStatus.OK);
	}
	@GetMapping("/getStatus")
	public ResponseEntity<List<String>> getUniqueStatus()
	{
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(uniquePlanStatus,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> searchRequest(@RequestBody SearchRequest request)
	{
		System.out.println(request);
		List<SearchResponse> search = service.search(request);
		return new ResponseEntity<List<SearchResponse>>(search,HttpStatus.OK);
	}
//	@PostMapping("/save")
//	public ResponseEntity<String> send(@RequestBody EligibilityDetails request)
//	{
//		
//		boolean saveData = service.saveData(request);
//		if(saveData)
//		{
//			return new ResponseEntity<String>("Saved",HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<String>("Not saved",HttpStatus.OK);
//		}
//		
//		
//	}
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody EligibilityDetails details){
		boolean saveData = service.saveData(details);
		if(saveData) {
			return new ResponseEntity<String>("saved",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("notSaved",HttpStatus.OK);
		}
	}
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = data.xls";
		response.setHeader(headerKey, headerValue);
		
		service.generateExcel(response);
	}
	@GetMapping("/pdff")
	public void generatePdf(HttpServletResponse response) throws IOException {
		response.setContentType("application123/dora");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = data.pdf";
		response.setHeader(headerKey, headerValue);
		
		service.geneartePdf(response);
	}
	
	
	

}
