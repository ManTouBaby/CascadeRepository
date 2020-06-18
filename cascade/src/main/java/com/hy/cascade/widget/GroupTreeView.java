package com.hy.cascade.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.cascade.R;
import com.hy.cascade.base.BaseBean;
import com.hy.cascade.base.GroupBean;
import com.hy.cascade.base.GroupListAdapter;
import com.hy.cascade.base.MemberSearchAdapter;
import com.hy.cascade.database.BaseGroupBO;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.database.DBHelper;
import com.hy.cascade.database.MemberDAO;
import com.hy.cascade.listener.OnGroupItemClickListener;
import com.hy.cascade.listener.OnMemberClickListener;
import com.hy.cascade.utils.ArrayToTreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/16 17:42
 * @desc:
 */
public class GroupTreeView extends LinearLayout implements View.OnClickListener, OnGroupItemClickListener {
    private final RecyclerView mCascadeList;
    private final TextView mCancel;
    private OnMemberClickListener mMemberClickListener;

    private final SharedPreferences mSharedPreferences;
    private final String mRootGroupID;
    private final String mRootGroupName;
    private final MemberDAO mMemberDao;
    private String ROOT_GROUP = "ROOT_GROUP";
    private String ROOT_GROUP_NAME = "ROOT_GROUP_NAME";
    private String ROOT_GROUP_ID = "ROOT_GROUP_ID";
    private String GROUP_LIST = "GROUP_LIST";
    private String GROUP_MEMBERS = "GROUP_MEMBERS";
    private EditText mEditText;

    private GroupListAdapter mCascadeAdapter;
    private MemberSearchAdapter mMemberSearchAdapter;

    private List<BaseGroupBO> mBaseGroupBOS;
    private List<BaseGroupMemberBO> mBaseGroupMemberBOS;

    boolean isShowRoot;
    boolean isOpenMultiSelect;
    int mGroupItemBGColor;//群组背景


    public GroupTreeView(Context context) {
        this(context, null);
    }

    public GroupTreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GroupTreeView);
        mGroupItemBGColor = array.getColor(R.styleable.GroupTreeView_groupItemBGColor, Color.parseColor("#e2e2e2"));
        isOpenMultiSelect = array.getBoolean(R.styleable.GroupTreeView_isOpenMultiSelect, false);
        isShowRoot = array.getBoolean(R.styleable.GroupTreeView_isShowRoot, false);
        mMemberDao = DBHelper.getInstance(context).createMemberDao();

        LayoutInflater.from(context).inflate(R.layout.include_group_tree_show, this);
        mCancel = findViewById(R.id.mi_cancel_search_history);
        mCancel.setOnClickListener(this);
        mCancel.setVisibility(GONE);
        mCascadeList = findViewById(R.id.rv_cascade_list);
        mMemberSearchAdapter = new MemberSearchAdapter();
        mCascadeAdapter = new GroupListAdapter(isOpenMultiSelect, mGroupItemBGColor);

        initEditText();
        mCascadeList.setLayoutManager(new LinearLayoutManager(context));
        mCascadeList.setHasFixedSize(true);
        mCascadeList.setAdapter(mCascadeAdapter);
        mCascadeAdapter.setOnMemberClickListener(this);
        mMemberSearchAdapter.setOnMemberClickListener(this);
        mSharedPreferences = context.getSharedPreferences(ROOT_GROUP, Context.MODE_PRIVATE);

        mBaseGroupBOS = mMemberDao.queryAllGroup();
        mBaseGroupMemberBOS = mMemberDao.queryAllMember();
        mRootGroupName = mSharedPreferences.getString(ROOT_GROUP_NAME, null);
        mRootGroupID = mSharedPreferences.getString(ROOT_GROUP_ID, null);
        if (!TextUtils.isEmpty(mRootGroupID)) initDate(mRootGroupID, mRootGroupName);
    }

    private void initEditText() {
        mEditText = findViewById(R.id.mi_history_search_input);
        mEditText.setOnClickListener(v -> {
            mCascadeList.setAdapter(mMemberSearchAdapter);
            mCancel.setVisibility(VISIBLE);
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<BaseGroupMemberBO> groupMemberBOS = mMemberDao.queryMemberByNumberOrName(s.toString(), s.toString(), s.toString());
                mMemberSearchAdapter.setDates(s.toString(), groupMemberBOS);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getParentGroup(BaseGroupBO baseGroupBO, List<BaseGroupBO> baseGroupBOS) {
        for (BaseGroupBO groupBO : mBaseGroupBOS) {
            if (groupBO.getGroupId().equals(baseGroupBO.getParentGroupId())) {
                if (!baseGroupBOS.contains(groupBO)) baseGroupBOS.add(groupBO);
                getParentGroup(groupBO, baseGroupBOS);
                break;
            }
        }
    }

    public <G extends BaseGroupBO, M extends BaseGroupMemberBO> void setGroupBOS(String rootGroupId, String rootGroupName, List<G> baseGroupBOS, List<M> baseGroupMemberBOS) {
        mBaseGroupBOS = new ArrayList<>(baseGroupBOS);
        mBaseGroupMemberBOS = new ArrayList<>(baseGroupMemberBOS);
        mMemberDao.insertGroups(mBaseGroupBOS);
        mMemberDao.insertGroupMembers(mBaseGroupMemberBOS);
        mSharedPreferences.edit().putString(ROOT_GROUP_ID, rootGroupId).apply();
        mSharedPreferences.edit().putString(ROOT_GROUP_NAME, rootGroupName).apply();
        initDate(rootGroupId, rootGroupId);
    }

    private void initDate(String rootGroupId, String rootGroupName) {
        List<GroupBean> groupBeans = ArrayToTreeUtil.groupToTree(isShowRoot, false, rootGroupId, rootGroupName, mBaseGroupBOS, mBaseGroupMemberBOS);
        List<BaseBean> baseBeans = new ArrayList<>(groupBeans);
        mCascadeAdapter.setDates(baseBeans);
    }

    @Override
    public void onClick(View v) {
        mEditText.setText("");
        v.setVisibility(GONE);
        mCascadeList.setAdapter(mCascadeAdapter);
    }

    public void setOnMemberClickListener(OnMemberClickListener mMemberClickListener) {
        this.mMemberClickListener = mMemberClickListener;
    }

    @Override
    public void onItemClick(BaseGroupMemberBO itemBean, int position) {
        if (mMemberClickListener != null) {
            mMemberClickListener.onMemberClick(itemBean);
        }
    }
}
