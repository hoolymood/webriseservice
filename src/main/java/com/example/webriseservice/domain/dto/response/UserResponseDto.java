package com.example.webriseservice.domain.dto.response;

import java.util.List;

public record UserResponseDto(
        String username,
        String phone,
        List<SubscriptionResponseDto> subscriptions
) {
}
