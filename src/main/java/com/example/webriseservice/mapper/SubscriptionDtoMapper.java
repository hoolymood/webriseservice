package com.example.webriseservice.mapper;

import com.example.webriseservice.domain.Subscription;
import com.example.webriseservice.domain.dto.response.SubscriptionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionDtoMapper {
    SubscriptionResponseDto toDto(Subscription subscription);
}
