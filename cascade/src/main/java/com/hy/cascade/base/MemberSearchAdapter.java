package com.hy.cascade.base;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.cascade.R;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.listener.OnGroupItemClickListener;
import com.hy.cascade.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/18 14:53
 * @desc:
 */
public class MemberSearchAdapter extends RecyclerView.Adapter<SmartVH> {
    private OnGroupItemClickListener mMemberClickListener;
    private List<BaseGroupMemberBO> mDates = new ArrayList<>();
    private String searchLabel;
    private int mGroupMemberBGColor;

    public MemberSearchAdapter( int mGroupMemberBGColor) {
        this.mGroupMemberBGColor = mGroupMemberBGColor;
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_member, null);
        return new SmartVH(view);
    }

    @Override
    public void onBindViewHolder(SmartVH holder, int position) {
        holder.itemView.setBackgroundColor(mGroupMemberBGColor);
        BaseGroupMemberBO cascadeBean = mDates.get(position);
        String groupMemberName = cascadeBean.getGroupMemberName();
        String groupMemberNumber = cascadeBean.getGroupMemberNumber();
        if (!TextUtils.isEmpty(groupMemberName)) {
            int holderShowNameIndex = groupMemberName.indexOf(searchLabel);
            SpannableStringBuilder messageHolderShowName = new SpannableStringBuilder(groupMemberName);
            if (holderShowNameIndex != -1) {
                messageHolderShowName.setSpan(new ForegroundColorSpan(Color.RED), holderShowNameIndex, (holderShowNameIndex + searchLabel.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.getText(R.id.item_name).setText(messageHolderShowName);
        }
        if (!TextUtils.isEmpty(groupMemberNumber)) {
            int messageContentIndex = groupMemberNumber.indexOf(searchLabel);
            SpannableStringBuilder messageContent = new SpannableStringBuilder(groupMemberNumber);
            if (messageContentIndex != -1) {
                messageContent.setSpan(new ForegroundColorSpan(Color.RED), messageContentIndex, (messageContentIndex + searchLabel.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.getText(R.id.item_number).setText(messageContent);
        }
        if (TextUtils.isEmpty(cascadeBean.getGroupName())) {
            int groupNameIndex = cascadeBean.getGroupName().indexOf(searchLabel);
            SpannableStringBuilder groupName = new SpannableStringBuilder(cascadeBean.getGroupName());
            if (groupNameIndex != -1) {
                groupName.setSpan(new ForegroundColorSpan(Color.RED), groupNameIndex, (groupNameIndex + searchLabel.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.getText(R.id.item_member_depart).setText(groupName);
        }


        holder.getText(R.id.item_member_job).setText(StringUtil.isEmpty(cascadeBean.getGroupMemberJob()));

        holder.getText(R.id.item_pro).setText(groupMemberName);
        holder.itemView.setOnClickListener(v -> {
            if (mMemberClickListener != null) {
                mMemberClickListener.onItemClick(cascadeBean, position);
            }
        });
    }

    public void setDates(String searchLabel, List<BaseGroupMemberBO> mDates) {
        this.mDates = mDates;
        this.searchLabel = searchLabel;
        notifyDataSetChanged();
    }

    public void setOnMemberClickListener(OnGroupItemClickListener mMemberClickListener) {
        this.mMemberClickListener = mMemberClickListener;
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }
}
