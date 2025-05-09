package com.example.webriseservice.domain.dto.response;

import com.example.webriseservice.domain.SubscriptionName;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionResponseDto(
        UUID id,
        SubscriptionName name,
        LocalDateTime start
) {
}
