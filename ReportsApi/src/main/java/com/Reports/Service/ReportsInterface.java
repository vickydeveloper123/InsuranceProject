package com.Reports.Service;

import java.io.IOException;
import java.util.List;

import com.Reports.Entity.EligibilityDetails;
import com.Reports.Request.SearchRequest;
import com.Reports.Response.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportsInterface {
	boolean saveData(EligibilityDetails details);

	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatus();

	public List<SearchResponse> search(SearchRequest request);

	public void generateExcel(HttpServletResponse response) throws IOException;

	public void geneartePdf(HttpServletResponse response) throws IOException;

}
