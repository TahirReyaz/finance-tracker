package com.tahir.finance_manager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.stat.StatRequestDto;
import com.tahir.finance_manager.dto.stat.StatResponseDto;
import com.tahir.finance_manager.services.StatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatController {
    private final StatService statService;

    @GetMapping
    public StatResponseDto getStats (@Valid @ModelAttribute StatRequestDto statRequestDto) {
        return statService.getStats(statRequestDto);
    }
}
