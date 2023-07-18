package com.rrmsbackend.eneity.user;

import lombok.Data;

@Data
public class AccountUser {
    int id;
    String identity;
    String username;
    String email;
}
