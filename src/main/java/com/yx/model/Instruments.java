package com.yx.model;

import java.util.Date;

/**
 * @description:
 * @author: yyb
 * @create: 2017/10/26.
 */
public class Instruments {
    private Integer Id;//主键Id
    private Integer fProductId;//产品id
    private String fCompanyId;//公司id
    private String fName;//产品名称
    private String fCode;//批准文号
    private String fTag;//产品标签
    private String fArea;//招商区域
    private String fFeature;//产品卖点
    private String fCategory;//器械类别
    private String fCategory2;//耗材类别
    private String fOtherCategory;//其它分类
    private String fFunction;//功能
    private String fUseForFamily;//家用与否
    private String fDepartment;//科室
    private String fCatelog;//标准目录
    private String fCompany;//公司名称
    private String fCompanyAddr;//公司地址
    private String fContact;//联系人
    private String fTel;//公司电话
    private String fPhone;//手机号码
    private String fQQ;//QQ
    private String fRemark;//备注
    private String fUpdateDate;//更新时间

    private String fUrl;//url地址
    private String fSource;//来源
    private String fDate;//抓取时间

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getfProductId() {
        return fProductId;
    }

    public void setfProductId(Integer fProductId) {
        this.fProductId = fProductId;
    }

    public String getfCompanyId() {
        return fCompanyId;
    }

    public void setfCompanyId(String fCompanyId) {
        this.fCompanyId = fCompanyId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getfTag() {
        return fTag;
    }

    public void setfTag(String fTag) {
        this.fTag = fTag;
    }

    public String getfArea() {
        return fArea;
    }

    public void setfArea(String fArea) {
        this.fArea = fArea;
    }

    public String getfFeature() {
        return fFeature;
    }

    public void setfFeature(String fFeature) {
        this.fFeature = fFeature;
    }

    public String getfCategory() {
        return fCategory;
    }

    public void setfCategory(String fCategory) {
        this.fCategory = fCategory;
    }

    public String getfCategory2() {
        return fCategory2;
    }

    public void setfCategory2(String fCategory2) {
        this.fCategory2 = fCategory2;
    }

    public String getfOtherCategory() {
        return fOtherCategory;
    }

    public void setfOtherCategory(String fOtherCategory) {
        this.fOtherCategory = fOtherCategory;
    }

    public String getfFunction() {
        return fFunction;
    }

    public void setfFunction(String fFunction) {
        this.fFunction = fFunction;
    }

    public String getfUseForFamily() {
        return fUseForFamily;
    }

    public void setfUseForFamily(String fUseForFamily) {
        this.fUseForFamily = fUseForFamily;
    }

    public String getfDepartment() {
        return fDepartment;
    }

    public void setfDepartment(String fDepartment) {
        this.fDepartment = fDepartment;
    }

    public String getfCatelog() {
        return fCatelog;
    }

    public void setfCatelog(String fCatelog) {
        this.fCatelog = fCatelog;
    }

    public String getfCompany() {
        return fCompany;
    }

    public void setfCompany(String fCompany) {
        this.fCompany = fCompany;
    }

    public String getfCompanyAddr() {
        return fCompanyAddr;
    }

    public void setfCompanyAddr(String fCompanyAddr) {
        this.fCompanyAddr = fCompanyAddr;
    }

    public String getfContact() {
        return fContact;
    }

    public void setfContact(String fContact) {
        this.fContact = fContact;
    }

    public String getfTel() {
        return fTel;
    }

    public void setfTel(String fTel) {
        this.fTel = fTel;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public String getfQQ() {
        return fQQ;
    }

    public void setfQQ(String fQQ) {
        this.fQQ = fQQ;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public String getfUpdateDate() {
        return fUpdateDate;
    }

    public void setfUpdateDate(String fUpdateDate) {
        this.fUpdateDate = fUpdateDate;
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl;
    }

    public String getfSource() {
        return fSource;
    }

    public void setfSource(String fSource) {
        this.fSource = fSource;
    }

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    @Override
    public String toString() {
        return "Instruments{" +
                "Id=" + Id +
                ", fProductId=" + fProductId +
                ", fCompanyId=" + fCompanyId +
                ", fName='" + fName + '\'' +
                ", fCode='" + fCode + '\'' +
                ", fTag='" + fTag + '\'' +
                ", fArea=" + fArea +
                ", fFeature='" + fFeature + '\'' +
                ", fCategory='" + fCategory + '\'' +
                ", fCategory2='" + fCategory2 + '\'' +
                ", fOtherCategory='" + fOtherCategory + '\'' +
                ", fFunction='" + fFunction + '\'' +
                ", fUseForFamily='" + fUseForFamily + '\'' +
                ", fDepartment='" + fDepartment + '\'' +
                ", fCatelog='" + fCatelog + '\'' +
                ", fCompany='" + fCompany + '\'' +
                ", fCompanyAddr='" + fCompanyAddr + '\'' +
                ", fContact='" + fContact + '\'' +
                ", fTel='" + fTel + '\'' +
                ", fPhone='" + fPhone + '\'' +
                ", fQQ='" + fQQ + '\'' +
                ", fRemark='" + fRemark + '\'' +
                ", fUpdateDate='" + fUpdateDate + '\'' +
                ", fUrl='" + fUrl + '\'' +
                ", fSource='" + fSource + '\'' +
                ", fDate=" + fDate +
                '}';
    }
}
