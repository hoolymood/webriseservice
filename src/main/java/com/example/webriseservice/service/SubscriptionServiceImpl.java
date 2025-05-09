package com.example.webriseservice.service;

import com.example.webriseservice.log.Loggable;
import com.example.webriseservice.repo.SubscriptionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Loggable
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;

    @Override
    public List<String> top() {
        return subscriptionRepo.top();
    }
}
