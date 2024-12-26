package com.example.catalog_service.controller;

import com.example.catalog_service.mapper.CatalogMapper;
import com.example.catalog_service.vo.ResponseCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/catalog-service/")
public class CatalogController {
    private Environment env;
    private CatalogMapper catalogMapper;

    @Autowired
    public CatalogController(Environment env, CatalogMapper catalogMapper) {
        this.env = env;
        this.catalogMapper = catalogMapper;
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<ResponseCatalog>> getCatalogAll() {
        List<ResponseCatalog> result = null;
        try {
            result = this.catalogMapper.getCatalogAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.trace(ex.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in Catalog Service on PORT %s", this.env.getProperty("local.server.port"));
    }
}
