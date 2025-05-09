package com.example.webriseservice.service;

import com.example.webriseservice.domain.dto.request.SubscriptionRequestDto;
import com.example.webriseservice.domain.dto.response.SubscriptionResponseDto;
import com.example.webriseservice.domain.dto.request.UserRequestDto;
import com.example.webriseservice.domain.dto.request.UserRequestUpdateDto;
import com.example.webriseservice.domain.dto.response.UserCreateResponseDto;
import com.example.webriseservice.domain.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto get(UUID userId);
    UserResponseDto update(UUID id, UserRequestUpdateDto userRequestDto);
    UserCreateResponseDto add(UserRequestDto userRequestDto);
    void delete(UUID userId);
    void deleteSubscription (UUID userId, UUID subscriptionId);
    UserResponseDto addSubscription (UUID userId, SubscriptionRequestDto subscriptionRequestDto);
    List<SubscriptionResponseDto> getSubscriptions(UUID userId);
}
