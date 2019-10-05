package com.algalopez.ranking.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    UserInfo userInfo;
    UserAuth userAuth;
}
