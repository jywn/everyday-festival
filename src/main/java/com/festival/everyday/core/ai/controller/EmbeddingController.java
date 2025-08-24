package com.festival.everyday.core.ai.controller;

import com.festival.everyday.core.ai.dto.request.TestRequest;
import com.festival.everyday.core.ai.service.EmbeddingService;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.company.dto.response.RecommendCompanyResponse;
import com.festival.everyday.core.company.repository.CompanyRepository;
import com.festival.everyday.core.festival.dto.response.RecommendFestivalResponse;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmbeddingController {

    private final EmbeddingService embeddingService;
    private final FestivalRepository festivalRepository;
    private final CompanyRepository companyRepository;

    @PostMapping("/test/embed-festival")
    public void embedFestival(@RequestBody TestRequest request) {
        embeddingService.embedFestival(request.getId(), request.getText());
    }

    @PostMapping("/test/embed-company")
    public void embedCompany(@RequestBody TestRequest request) {
        embeddingService.embedCompany(request.getId(), request.getText());
    }
}
