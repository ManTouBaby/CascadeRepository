package com.hy.cascaderepository;

import com.hy.cascade.database.BaseGroupBO;

/**
 * @author:MtBaby
 * @date:2020/06/17 15:31
 * @desc:
 */
public class AllDepart extends BaseGroupBO {
    /**
     * id : ZB0115000-01
     * uuid : ZB0115000-01
     * parentId : ZB0115000
     * name : 新华临时
     * fullName : 新华临时
     * orderNum : 1274
     * type : 1
     * shortName : 新华临时
     * zb : null
     * tag : null
     * businessId : 1
     * shortPym : null
     * phoneAll : null
     * fax : null
     * orgAddress : null
     * email : null
     * inlineCode : null
     * longitude : null
     * latitude : null
     * stationNumber : null
     * sfcx : 0
     * checked : null
     * childCount : 0
     * shortNamePa : 新华临时
     * childrenList : null
     * parentName : 临时单位
     * orgId : null
     * clientStr : null
     * orgLevel : 4
     * cjr : 88888888888888888888888888888888
     * gxr : null
     * cjsj : 2018-07-11 00:00:00
     * gxsj : 2018-07-11 00:00:00
     * sfsy : 1
     * sjbb : 1
     * fjCode : 440114000000
     */

    private String id;
    private String uuid;
    private String parentId;
    private String name;
    private String fullName;
    private int orderNum;
    private String type;
    private String shortName;
    private Object zb;
    private Object tag;
    private String businessId;
    private Object shortPym;
    private Object phoneAll;
    private Object fax;
    private Object orgAddress;
    private Object email;
    private Object inlineCode;
    private Object longitude;
    private Object latitude;
    private Object stationNumber;
    private String sfcx;
    private Object checked;
    private int childCount;
    private String shortNamePa;
    private Object childrenList;
    private String parentName;
    private Object orgId;
    private Object clientStr;
    private String orgLevel;
    private String cjr;
    private Object gxr;
    private String cjsj;
    private String gxsj;
    private String sfsy;
    private String sjbb;
    private String fjCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        setGroupId(id);
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        setParentGroupId(parentId);
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        setGroupName(fullName);
        this.fullName = fullName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Object getZb() {
        return zb;
    }

    public void setZb(Object zb) {
        this.zb = zb;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Object getShortPym() {
        return shortPym;
    }

    public void setShortPym(Object shortPym) {
        this.shortPym = shortPym;
    }

    public Object getPhoneAll() {
        return phoneAll;
    }

    public void setPhoneAll(Object phoneAll) {
        this.phoneAll = phoneAll;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public Object getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(Object orgAddress) {
        this.orgAddress = orgAddress;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getInlineCode() {
        return inlineCode;
    }

    public void setInlineCode(Object inlineCode) {
        this.inlineCode = inlineCode;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Object stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getSfcx() {
        return sfcx;
    }

    public void setSfcx(String sfcx) {
        this.sfcx = sfcx;
    }

    public Object getChecked() {
        return checked;
    }

    public void setChecked(Object checked) {
        this.checked = checked;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getShortNamePa() {
        return shortNamePa;
    }

    public void setShortNamePa(String shortNamePa) {
        this.shortNamePa = shortNamePa;
    }

    public Object getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(Object childrenList) {
        this.childrenList = childrenList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Object getOrgId() {
        return orgId;
    }

    public void setOrgId(Object orgId) {
        this.orgId = orgId;
    }

    public Object getClientStr() {
        return clientStr;
    }

    public void setClientStr(Object clientStr) {
        this.clientStr = clientStr;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Object getGxr() {
        return gxr;
    }

    public void setGxr(Object gxr) {
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

    public String getFjCode() {
        return fjCode;
    }

    public void setFjCode(String fjCode) {
        this.fjCode = fjCode;
    }

}
