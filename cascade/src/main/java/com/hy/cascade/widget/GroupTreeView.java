package com.hy.cascade.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
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
    private final LinearLayout mToolbarContainer;
    private RecyclerView mCascadeList;
    private TextView mCancel;
    private EditText mEditText;
    private OnMemberClickListener mMemberClickListener;

    private SharedPreferences mSharedPreferences;
    private String mRootGroupID;
    private String mRootGroupName;
    private MemberDAO mMemberDao;
    private String ROOT_GROUP = "ROOT_GROUP";
    private String ROOT_GROUP_NAME = "ROOT_GROUP_NAME";
    private String ROOT_GROUP_ID = "ROOT_GROUP_ID";

    private GroupListAdapter mCascadeAdapter;
    private MemberSearchAdapter mMemberSearchAdapter;

    private List<BaseGroupBO> mBaseGroupBOS;
    private List<BaseGroupMemberBO> mBaseGroupMemberBOS;

    private Handler mHandler = new Handler();

    boolean isShowRoot;
    boolean isOpenMultiSelect;
    int mGroupItemBGColor;//分组背景
    int mGroupItemColor;//分组背景
    int mGroupMemberBGColor;//分组成员背景
    int mGroupTitleBGColor;//标题背景
    int mGroupTitleColor;//颜色
    int mGroupCancelBtnColor;//颜色
    int mGroupOpenTagColor;//颜色


    public GroupTreeView(Context context) {
        this(context, null);
    }

    public GroupTreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GroupTreeView);
        mGroupItemBGColor = array.getColor(R.styleable.GroupTreeView_groupItemBGColor, Color.parseColor("#FFFFFF"));
        mGroupMemberBGColor = array.getColor(R.styleable.GroupTreeView_groupMemberBGColor, Color.parseColor("#e2e2e2"));
        mGroupTitleBGColor = array.getColor(R.styleable.GroupTreeView_groupTitleBGColor, Color.parseColor("#EDEDED"));
        mGroupTitleColor = array.getColor(R.styleable.GroupTreeView_groupTitleColor, Color.parseColor("#666666"));
        mGroupCancelBtnColor = array.getColor(R.styleable.GroupTreeView_groupCancelBtnColor, Color.parseColor("#333333"));
        mGroupOpenTagColor = array.getColor(R.styleable.GroupTreeView_groupOpenTagColor, Color.parseColor("#333333"));
        mGroupItemColor = array.getColor(R.styleable.GroupTreeView_groupItemColor, Color.parseColor("#333333"));

        isOpenMultiSelect = array.getBoolean(R.styleable.GroupTreeView_isOpenMultiSelect, false);
        isShowRoot = array.getBoolean(R.styleable.GroupTreeView_isShowRoot, false);
        mMemberDao = DBHelper.getInstance(context).createMemberDao();

        LayoutInflater.from(context).inflate(R.layout.include_group_tree_show, this);
        mCancel = findViewById(R.id.mi_cancel_search_history);

        mToolbarContainer = findViewById(R.id.mi_toolbar_container);

        mToolbarContainer.setBackgroundColor(mGroupTitleBGColor);
        mCancel.setOnClickListener(this);
        mCancel.setVisibility(GONE);
        mCancel.setTextColor(mGroupCancelBtnColor);
        mCascadeList = findViewById(R.id.rv_cascade_list);
        mMemberSearchAdapter = new MemberSearchAdapter(mGroupMemberBGColor);
        mCascadeAdapter = new GroupListAdapter(isOpenMultiSelect,mGroupItemColor, mGroupItemBGColor, mGroupMemberBGColor,mGroupOpenTagColor);

        initEditText();
        mCascadeList.setLayoutManager(new LinearLayoutManager(context));
        mCascadeList.setHasFixedSize(true);
        mCascadeList.setAdapter(mCascadeAdapter);
        mCascadeAdapter.setOnMemberClickListener(this);
        mMemberSearchAdapter.setOnMemberClickListener(this);
        mSharedPreferences = context.getSharedPreferences(ROOT_GROUP, Context.MODE_PRIVATE);

        new Thread(() -> {
            mRootGroupName = mSharedPreferences.getString(ROOT_GROUP_NAME, null);
            mRootGroupID = mSharedPreferences.getString(ROOT_GROUP_ID, null);
            mBaseGroupBOS = mMemberDao.queryAllGroup();
            mBaseGroupMemberBOS = mMemberDao.queryAllMember();
            mHandler.post(() -> {
                if (!TextUtils.isEmpty(mRootGroupID)) initDate(mRootGroupID, mRootGroupName);
            });
        }).start();
    }

    private void initEditText() {
        mEditText = findViewById(R.id.mi_history_search_input);
        mEditText.setTextColor(mGroupTitleColor);
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
                new Thread(() -> {
                    List<BaseGroupMemberBO> groupMemberBOS = mMemberDao.queryMemberByNumberOrName(s.toString(), s.toString(), s.toString());
                    mHandler.post(() -> mMemberSearchAdapter.setDates(s.toString(), groupMemberBOS));
                }).start();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public <G extends BaseGroupBO, M extends BaseGroupMemberBO> void setGroupBOS(String rootGroupId, String rootGroupName, List<G> baseGroupBOS, List<M> baseGroupMemberBOS) {
        mBaseGroupBOS = new ArrayList<>(baseGroupBOS);
        mBaseGroupMemberBOS = new ArrayList<>(baseGroupMemberBOS);
        mMemberDao.insertGroups(mBaseGroupBOS);
        mMemberDao.insertGroupMembers(mBaseGroupMemberBOS);
        mSharedPreferences.edit().putString(ROOT_GROUP_ID, rootGroupId).apply();
        mSharedPreferences.edit().putString(ROOT_GROUP_NAME, rootGroupName).apply();
        initDate(rootGroupId, rootGroupName);
    }

    private void initDate(String rootGroupId, String rootGroupName) {
        new Thread(() -> {
            List<GroupBean> groupBeans = ArrayToTreeUtil.groupToTree(isShowRoot, false, rootGroupId, rootGroupName, mBaseGroupBOS, mBaseGroupMemberBOS);
            List<BaseBean> baseBeans = new ArrayList<>(groupBeans);
            mHandler.post(() -> mCascadeAdapter.setDates(baseBeans));
        }).start();
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
