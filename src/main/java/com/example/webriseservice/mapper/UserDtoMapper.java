package com.example.webriseservice.mapper;


import com.example.webriseservice.domain.User;
import com.example.webriseservice.domain.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserResponseDto toDto (User user);

}
