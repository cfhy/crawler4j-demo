package com.yx.dao;

import com.yx.model.Instruments;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: yyb
 * @create: 2017/10/26.
 */
public class EnterpriseDao {
    public boolean insert(Instruments instruments) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO rtHCProduct([fProductId],[fCompanyId],[fName],[fCode],[fTag],[fArea],");
        sb.append("[fFeature],[fCategory],[fCategory2],[fOtherCategory],[fFunction],[fUseForFamily],[fDepartment]");
        sb.append(",[fCatelog],[fCompany],[fCompanyAddr],[fContact],[fTel],[fPhone],[fQQ],[fRemark],[fUpdateDate]");
        sb.append(",[fUrl],[fSource],[fDate])");
        sb.append("values(");
        sb.append(instruments.getfProductId() + ",'" + instruments.getfCompanyId() + "','" + instruments.getfName());
        sb.append("','" + instruments.getfCode() + "','" + instruments.getfTag() + "','" + instruments.getfArea());
        sb.append("','" + instruments.getfFeature() + "','" + instruments.getfCategory() + "','" + instruments.getfCategory2());
        sb.append("','" + instruments.getfOtherCategory() + "','" + instruments.getfFunction() + "','" + instruments.getfUseForFamily());
        sb.append("','" + instruments.getfDepartment() + "','" + instruments.getfCatelog() + "','" + instruments.getfCompany());
        sb.append("','" + instruments.getfCompanyAddr() + "','" + instruments.getfContact() + "','" + instruments.getfTel());
        sb.append("','" + instruments.getfPhone() + "','" + instruments.getfQQ() + "','" + instruments.getfRemark());
        sb.append("','" + instruments.getfUpdateDate() + "','" + instruments.getfUrl() + "','" + instruments.getfSource() + "','" + instruments.getfDate());
        sb.append("')");
        return SqlHelper.executeUpdate(sb.toString());
    }

    public boolean isExisted(String url) throws SQLException {
        String sql = "select count(*) from rtHCProduct where furl='" + url + "'";
        ResultSet rs = SqlHelper.executeQuery(sql);
        int recordCount=0;
        while (rs.next()) {
            recordCount = rs.getInt(1);
        }
        rs.close();
        return recordCount>0;
    }
}
