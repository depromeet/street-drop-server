package com.depromeet.domains.village.controller;

import com.depromeet.domains.village.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/villages")
@RequiredArgsConstructor
public class VillageController {
    private final VillageService villageService;

    @GetMapping("/items/count")
    public ResponseEntity<?> getVillageItemCount() {
        return ResponseEntity.ok(villageService.getVillageItemCount());
    }

    @GetMapping("/items/count/recent")
    public ResponseEntity<?> getVillageItemCountIn3Days() {
        return ResponseEntity.ok(villageService.getVillageItemCountRecent());
    }

}