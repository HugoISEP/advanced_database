package com.isep.testjpa.service.mapper;

import com.isep.testjpa.model.Emp;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmpMapper {
    void updatedEmp(Emp in, @MappingTarget Emp out);
}
