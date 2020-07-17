package com.hy.cascade.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.hy.cascade.base.BaseBean;

/**
 * @author:MtBaby
 * @date:2020/06/11 9:51
 * @desc:
 */

@Entity(indices = {@Index("groupMemberNumber")})
@TypeConverters({ConverterObject.class})
public class BaseGroupMemberBO extends BaseBean {

    @NonNull
    @PrimaryKey
    private String groupMemberId;//警员Id
    private String groupMemberNumber;//警员电话号码
    private String groupMemberName;//警员名称
    private String groupMemberJob;//警员职务
    private String groupId;//警员所在单位ID
    private String groupName;//警员所在单位名称
    private Object memberData;//警员数据
    private String memberDataStr;//警员数据

    public String getMemberDataStr() {
        return memberDataStr;
    }

    public void setMemberDataStr(String memberDataStr) {
        this.memberDataStr = memberDataStr;
    }

    public String getGroupMemberJob() {
        return groupMemberJob;
    }

    public void setGroupMemberJob(String groupMemberJob) {
        this.groupMemberJob = groupMemberJob;
    }

    @NonNull
    public String getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(@NonNull String groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public String getGroupMemberName() {
        return groupMemberName;
    }

    public void setGroupMemberName(String groupMemberName) {
        this.groupMemberName = groupMemberName;
    }

    public String getGroupMemberNumber() {
        return groupMemberNumber;
    }

    public void setGroupMemberNumber(String groupMemberNumber) {
        this.groupMemberNumber = groupMemberNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Object getMemberData() {
        return memberData;
    }

    public void setMemberData(Object memberData) {
        this.memberData = memberData;
    }
}
