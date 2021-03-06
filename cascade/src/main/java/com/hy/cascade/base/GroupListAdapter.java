package com.hy.cascade.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.cascade.R;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.listener.OnGroupItemClickListener;
import com.hy.cascade.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/11 9:57
 * @desc:
 */
public class GroupListAdapter extends RecyclerView.Adapter<SmartVH> {
    private OnGroupItemClickListener mMemberClickListener;
    private List<BaseBean> mDates = new ArrayList<>();
    private boolean isOpenTree;
    private boolean isOpenMultiSelect;
    private int mGroupItemBGColor;
    private int mGroupItemColor;
    private int mGroupMemberBGColor;
    private int mGroupOpenTagColor;

    public GroupListAdapter(boolean isOpenTree, boolean isOpenMultiSelect, int groupItemColor, int groupItemBGColor, int groupMemberBGColor, int groupOpenTagColor) {
        this.isOpenTree = isOpenTree;
        this.isOpenMultiSelect = isOpenMultiSelect;
        this.mGroupItemBGColor = groupItemBGColor;
        this.mGroupItemColor = groupItemColor;
        this.mGroupMemberBGColor = groupMemberBGColor;
        this.mGroupOpenTagColor = groupOpenTagColor;
    }

    @Override
    public int getItemViewType(int position) {
        return mDates.get(position).getItemType();
    }

    public void setDates(List<BaseBean> dates) {
        if (isOpenTree) {
            mDates.clear();
            List<BaseBean> baseBeans = new ArrayList<>(dates);
            for (BaseBean baseBean : baseBeans) {
                GroupBean bean = (GroupBean) baseBean;
                initDates(bean);
            }
        } else {
            this.mDates = dates;
        }
        notifyDataSetChanged();
    }

    private void initDates(GroupBean groupItem) {
        groupItem.setSelect(true);
        this.mDates.add(groupItem);
//        System.out.print(groupItem.getGroupName() + "--");
        if (groupItem.getGroupMembers() != null && groupItem.getGroupMembers().size() > 0) {
            List<BaseBean> cascades = new ArrayList<>(groupItem.getGroupMembers());
            this.mDates.addAll(cascades);
//            System.out.print("分类成员数：" + groupItem.getGroupMembers().size());
        }

        if (groupItem.getChildren() != null && groupItem.getChildren().size() > 0) {
            List<BaseBean> cascades = new ArrayList<>(groupItem.getChildren());
//            System.out.print("   子成员数量：" + groupItem.getChildren().size());
            for (BaseBean baseBean : cascades) {
                GroupBean bean = (GroupBean) baseBean;
                initDates(bean);
            }
        }
//        System.out.println("   结束");
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == BaseBean.TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_group_item, null);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_group, null);
        }
        return new SmartVH(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(SmartVH holder, int position) {
        BaseBean cascadeBean = mDates.get(position);
        if (cascadeBean.getItemType() == BaseBean.TYPE_ITEM) {
            holder.itemView.setBackgroundColor(mGroupMemberBGColor);
            BaseGroupMemberBO itemBean = (BaseGroupMemberBO) cascadeBean;
            holder.getText(R.id.item_pro).setText(StringUtil.isEmpty(itemBean.getGroupMemberName()));
            holder.getText(R.id.item_name).setText(StringUtil.isEmpty(itemBean.getGroupMemberName()));
            holder.getText(R.id.item_number).setText(StringUtil.isEmpty(itemBean.getGroupMemberNumber()));
            holder.getText(R.id.item_member_job).setText(StringUtil.isEmpty(itemBean.getGroupMemberJob()));
            TextView selectTag = holder.getText(R.id.cascade_select_tag);
            selectTag.setVisibility(isOpenMultiSelect ? View.VISIBLE : View.INVISIBLE);
            selectTag.setOnClickListener(v -> v.setSelected(!v.isSelected()));
            LinearLayout mLineContainer = holder.getViewById(R.id.line_container);
            if (itemBean.getLevel() > 1) {
                if (itemBean.isEnd()) {
                    addEndTag(mLineContainer, cascadeBean.getLevel() + 1);
                } else {
                    addLineTag(mLineContainer, cascadeBean.getLevel());
                }
            }
            holder.itemView.setOnClickListener(v -> {
                if (mMemberClickListener != null) {
                    mMemberClickListener.onItemClick(itemBean, position);
                }
            });
        } else {
            holder.itemView.setBackgroundColor(mGroupItemBGColor);
            TextView groupName = holder.getText(R.id.item_group_name);
            GroupBean groupItem = (GroupBean) cascadeBean;
            groupName.setText(groupItem.getGroupName());
            groupName.setTextColor(mGroupItemColor);
            ImageView selectTag = holder.getImage(R.id.item_group_select_tag);
            LinearLayout mLineContainer = holder.getViewById(R.id.line_container);
            mLineContainer.setVisibility(groupItem.getLevel() == 1 ? View.GONE : View.VISIBLE);
            if (groupItem.getLevel() > 1) {
                if (groupItem.isEnd()) {
                    addEndTag(mLineContainer, groupItem.getLevel());
                } else {
                    addBranchTag(mLineContainer, groupItem.getLevel());
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectTag.getDrawable().setTint(mGroupOpenTagColor);
            }
            selectTag.setVisibility(groupItem.getChildren() == null && groupItem.getGroupMembers() == null ? View.GONE : View.VISIBLE);
            selectTag.setSelected(groupItem.isSelect());
            selectTag.setOnClickListener(v -> {
                v.setSelected(!v.isSelected());
                groupItem.setSelect(v.isSelected());
                if (v.isSelected()) {
                    if (groupItem.getGroupMembers() != null && groupItem.getGroupMembers().size() > 0)
                        addCascadeItem(position, groupItem.getGroupMembers());
                    if (groupItem.getChildren() != null && groupItem.getChildren().size() > 0)
                        addCascadeGroup(groupItem.getGroupMembers().size() + position, groupItem.getChildren());
                } else {
                    removeCascade(cascadeBean);
                }
            });
        }
    }

    private void addLineTag(LinearLayout mLineContainer, int level) {
        mLineContainer.removeAllViews();
        for (int i = 0; i < level; i++) {
            addLine(mLineContainer);
        }
    }

    private void addBranchTag(LinearLayout mLineContainer, int level) {
        mLineContainer.removeAllViews();
        for (int i = 0; i < level - 1; i++) {
            if (i == (level - 2)) {
                LayoutInflater.from(mLineContainer.getContext()).inflate(R.layout.include_branch_tag, mLineContainer);
            } else {
                addLine(mLineContainer);
            }
        }
    }

    private void addEndTag(LinearLayout mLineContainer, int level) {
        mLineContainer.removeAllViews();
        for (int i = 0; i < level - 1; i++) {
            if (i == (level - 2)) {
                LayoutInflater.from(mLineContainer.getContext()).inflate(R.layout.include_end_tag, mLineContainer);
            } else {
                addLine(mLineContainer);
            }
        }

    }

    private void addLine(LinearLayout mLineContainer) {
        float density = mLineContainer.getContext().getResources().getDisplayMetrics().density;
        View view = LayoutInflater.from(mLineContainer.getContext()).inflate(R.layout.include_line_tag, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(3, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, (int) (23 * density), 0);
        mLineContainer.addView(view, params);
    }


    private void removeCascade(BaseBean baseCascade) {
        GroupBean groupItem = (GroupBean) baseCascade;
        List<BaseGroupMemberBO> dates = groupItem.getGroupMembers();
        mDates.removeAll(dates);

        List<GroupBean> children = groupItem.getChildren();
        if (children != null && children.size() > 0) {
            for (GroupBean bean : children) {
                if (bean.isSelect()) {
                    removeCascade(bean);
                    bean.setSelect(false);
                }
            }
            mDates.removeAll(children);
        }
        notifyDataSetChanged();
    }

    private void addCascadeItem(int clickIndex, List<BaseGroupMemberBO> itemBeans) {
        List<BaseBean> cascades = new ArrayList<>(itemBeans);
        cascades.get(cascades.size() - 1).setEnd(true);
        this.mDates.addAll(clickIndex + 1, cascades);
//        notifyItemRangeInserted(clickIndex + 1, itemBeans.size());
        notifyDataSetChanged();
    }

    private void addCascadeGroup(int clickIndex, List<GroupBean> itemBeans) {
        List<BaseBean> cascades = new ArrayList<>(itemBeans);
        cascades.get(cascades.size() - 1).setEnd(true);
        this.mDates.addAll(clickIndex + 1, cascades);
//        notifyItemRangeInserted(clickIndex + 1, itemBeans.size());
        notifyDataSetChanged();
    }

    public void setOnMemberClickListener(OnGroupItemClickListener mMemberClickListener) {
        this.mMemberClickListener = mMemberClickListener;
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public List<BaseBean> getGroupMembers() {
        return mDates;
    }


}
