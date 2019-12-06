package com.rabbitmq.provider.domin;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class User implements Serializable {
    private String username;
    private String password;
}
