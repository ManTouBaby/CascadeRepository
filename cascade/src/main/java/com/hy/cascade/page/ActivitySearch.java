package com.hy.cascade.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.hy.cascade.R;
import com.hy.cascade.base.MemberSearchAdapter;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.database.DBHelper;
import com.hy.cascade.database.MemberDAO;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/18 14:47
 * @desc:
 */
public class ActivitySearch extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    MemberSearchAdapter mMemberSearchAdapter;
    private MemberDAO mMemberDao;
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById(R.id.mi_cancel_search_history).setOnClickListener(this);
        mEditText = findViewById(R.id.mi_history_search_input);
        mEditText.addTextChangedListener(this);
        RecyclerView mCascadeList = findViewById(R.id.rv_cascade_list);
        mCascadeList.setLayoutManager(new LinearLayoutManager(this));
        mCascadeList.setHasFixedSize(true);
        mMemberDao = DBHelper.getInstance(this).createMemberDao();
        mCascadeList.setAdapter(mMemberSearchAdapter = new MemberSearchAdapter());

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        List<BaseGroupMemberBO> groupMemberBOS = mMemberDao.queryMemberByNumberOrName(s.toString(), s.toString(),s.toString());
        mMemberSearchAdapter.setDates(s.toString(), groupMemberBOS);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
