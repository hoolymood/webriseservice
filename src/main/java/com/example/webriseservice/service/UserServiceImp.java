package com.example.webriseservice.service;

import com.example.webriseservice.domain.Subscription;
import com.example.webriseservice.domain.User;
import com.example.webriseservice.domain.dto.request.SubscriptionRequestDto;
import com.example.webriseservice.domain.dto.request.UserRequestDto;
import com.example.webriseservice.domain.dto.request.UserRequestUpdateDto;
import com.example.webriseservice.domain.dto.response.SubscriptionResponseDto;
import com.example.webriseservice.domain.dto.response.UserCreateResponseDto;
import com.example.webriseservice.domain.dto.response.UserResponseDto;
import com.example.webriseservice.handler.exception.DuplicateSubscriptionException;
import com.example.webriseservice.handler.exception.SubscriptionNotFoundException;
import com.example.webriseservice.handler.exception.UserAlreadyExistsException;
import com.example.webriseservice.handler.exception.UserNotFoundException;
import com.example.webriseservice.log.Loggable;
import com.example.webriseservice.repo.SubscriptionRepo;
import com.example.webriseservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Loggable
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final SubscriptionRepo subscriptionRepo;

    @Override
    public UserResponseDto get(UUID id) {
        User user = findById(id);
        return new UserResponseDto(user.getUsername(), user.getPhone());
    }

    @Override
    @Transactional
    public UserResponseDto update(UUID id, UserRequestUpdateDto userRequestUpdateDto) {
        User user = findById(id);
        user.setUsername(userRequestUpdateDto.username());
        user.setPhone(userRequestUpdateDto.phone());

        return new UserResponseDto(user.getUsername(), user.getPhone());
    }

    @Override
    public void delete(UUID id) {
        userRepo.deleteById(id);
    }

    @Transactional
    @Override
    public UserResponseDto addSubscription(UUID userId, SubscriptionRequestDto subscriptionRequestDto) {
        User user = findById(userId);

        boolean exists = user.getSubscriptions()
                .stream()
                .anyMatch(subscription -> subscription.getName().equals(subscriptionRequestDto.name()));

        if (exists) throw new DuplicateSubscriptionException(
                String.format("User already has this subscription %s", subscriptionRequestDto.name())
        );

        Subscription subscription = new Subscription();
        subscription.setName(subscriptionRequestDto.name());
        subscription.setStart(LocalDateTime.now());
        user.addSubscription(subscription);

        return new UserResponseDto(user.getUsername(), user.getPhone());
    }


    @Override
    @Transactional
    public void deleteSubscription(UUID userId, UUID subscriptionId) {

        User user = findById(userId);

        Subscription subscription = user.getSubscriptions()
                .stream()
                .filter(s -> s.getId().equals(subscriptionId))
                .findFirst()
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(
                                String.format("Subscription with id %s does not exist", subscriptionId)));

        user.getSubscriptions().remove(subscription);
        userRepo.save(user);

    }

    @Override
    public List<SubscriptionResponseDto> getSubscriptions(UUID userId) {
        User user = findById(userId);

        return user.getSubscriptions().stream().map(subscription ->
                new SubscriptionResponseDto(subscription.getId(), subscription.getName(), subscription.getStart())).toList();
    }


    @Override
    @Transactional
    public UserCreateResponseDto add(UserRequestDto userRequestDto) {

        if (userRepo.existsByPhone(userRequestDto.phone())) {
            throw new UserAlreadyExistsException(
                    String.format("User already exists wih number %s", userRequestDto.phone())
            );
        }
        User user = new User();
        user.setPhone(userRequestDto.phone());
        user.setUsername(userRequestDto.username());

        User userFromDb = userRepo.save(user);
        return new UserCreateResponseDto(userFromDb.getId());
    }

    private User findById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("User with id %s does not exist", id)
                        ));
    }
}
