package com.hy.cascade.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

/**
 * @author:MtBaby
 * @date:2020/06/16 17:30
 * @desc:
 */
@Entity(indices = {@Index("groupId")})
@TypeConverters({ConverterObject.class})
public class BaseGroupBO {

    /**
     * id : 783755097-01
     * pId : 783755097
     * name : 领导
     */
    @NonNull
    @PrimaryKey
    private String groupId;//单位ID
    private String parentGroupId;//上级单位ID
    private String groupName;//单位名称
    private Object groupData;//单位数据

    @NonNull
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(@NonNull String groupId) {
        this.groupId = groupId;
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Object getGroupData() {
        return groupData;
    }

    public void setGroupData(Object groupData) {
        this.groupData = groupData;
    }
}
