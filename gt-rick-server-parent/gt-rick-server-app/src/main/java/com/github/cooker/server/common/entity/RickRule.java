package com.github.cooker.server.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ZoomGrant 2020/5/31 15:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickRule implements Serializable {

    private static final long serialVersionUID = 8115723710783092147L;

    private String clientId;

    RuleType ruleType;
}
