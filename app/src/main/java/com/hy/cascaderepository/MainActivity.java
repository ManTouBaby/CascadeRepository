package com.hy.cascaderepository;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.listener.OnGroupRefreshListener;
import com.hy.cascade.listener.OnMemberClickListener;
import com.hy.cascade.widget.GroupTreeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnMemberClickListener,  SwipeRefreshLayout.OnRefreshListener {

    private List<Member> mMembers;
    private List<AllDepart> mDeparts;
    private GroupTreeView mGroupTreeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGroupTreeView = findViewById(R.id.gt_view);
        mGroupTreeView.setOnMemberClickListener(this);
        mGroupTreeView.setOnGroupRefreshListener(this);
        String departJson = getJson(this, "allDepart.json");
        String memberJson = getJson(this, "allMember.json");
        mMembers = JSON.parseArray(memberJson, Member.class);
        mDeparts = JSON.parseArray(departJson, AllDepart.class);
        for (Member member : mMembers) {
            member.setMemberData(member);
        }
        for (AllDepart depart : mDeparts) {
            depart.setGroupData(depart);
        }
//        groupTreeView.setGroupBOS("783755097", "花都分局", departs, members);
        initDates();
    }

    private void initDates() {
        mGroupTreeView.setGroupBOS("440114000000", "花都分局", mDeparts, mMembers);
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void onMemberClick(BaseGroupMemberBO groupMemberBO) {
        System.out.println(groupMemberBO.getGroupMemberName());
    }


    @Override
    public void onRefresh() {
//        initDates();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mGroupTreeView.setRefreshFinish();
                    }
                });

            }
        },3000);
    }
}
