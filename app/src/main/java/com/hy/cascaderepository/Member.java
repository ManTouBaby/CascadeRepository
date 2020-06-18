package com.hy.cascaderepository;

import com.hy.cascade.database.BaseGroupMemberBO;

/**
 * @author:MtBaby
 * @date:2020/06/17 10:27
 * @desc:
 */
public class Member extends BaseGroupMemberBO {

    /**
     * id : F71D16020E3F4D4699B4D1D80E68ADDC
     * usercode : 13660014262
     * activitisync : null
     * realname : 张书燕
     * status : 1
     * userType : mj
     * isDel : 0
     * username : null
     * phone : 13660014262
     * createTime : null
     * createUserId : null
     * tag : null
     * tags : null
     * minCreateTime : null
     * maxCreateTime : null
     * orgainId : 783755097-02
     * orgainName : 办公行政组
     * roleId : null
     * roleStr : 超级管理员
     * checked : null
     * orgainLevel : null
     * treeLevel3 : null
     * treeLevel3Name : null
     * roleList : null
     * pwd : null
     * confirmPwd : null
     * pwdId : null
     * clientStr : 统一用户管理
     * gender : 男
     * bornDate : 12-7月 -18 12.00.00.000000000 上午
     * userLevel : 科员
     * idNumber : null
     * isLeader : null
     * isOnline : null
     * onlineClient : null
     * fixedPhone : null
     * zdPhone : null
     * operaterIp : null
     * perList : null
     * clientList : null
     * menuList : null
     * roleName : null
     * updateTime : null
     * cjr : null
     * gxr : null
     * cjsj : 2018-07-12 00:00:00
     * gxsj : null
     * sfsy : 1
     * sjbb : 1
     */

    private String id;
    private String usercode;
    private String activitisync;
    private String realname;
    private String status;
    private String userType;
    private String isDel;
    private String username;
    private String phone;
    private String createTime;
    private String createUserId;
    private String tag;
    private String tags;
    private String minCreateTime;
    private String maxCreateTime;
    private String orgainId;
    private String orgainName;
    private String roleId;
    private String roleStr;
    private String checked;
    private String orgainLevel;
    private String treeLevel3;
    private String treeLevel3Name;
    private String roleList;
    private String pwd;
    private String confirmPwd;
    private String pwdId;
    private String clientStr;
    private String gender;
    private String bornDate;
    private String userLevel;
    private String idNumber;
    private String isLeader;
    private String isOnline;
    private String onlineClient;
    private String fixedPhone;
    private String zdPhone;
    private String operaterIp;
    private String perList;
    private String clientList;
    private String menuList;
    private String roleName;
    private String updateTime;
    private String cjr;
    private String gxr;
    private String cjsj;
    private String gxsj;
    private String sfsy;
    private String sjbb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        setGroupMemberId(id);
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        setGroupMemberNumber(usercode);
        this.usercode = usercode;
    }

    public String getActivitisync() {
        return activitisync;
    }

    public void setActivitisync(String activitisync) {
        this.activitisync = activitisync;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        setGroupMemberName(realname);
        this.realname = realname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(String minCreateTime) {
        this.minCreateTime = minCreateTime;
    }

    public String getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(String maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public String getOrgainId() {
        return orgainId;
    }

    public void setOrgainId(String orgainId) {
        setGroupId(orgainId);
        this.orgainId = orgainId;
    }

    public String getOrgainName() {
        return orgainName;
    }

    public void setOrgainName(String orgainName) {
        setGroupName(orgainName);
        this.orgainName = orgainName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        setGroupMemberJob(roleStr);
        this.roleStr = roleStr;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getOrgainLevel() {
        return orgainLevel;
    }

    public void setOrgainLevel(String orgainLevel) {
        this.orgainLevel = orgainLevel;
    }

    public String getTreeLevel3() {
        return treeLevel3;
    }

    public void setTreeLevel3(String treeLevel3) {
        this.treeLevel3 = treeLevel3;
    }

    public String getTreeLevel3Name() {
        return treeLevel3Name;
    }

    public void setTreeLevel3Name(String treeLevel3Name) {
        this.treeLevel3Name = treeLevel3Name;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getPwdId() {
        return pwdId;
    }

    public void setPwdId(String pwdId) {
        this.pwdId = pwdId;
    }

    public String getClientStr() {
        return clientStr;
    }

    public void setClientStr(String clientStr) {
        this.clientStr = clientStr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getOnlineClient() {
        return onlineClient;
    }

    public void setOnlineClient(String onlineClient) {
        this.onlineClient = onlineClient;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getZdPhone() {
        return zdPhone;
    }

    public void setZdPhone(String zdPhone) {
        this.zdPhone = zdPhone;
    }

    public String getOperaterIp() {
        return operaterIp;
    }

    public void setOperaterIp(String operaterIp) {
        this.operaterIp = operaterIp;
    }

    public String getPerList() {
        return perList;
    }

    public void setPerList(String perList) {
        this.perList = perList;
    }

    public String getClientList() {
        return clientList;
    }

    public void setClientList(String clientList) {
        this.clientList = clientList;
    }

    public String getMenuList() {
        return menuList;
    }

    public void setMenuList(String menuList) {
        this.menuList = menuList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getGxr() {
        return gxr;
    }

    public void setGxr(String gxr) {
        this.gxr = gxr;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public String getSfsy() {
        return sfsy;
    }

    public void setSfsy(String sfsy) {
        this.sfsy = sfsy;
    }

    public String getSjbb() {
        return sjbb;
    }

    public void setSjbb(String sjbb) {
        this.sjbb = sjbb;
    }
}
