package com.yx.model;

import java.util.Date;

/**
 * @description:
 * @author: yyb
 * @create: 2017/11/9.
 */
public class Enterprise {
    /**
     * 主键Id
     */
    private Integer id;
    /**
     * 证号
     */
    private String fCardNumber;
    /**
     * 企业名称
     */
    private String fName;
    /**
     *注册地址
     */
    private String fRegisterAddr;
    /**
     *仓库地址
     */
    private String fStorehouseAddr;
    /**
     * 法定代表人
     */
    private String fRepresentative;
    /**
     * 企业负责人
     */
    private String fEnterpriseMan;
    /**
     * 质量负责人
     */
    private String fQualityMan;
    /**
     * 经营方式
     */
    private String fManageMode;
    /**
     * 经营范围
     */
    private String fManageRange;
    /**
     * 发证日期
     */
    private Date fCertificateDate;
    /**
     * 有效期至
     */
    private Date fExpiryDate;
    /**
     * 发证部门
     */
    private String fCertificateDepart;
    /**
     * 备注
     */
    private String fRemarks;
    /**
     * url地址
     */
    private String fUrl;
    /**
     * 来源
     */
    private String fSource;
    /**
     * 抓取时间
     */
    private Date fDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfCardNumber() {
        return fCardNumber;
    }

    public void setfCardNumber(String fCardNumber) {
        this.fCardNumber = fCardNumber;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfRegisterAddr() {
        return fRegisterAddr;
    }

    public void setfRegisterAddr(String fRegisterAddr) {
        this.fRegisterAddr = fRegisterAddr;
    }

    public String getfStorehouseAddr() {
        return fStorehouseAddr;
    }

    public void setfStorehouseAddr(String fStorehouseAddr) {
        this.fStorehouseAddr = fStorehouseAddr;
    }

    public String getfRepresentative() {
        return fRepresentative;
    }

    public void setfRepresentative(String fRepresentative) {
        this.fRepresentative = fRepresentative;
    }

    public String getfEnterpriseMan() {
        return fEnterpriseMan;
    }

    public void setfEnterpriseMan(String fEnterpriseMan) {
        this.fEnterpriseMan = fEnterpriseMan;
    }

    public String getfQualityMan() {
        return fQualityMan;
    }

    public void setfQualityMan(String fQualityMan) {
        this.fQualityMan = fQualityMan;
    }

    public String getfManageMode() {
        return fManageMode;
    }

    public void setfManageMode(String fManageMode) {
        this.fManageMode = fManageMode;
    }

    public String getfManageRange() {
        return fManageRange;
    }

    public void setfManageRange(String fManageRange) {
        this.fManageRange = fManageRange;
    }

    public Date getfCertificateDate() {
        return fCertificateDate;
    }

    public void setfCertificateDate(Date fCertificateDate) {
        this.fCertificateDate = fCertificateDate;
    }

    public Date getfExpiryDate() {
        return fExpiryDate;
    }

    public void setfExpiryDate(Date fExpiryDate) {
        this.fExpiryDate = fExpiryDate;
    }

    public String getfCertificateDepart() {
        return fCertificateDepart;
    }

    public void setfCertificateDepart(String fCertificateDepart) {
        this.fCertificateDepart = fCertificateDepart;
    }

    public String getfRemarks() {
        return fRemarks;
    }

    public void setfRemarks(String fRemarks) {
        this.fRemarks = fRemarks;
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

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }
}
