package com.hy.cascade.listener;

import com.hy.cascade.database.BaseGroupMemberBO;

/**
 * @author:MtBaby
 * @date:2020/06/11 14:09
 * @desc:
 */
public interface OnGroupItemClickListener {
    void onItemClick(BaseGroupMemberBO itemBean, int position);
}
