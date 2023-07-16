package com.rrmsbackend.eneity.auth;

import lombok.Data;

@Data
public class Account {
    String username;
    String password;
    String email;
}
