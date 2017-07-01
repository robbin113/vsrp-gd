package com.zxit.share;


import com.zxit.dao.ABaseDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;

/**
 * 功能描述：生成数据库表中的UUID 作者：JoryLee 日期：
 */
@Component
public class CreateDbUtil extends BaseTagConfig {

    @Resource
    private ABaseDao aBaseDao;

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    /**
     * 根据序列生成key
     */
    public String CreateSqPk(String squenceName) {
        String pkey = aBaseDao.findBySQL("select " + squenceName + ".nextval  from dual").uniqueResult().toString();
        return pkey;
    }


    /**
     * 根据表和字段获取中文注释
     *
     * @param tablename
     * @param colum
     * @return
     */
    public String forCn(String tablename, String colum) {
        String str = "";
        String sql = "";
        try {
            sql = "select comments from ( select replace(column_name,'_','') as colname ,comments,replace(Table_Name,'_','') as tablename from user_col_comments )"
                    + "where tablename = '"
                    + tablename.toUpperCase()
                    + "'  and colname = '" + colum.toUpperCase() + "' ";
            str = aBaseDao.findBySQL(sql).uniqueResult().toString();
        } catch (Exception e) {
            System.out.println(sql);
            return "<font color='red' title='" + tablename + ":" + colum + "'>这可能是个错误！</red>";
        }
        return str;
    }


}

