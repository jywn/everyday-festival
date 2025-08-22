package com.festival.everyday.core.ai.controller;

import com.festival.everyday.core.ai.dto.request.TestRequest;
import com.festival.everyday.core.ai.service.RecommendService;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmbeddingController {

    private final RecommendService recommendService;

    @PostMapping("/test/embed-festival")
    public void embedFestival(@RequestBody TestRequest request) throws SQLException {
        recommendService.embedFestival(request.getId(), request.getText());
    }

    @PostMapping("/test/embed-company")
    public void embedCompany(@RequestBody TestRequest request) throws SQLException {
        recommendService.embedCompany(request.getId(), request.getText());
    }

    @GetMapping("/test/{companyId}/recommend-festival")
    public List<Long> recommendFestivals(@PathVariable Long companyId) throws SQLException {
        return recommendService.recommendFestivals(1L, companyId);
    }

    @GetMapping("/test/{festivalId}/recommend-company")
    public List<Long> recommendCompanies(@PathVariable Long festivalId) throws SQLException {
        return recommendService.recommendCompanies(1L, festivalId);
    }
}
