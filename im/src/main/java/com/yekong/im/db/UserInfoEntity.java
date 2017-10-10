package com.yekong.im.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by xigua on 2017/9/14.
 */
@Entity
public class UserInfoEntity {
    @Id(autoincrement = true)
    public Long id;
    @Unique
    public String userId;

    public String userName;
    public String userAvata;

    @Generated(hash = 1188877089)
    public UserInfoEntity(Long id, String userId, String userName,
            String userAvata) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userAvata = userAvata;
    }

    @Generated(hash = 2042969639)
    public UserInfoEntity() {
    }


    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvata() {
        return this.userAvata;
    }

    public void setUserAvata(String userAvata) {
        this.userAvata = userAvata;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
