package com.example.catalog_service.mapper;

import com.example.catalog_service.vo.ResponseCatalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatalogMapper {

    List<ResponseCatalog> getCatalogAll();
}
