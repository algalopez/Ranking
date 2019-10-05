package com.algalopez.ranking.user.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}
