package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.FabricaDeCoduriInfo;
import com.lazaria.metacode.service.FabricaDeCoduriInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabrica-de-coduri-info")
public class PublicFabricaDeCoduriInfoController {

    private final FabricaDeCoduriInfoService fabricaDeCoduriInfoService;

    @Autowired
    public PublicFabricaDeCoduriInfoController(FabricaDeCoduriInfoService fabricaDeCoduriInfoService) {
        this.fabricaDeCoduriInfoService = fabricaDeCoduriInfoService;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> getFabricaDeCoduriInfoById(@PathVariable int id) {
        FabricaDeCoduriInfo fabricaDeCoduriInfo = fabricaDeCoduriInfoService.getFabricaDeCoduriInfoById(id);
        if (fabricaDeCoduriInfo != null) {
            return ResponseEntity.ok(fabricaDeCoduriInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
