package com.rrmsbackend.eneity.auth;

import lombok.Data;

@Data
public class Account {
    long id;
    String identity;
    String username;
    String password;
    String email;
}
