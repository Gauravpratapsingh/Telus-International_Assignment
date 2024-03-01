package com.rewardProgram.RewardProgram.service;

import com.rewardProgram.RewardProgram.model.Transaction;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardControllerService {

    public Map<Integer, Map<String, Integer>> calculateRewards(List<Transaction> transactions) {
        Map<Integer, Map<String, Integer>> rewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            int customerId = transaction.getCustomerId();
            String month = transaction.getMonth();
            double amount = transaction.getAmount();

            int points = calculateRewardPoints(amount);

            rewards.putIfAbsent(customerId, new HashMap<>());
            rewards.get(customerId).put(month, rewards.get(customerId).getOrDefault(month, 0) + points);
        }

        return rewards;
    }

    private int calculateRewardPoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            points += 50; // Additional 50 points for reaching $100
        } else if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}

