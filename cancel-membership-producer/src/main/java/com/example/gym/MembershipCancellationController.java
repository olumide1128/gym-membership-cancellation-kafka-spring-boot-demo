package com.example.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")

public class MembershipCancellationController {

    @Autowired
    MembershipService membershipService;

    @PostMapping("/cancel/{memberId}")
    public String cancelMembership(@PathVariable String memberId) {
        return membershipService.cancelMembership(memberId);
    }

}
