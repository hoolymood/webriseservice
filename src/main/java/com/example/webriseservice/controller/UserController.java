package com.example.webriseservice.controller;

import com.example.webriseservice.domain.dto.request.SubscriptionRequestDto;
import com.example.webriseservice.domain.dto.request.UserRequestDto;
import com.example.webriseservice.domain.dto.request.UserRequestUpdateDto;
import com.example.webriseservice.domain.dto.response.SubscriptionResponseDto;
import com.example.webriseservice.domain.dto.response.UserCreateResponseDto;
import com.example.webriseservice.domain.dto.response.UserResponseDto;
import com.example.webriseservice.service.SubscriptionService;
import com.example.webriseservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<UserCreateResponseDto> add(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.add(userRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable("id") UUID userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable("id") UUID userId,
                                                  @RequestBody UserRequestUpdateDto userRequestUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userRequestUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<UserResponseDto> addSubscription(
            @PathVariable("id") UUID id,
            @RequestBody SubscriptionRequestDto subscriptionRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addSubscription(id, subscriptionRequestDto));
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscriptions(@PathVariable("id") UUID userId) {
        return ResponseEntity.ok(userService.getSubscriptions(userId));
    }

    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    public ResponseEntity<?> deleteSubscriptions(
            @PathVariable("id") UUID userId,
            @PathVariable("sub_id") UUID subscriptionId
    ) {
        userService.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
