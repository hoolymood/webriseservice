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
import com.example.webriseservice.mapper.SubscriptionDtoMapper;
import com.example.webriseservice.mapper.UserDtoMapper;
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
    private final UserDtoMapper userDtoMapper;
    private final SubscriptionDtoMapper subscriptionDtoMapper;

    @Override
    public UserResponseDto get(UUID id) {
        User user = findById(id);
        return userDtoMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto update(UUID id, UserRequestUpdateDto userRequestUpdateDto) {
        User user = findById(id);
        user.setUsername(userRequestUpdateDto.username());
        user.setPhone(userRequestUpdateDto.phone());

        return userDtoMapper.toDto(user);
    }

    @Override
    public void delete(UUID id) {
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponseDto addSubscription(UUID userId, SubscriptionRequestDto subscriptionRequestDto) {
        User user = findById(userId);

        boolean exists = user.getSubscriptions()
                .stream()
                .anyMatch(subscription -> subscription.getSubscriptionName().equals(subscriptionRequestDto.subscriptionName()));

        if (exists) throw new DuplicateSubscriptionException(
                String.format("User already has this subscription %s", subscriptionRequestDto.subscriptionName())
        );

        Subscription subscription = new Subscription();
        subscription.setSubscriptionName(subscriptionRequestDto.subscriptionName());
        subscription.setStart(LocalDateTime.now());
        user.addSubscription(subscription);

        User saved = userRepo.save(user);
        return userDtoMapper.toDto(saved);
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

        return user.getSubscriptions().stream().map(subscriptionDtoMapper::toDto).toList();
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
