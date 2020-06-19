package com.hy.cascade.utils;

import com.hy.cascade.base.BaseBean;
import com.hy.cascade.database.BaseGroupBO;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.base.GroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/17 12:34
 * @desc:
 */
public class ArrayToTreeUtil {
    public static List<GroupBean> groupToTree(boolean isShowRoot, boolean isSelect, String rootGroupId, String rootGroupName, List<BaseGroupBO> baseGroupBOS, List<BaseGroupMemberBO> baseGroupMemberBOS) {
        List<GroupBean> groupBeans = new ArrayList<>();
        if (isShowRoot) {
            List<GroupBean> childrenGroup = new ArrayList<>();
            List<BaseGroupMemberBO> memberBOS = getMembersByGroupId(1, rootGroupId, baseGroupMemberBOS);
            GroupBean groupBean = new GroupBean();
            groupBean.setChildren(childrenGroup);
            groupBean.setGroupMembers(memberBOS);
            groupBean.setGroupName(rootGroupName);
            groupBean.setSelect(isSelect);
            groupBean.setGroupId(rootGroupId);
            groupBean.setItemType(BaseBean.TYPE_GROUP);
            groupBean.setLevel(1);
            groupBeans.add(groupBean);
        }

        if (isShowRoot) {
            for (GroupBean groupBean : groupBeans) {
                List<GroupBean> beans = toTree(2, isSelect, groupBean.getGroupId(), groupBean.getGroupName(), baseGroupBOS, baseGroupMemberBOS);
                groupBean.setChildren(beans);
            }
        } else {
            groupBeans.addAll(toTree(1, isSelect, rootGroupId, rootGroupName, baseGroupBOS, baseGroupMemberBOS));
        }

        return groupBeans;
    }

    private static List<GroupBean> toTree(int level, boolean isSelect, String rootGroupId, String rootGroupName, List<BaseGroupBO> baseGroupBOS, List<BaseGroupMemberBO> baseGroupMemberBOS) {
        List<GroupBean> groupBeans = new ArrayList<>();
        if (baseGroupBOS != null) for (BaseGroupBO baseGroupBO : baseGroupBOS) {
            String parentGroupId = baseGroupBO.getParentGroupId();
            if (rootGroupId.equals(parentGroupId)) {
                GroupBean bean = new GroupBean();
                String groupId = baseGroupBO.getGroupId();
                String groupName = baseGroupBO.getGroupName();
                List<BaseGroupMemberBO> membersByGroupS = getMembersByGroupId(level, groupId, baseGroupMemberBOS);
                List<GroupBean> beans = toTree(level + 1, isSelect, groupId, groupName, baseGroupBOS, baseGroupMemberBOS);
                bean.setItemType(BaseBean.TYPE_GROUP);
                bean.setpGroupId(parentGroupId);
                bean.setGroupName(groupName);
                bean.setGroupId(groupId);
                bean.setChildren(beans);
                bean.setSelect(isSelect);
                bean.setLevel(level);
                bean.setGroupMembers(membersByGroupS);
                groupBeans.add(bean);
            }
        }
        if (groupBeans.size() > 0) groupBeans.get(groupBeans.size() - 1).setEnd(true);
        return groupBeans;
    }

    private static List<BaseGroupMemberBO> getMembersByGroupId(int level, String groupId, List<BaseGroupMemberBO> baseGroupMemberBOS) {
        List<BaseGroupMemberBO> memberBOS = new ArrayList<>();
        if (baseGroupMemberBOS != null) for (BaseGroupMemberBO groupMemberBO : baseGroupMemberBOS) {
            if (groupMemberBO.getGroupId().equals(groupId)) {
                groupMemberBO.setItemType(BaseBean.TYPE_ITEM);
                groupMemberBO.setLevel(level);
                memberBOS.add(groupMemberBO);
            }
        }
        if (memberBOS.size() > 0) memberBOS.get(memberBOS.size() - 1).setEnd(true);
        return memberBOS;
    }
}
