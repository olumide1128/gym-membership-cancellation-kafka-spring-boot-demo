package com.example.gym.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor  // ✅ Required for Jackson
public class MembershipCancellationEvent {
    private String memberId;
    private String memberName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancellationTime;

    public MembershipCancellationEvent(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.cancellationTime = LocalDateTime.now();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}
