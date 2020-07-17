package com.hy.cascade.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @author:MtBaby
 * @date:2020/06/17 15:12
 * @desc:
 */
@Database(entities = {BaseGroupBO.class, BaseGroupMemberBO.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    abstract MemberDAO createMemberDao();
}

