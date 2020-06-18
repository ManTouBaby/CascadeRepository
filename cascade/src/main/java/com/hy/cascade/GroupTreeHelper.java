package com.hy.cascade;

import android.content.Context;

import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.database.DBHelper;
import com.hy.cascade.database.MemberDAO;

/**
 * @author:MtBaby
 * @date:2020/06/17 17:34
 * @desc:
 */
public class GroupTreeHelper {
    private static GroupTreeHelper groupTreeHelper;
    private final MemberDAO mMemberDao;

    private GroupTreeHelper(Context context) {
        mMemberDao = DBHelper.getInstance(context).createMemberDao();
    }

    public static GroupTreeHelper getInstance(Context context) {
        if (groupTreeHelper == null) {
            synchronized (GroupTreeHelper.class) {
                if (groupTreeHelper == null) {
                    groupTreeHelper = new GroupTreeHelper(context);
                }
            }
        }
        return groupTreeHelper;
    }

    public BaseGroupMemberBO queryMember(String phoneNumber) {
        return mMemberDao.queryMember(phoneNumber);
    }


}
