package com.hy.cascaderepository;

import com.hy.cascade.database.BaseGroupBO;

/**
 * @author:MtBaby
 * @date:2020/06/17 10:27
 * @desc:
 */
public class Depart extends BaseGroupBO {


    /**
     * id : 783755097-01
     * pId : 783755097
     * name : 领导
     * userType : null
     * checked : 0
     * isParent : 1
     * uniqueKey : null
     * usercode : null
     */

    private String id;
    private String pId;
    private String name;
    private Object userType;
    private int checked;
    private int isParent;
    private Object uniqueKey;
    private Object usercode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        setGroupId(id);
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        setParentGroupId(pId);
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setGroupName(name);
        this.name = name;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public Object getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(Object uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Object getUsercode() {
        return usercode;
    }

    public void setUsercode(Object usercode) {
        this.usercode = usercode;
    }
}
