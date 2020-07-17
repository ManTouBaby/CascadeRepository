package com.hy.cascade.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author:MtBaby
 * @date:2020/04/08 12:01
 * @desc:
 */
public class DBHelper {
    private final AppDataBase mDataBase;
    private static DBHelper mDbHelper;

    public static DBHelper getInstance(Context context) {
        if (mDbHelper == null) {
            synchronized (DBHelper.class) {
                if (mDbHelper == null) {
                    mDbHelper = new DBHelper(context);
                }
            }
        }
        return mDbHelper;
    }

    private DBHelper(Context context) {
        Migration migration1_2 = new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
//                database.execSQL("alter table instructModel add duration integer NOT NULL DEFAULT 0");
//                database.execSQL("alter table instructModel add fileSize real  NOT NULL DEFAULT 0");
                database.execSQL("alter table BaseGroupMemberBO add memberDataStr text");
            }
        };
        mDataBase = Room.databaseBuilder(context, AppDataBase.class, "member.db")
                .addMigrations(migration1_2)
                .allowMainThreadQueries()
                .build();
    }

    public MemberDAO createMemberDao() {
        return mDataBase.createMemberDao();
    }

}
