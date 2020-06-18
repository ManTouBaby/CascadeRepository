package com.hy.cascade.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/06/17 15:14
 * @desc:
 */
@Dao
public interface MemberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroups(List<BaseGroupBO> baseGroupBOS);

    @Query("select * from BaseGroupBO")
    List<BaseGroupBO> queryAllGroup();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroupMembers(List<BaseGroupMemberBO> baseGroupMemberBOS);

    @Query("select * from BaseGroupMemberBO")
    List<BaseGroupMemberBO> queryAllMember();

    @Query("select * from BaseGroupMemberBO where  groupMemberNumber like  '%' || :groupMemberNumber || '%'  or groupMemberName like  '%' || :groupMemberName || '%'or groupName like  '%' || :groupName || '%' ")
    List<BaseGroupMemberBO> queryMemberByNumberOrName(String groupMemberNumber, String groupMemberName, String groupName);

    @Query("select * from BaseGroupBO where   groupName like   '%' || :groupName || '%'")
    List<BaseGroupBO> queryDepartByName(String groupName);

    @Query("select * from BaseGroupMemberBO where groupMemberNumber=:groupMemberNumber")
    BaseGroupMemberBO queryMember(String groupMemberNumber);
}
