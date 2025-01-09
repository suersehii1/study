package com.example.myproject.portal.mapper;

import com.example.myproject.biz.com.CustomResultMap;
import com.example.myproject.portal.dto.PMenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortalMapper {
    List<PMenuDTO> getMenuTreeList();
}
