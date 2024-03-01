package com.rewardProgram.RewardProgram.controller;


import com.rewardProgram.RewardProgram.model.Transaction;
import com.rewardProgram.RewardProgram.service.RewardControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/reward")
public class RewardProgramController {

    @Autowired
    private RewardControllerService rewardControllerService;
    @PostMapping("/calculate-rewards")
    public Map<Integer, Map<String, Integer>> calculateRewards(@RequestBody List<Transaction> transactions) {
    return rewardControllerService.calculateRewards(transactions);
    }
}

