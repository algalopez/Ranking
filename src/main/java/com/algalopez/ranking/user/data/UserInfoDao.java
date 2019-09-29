package com.algalopez.ranking.user.data;

public interface UserInfoDao {

    UserInfo findUserInfoById(Long id);

    Long createUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
