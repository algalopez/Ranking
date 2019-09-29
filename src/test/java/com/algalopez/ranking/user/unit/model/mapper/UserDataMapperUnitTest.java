package com.algalopez.ranking.user.unit.model.mapper;

import com.algalopez.ranking.user.model.*;
import com.algalopez.ranking.user.model.mapper.UserDataMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserDataMapperUnitTest {

    private UserDataMapper userDataMapper;

    @Before
    public void init() {
        userDataMapper = new UserDataMapper();
    }

    @Test
    public void testFullMapToData() {

        UserAuth modelUserAuth = buildModelUserAuth(1L, "usr", "pass", true, false, UserRole.USER);
        UserInfo modelUserInfo = buildModelUserInfo(1L, "usr", "email", UserLevel.EXPERT);

        com.algalopez.ranking.user.data.UserAuth expectedDataUserAuth = buildDataUserAuth(1L, "usr", "pass", true, false, UserRole.USER.getRoleValue());
        com.algalopez.ranking.user.data.UserInfo expectedDataUserInfo = buildDataUserInfo(1L, "usr", "email", UserLevel.EXPERT.getLevelValue());

        UserDataMapper.DataUser dataUser = userDataMapper.mapTo(User.builder().userAuth(modelUserAuth).userInfo(modelUserInfo).build());

        assertEquals(expectedDataUserAuth, dataUser.getUserAuth());
        assertEquals(expectedDataUserInfo, dataUser.getUserInfo());
    }

    @Test
    public void testEmptyMapToData() {

        UserDataMapper.DataUser dataUser = userDataMapper.mapTo(User.builder().userAuth(null).userInfo(null).build());

        assertNull(dataUser.getUserAuth());
        assertNull(dataUser.getUserInfo());
    }

    @Test
    public void testNullUserRole() {

        UserAuth modelUserAuth = buildModelUserAuth(1L, "usr", "pass", true, false, null);
        UserDataMapper.DataUser dataUser = userDataMapper.mapTo(User.builder().userAuth(modelUserAuth).userInfo(null).build());
        assertNull(dataUser.getUserAuth().getRole());
    }

    @Test
    public void testNullUserLevel() {

        UserInfo modelUserInfo = buildModelUserInfo(1L, "usr", "email", null);
        UserDataMapper.DataUser dataUser = userDataMapper.mapTo(User.builder().userAuth(null).userInfo(modelUserInfo).build());
        assertNull(dataUser.getUserInfo().getLevel());
    }

    @Test
    public void testFullMapFromData() {

        com.algalopez.ranking.user.data.UserAuth dataUserAuth = buildDataUserAuth(1L, "usr", "pass", true, false, UserRole.USER.getRoleValue());
        com.algalopez.ranking.user.data.UserInfo dataUserInfo = buildDataUserInfo(1L, "usr", "email", UserLevel.EXPERT.getLevelValue());

        UserAuth expectedModelUserAuth = buildModelUserAuth(1L, "usr", "pass", true, false, UserRole.USER);
        UserInfo expectedModelUserInfo = buildModelUserInfo(1L, "usr", "email", UserLevel.EXPERT);

        User user = userDataMapper.mapFrom(new UserDataMapper.DataUser(dataUserAuth, dataUserInfo));

        assertEquals(expectedModelUserAuth.getId(), user.getUserAuth().getId());
        assertEquals(expectedModelUserAuth.getUsername(), user.getUserAuth().getUsername());
        assertEquals("***************", user.getUserAuth().getPassword());
        assertEquals(expectedModelUserAuth.isEnabled(), user.getUserAuth().isEnabled());
        assertEquals(expectedModelUserAuth.isLocked(), user.getUserAuth().isLocked());
        assertEquals(expectedModelUserAuth.getRole(), user.getUserAuth().getRole());
        assertEquals(expectedModelUserInfo, user.getUserInfo());
    }

    @Test
    public void testEmptyMapFromData() {

        User user = userDataMapper.mapFrom(new UserDataMapper.DataUser(null, null));

        assertNull(user.getUserAuth());
        assertNull(user.getUserInfo());
    }

    private UserAuth buildModelUserAuth(Long id, String username, String password, Boolean enabled, Boolean locked, UserRole role) {
        return UserAuth.builder()
                .id(id)
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .role(role)
                .build();
    }

    private UserInfo buildModelUserInfo(Long id, String username, String email, UserLevel level) {

        return UserInfo.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }

    private com.algalopez.ranking.user.data.UserAuth buildDataUserAuth(Long id, String username, String password, Boolean enabled, Boolean locked, String role) {
        return com.algalopez.ranking.user.data.UserAuth.builder()
                .id(id)
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .role(role)
                .build();
    }

    private com.algalopez.ranking.user.data.UserInfo buildDataUserInfo(Long id, String username, String email, Integer level) {
        return com.algalopez.ranking.user.data.UserInfo.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }
}
