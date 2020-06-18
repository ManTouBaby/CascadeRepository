package com.hy.cascaderepository;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.hy.cascade.database.BaseGroupMemberBO;
import com.hy.cascade.listener.OnMemberClickListener;
import com.hy.cascade.widget.GroupTreeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMemberClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroupTreeView groupTreeView = findViewById(R.id.gt_view);
        groupTreeView.setOnMemberClickListener(this);
        String departJson = getJson(this, "depart.json");
        String memberJson = getJson(this, "member.json");
        List<Member> members = JSON.parseArray(memberJson, Member.class);
        List<Depart> departs = JSON.parseArray(departJson, Depart.class);
        for (Member member : members) {
            member.setMemberData(member);
        }
        for (Depart depart : departs) {
            depart.setGroupData(depart);
        }
        groupTreeView.setGroupBOS("783755097", "花都分局", departs, members);
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
}
