package com.club012.vibe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private Set<Long> userIds;
}
