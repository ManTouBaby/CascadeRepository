package com.hy.cascade.base;

import com.hy.cascade.database.BaseGroupMemberBO;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/11 9:41
 * @desc:级联组
 */
public class GroupBean extends BaseBean {
    private String groupId;        //级联组ID
    private String pGroupId;    //父级联ID
    private String groupName;   //级联组名称
    private List<BaseGroupMemberBO> groupMembers;
    private List<GroupBean> children;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getpGroupId() {
        return pGroupId;
    }

    public void setpGroupId(String pGroupId) {
        this.pGroupId = pGroupId;
    }

    public List<GroupBean> getChildren() {
        return children;
    }

    public void setChildren(List<GroupBean> children) {
        this.children = children;
    }

    public List<BaseGroupMemberBO> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<BaseGroupMemberBO> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
