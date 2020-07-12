package com.github.cooker.server.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ZoomGrant 2020/5/31 15:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickRules implements Serializable {

    private static final long serialVersionUID = 2023082977167895731L;

    //客户端id
    private String clientId;
    //方法
    private String method;

    List<RickRule> rules;
}
