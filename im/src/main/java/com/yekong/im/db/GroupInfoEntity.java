package com.yekong.im.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xigua on 2017/9/14.
 */
@Entity
public class GroupInfoEntity {
    @Id(autoincrement = true)
    public Long id;
    @Unique
    public String groupId;

    public String groupName;
    public String groupAvata;
    @Generated(hash = 1633849281)
    public GroupInfoEntity(Long id, String groupId, String groupName,
            String groupAvata) {
        this.id = id;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupAvata = groupAvata;
    }
    @Generated(hash = 1170648783)
    public GroupInfoEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupAvata() {
        return this.groupAvata;
    }
    public void setGroupAvata(String groupAvata) {
        this.groupAvata = groupAvata;
    }

}
