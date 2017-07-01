package com.zxit.share;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class Pager {
    /**
     * 作者:阿阁 时间:2006-03-20 功能:为数据查询提供分页功能 版本说明:1.0 修改：原来只能处理http get方式
     * 更新：2013年，更新了适合hibernate的分页处理
     */
    private Logger m_log = Logger.getLogger("com.zxit.share.Pager");
    private String strNavigation = "";
    private int nTotalNum; // 全部记录数
    private int nTotalPage; // 全部页数
    private int nNumPer; // 每页显示条数
    private int nPrePos; // 上页起始数
    private int nEndPos; // 结束页起始数
    private int nNextPos; // 下页起始数
    private int nCurStart; // 当前起始记录数
    private int nCurPage; // 当前页码
    @SuppressWarnings("unused")
    private int nNextPage; // 下一页页码
    private int nTempCursorPos; // 指针的临时位置记录
    private int nDefaultPerNum;// 默认的每页显示数
    private int nCurEndPos;// 当前页的结束记录数
    private String strNavStyle = "2";// 1 2 为http方式返回的导航条 3 4 5 为js导航条
    private String strHref = ""; // 连接时的默认地址字符串

    public Pager() {
        // 构造函数
        nTotalNum = 0;
        nTotalPage = 0;
        nNumPer = 0;
        nPrePos = 0;
        nEndPos = 0;
        nNextPos = 0;
        nCurStart = 0;
        nCurPage = 0;
        nTempCursorPos = 0;
        nDefaultPerNum = 15;
        nCurEndPos = 0;
    }

    /**
     * 根据传入的记录数初始化导航条的参数 sTotalNum 用于http提交方式
     *
     * @return int
     */
    @SuppressWarnings("rawtypes")
    public int Init(int nTotalNum, HttpServletRequest request) {
        // 取request中的参数
        this.nTotalNum = nTotalNum;
        String sCurPos = request.getParameter("EgecStart");
        String sPerNum = request.getParameter("EgecPerNum");
        @SuppressWarnings("unused")
        String RetoPage = request.getParameter("RetoPage");
        nNumPer = GetInt(sPerNum, nDefaultPerNum);
        nCurStart = GetInt(sCurPos, 0);
        /***
         * 获得request传递的参数,同时为了保证get方式提交的中文显示正确 需要在tomcat
         * 配置文件增加URIEncoding="GBK"，保证post的数据 获得正确，增加一个过滤器
         */
        //
        Enumeration Enumx = request.getParameterNames();
        String param = "1=1";
        while (Enumx.hasMoreElements()) {
            String name = (String) Enumx.nextElement();
            String value = ServletParameter.getParameter(request, name, "");

            // if (StringUtils.isNotEmpty(value)) {
            param += "&" + name + "=" + value;
            // }
        }
        // 去除冗余数据
        param = param.replaceAll("1=1&", "");

        FromatHref(request.getRequestURI(), param, sCurPos, sPerNum);
        //
        // FromatHref(request.getRequestURI(),
        // Tool.toGBK(request.getQueryString()), sCurPos,
        // sPerNum);

        return Init(nTotalNum, nCurStart);
    }

    /**
     * 根据传入的记录数初始化导航条的参数 sTotalNum 用于ajax调用使用
     *
     * @param nTotalNum
     * @param nCurStart
     * @return
     */

    public int Init(int nTotalNum, int aCurStart) {
        // 对参数进行初始化
        int iFlag = 1;
        // 取request中的参数
        this.nTotalNum = nTotalNum;
        nNumPer = this.nDefaultPerNum;
        this.nCurStart = aCurStart;
        // 超出范围
        if (nCurStart > nTotalNum) {
            nCurStart = 0;
        }
        // 计算总页数
        nTotalPage = nTotalNum / nNumPer;
        if (nTotalNum % nNumPer != 0) {
            nTotalPage++;
            // 计算当前页数
        }
        // 当前页页码
        nCurPage = nCurStart / nNumPer + 1;
        // 下一页页码
        nNextPage = nCurPage + 1 >= nTotalPage ? nTotalPage : (nCurPage + 1);
        // 计算上页起始数
        if (nCurStart < nNumPer) {
            nPrePos = 0;
        } else {
            nPrePos = nCurStart - nNumPer;
            // 计算上一页起始数
        }
        if ((nCurStart + nNumPer) >= nTotalNum) {
            nNextPos = nCurStart;
        } else {
            nNextPos = nCurStart + nNumPer;
            // 计算最后一页的起始数
        }
        nEndPos = (nTotalPage - 1) * nNumPer;
        // 复原临时指针的位置
        nTempCursorPos = 0;
        // 计算当前页最后一条的记录数
        int nTemp = this.nCurStart + this.nNumPer;
        if (nTemp > nTotalNum)
            nTemp = nTotalNum;

        nCurEndPos = nTemp;

        if (nTotalPage == 0)
            nTotalPage = 1;

        return iFlag;
    }

    /**
     * 确定当前的rs是否还需要继续往后滚动
     *
     * @return boolean
     */
    public boolean next() {
        if (this.nTotalNum < this.nNumPer)
            return false;
        boolean bFlag = true;
        nTempCursorPos++;
        if (nTempCursorPos > nCurStart) {
            bFlag = false;
        }
        return bFlag;
    }

    /**
     * 返回导航字符串
     *
     * @return String
     * @Modified HaoX 原来首页的连接地址生成有误，问号后边直接为“&”字符，已去除
     */
    public String getStr() {
        if (nTotalNum <= 0) {
            strNavigation = "无显示信息";
            return strNavigation;
        }
        // modify by wyz
        // strHref = strHref;
        //
        strNavigation = "共有<a href=\"#\" class=\"page\">" + nTotalNum
                + "</a>条 当前第<a href=\"#\" class=\"page\">" + nCurPage + "/"
                + nTotalPage + "</a>页 本页显示从<a href=\"#\" class=\"page\">"
                + (nCurStart + 1) + "</a>到<a href=\"#\" class=\"page\">"
                + nCurEndPos + "</a>条。";
        if (nCurStart != 0) {
            strNavigation += " <a href=\"" + strHref + "0\">首页</a>&nbsp;";
        } else {
            strNavigation += " ";
        }
        if (nPrePos < nCurStart) {
            strNavigation += " <a class=\"pre\" href=\"" + strHref + nPrePos
                    + "\"> 上一页</a>&nbsp;";
        } else {
            strNavigation += " ";
        }
        if (nNextPos > nCurStart) {
            strNavigation += " <a class=\"next\" href=\"" + strHref + nNextPos
                    + "\"> 下一页 </a>&nbsp;";
        } else {
            strNavigation += " ";
        }
        if (nEndPos > nCurStart) {
            strNavigation += " <a href=\"" + strHref + nEndPos
                    + "\">尾页</a>&nbsp;";
        } else {
            strNavigation += " ";
        }
        if (nTotalPage > 1) {// 显示当前页的前后最多10个页面序列
            if ("1".equals(strNavStyle)) {
                strNavigation += "跳至第 ";
                String strTemp = "";
                for (int i = 1; i < 5; i++) {
                    if ((nCurPage - i) < 1)
                        break;
                    strTemp = "<a href=\"" + strHref + (nCurPage - i - 1)
                            * nNumPer + "\"><font color=\"#0000FF\">"
                            + (nCurPage - i) + "</font></a> " + strTemp;
                }
                strTemp += "<font color=\"#FF0000\">" + nCurPage + "</font> ";
                for (int i = 1; i < 5; i++) {
                    if ((nCurPage + i) > nTotalPage)
                        break;
                    strTemp += "<a href=\"" + strHref + (nCurPage + i - 1)
                            * nNumPer + "\"><font color=\"#0000FF\">"
                            + (nCurPage + i) + "</font></a> ";
                }
                strNavigation += strTemp + "页";
            } else if ("2".equals(strNavStyle)) {
                strNavigation += "跳至第 <input type=\"text\" value=\""
                        + nCurPage
                        + "\" name=\"RetoPage\" id=\"RetoPage\" size=\"3\" style=\"width:50px;\"/> 页 <a href=\""
                        + strHref
                        + "\" onclick=\"javascript:return changePage(this);\">GO</a>";

                String strJS = "<script language=\"javascript\">\r";// 使用到的JS代码
                strJS += "\r";
                strJS += "function isNum(strValue){\rvar lsStandValue=\"0123456789\";\rif(strValue==null) return false;\rif(strValue==\"\") return false;\rvar bNum = true;\rfor(var i=0;i<strValue.length;i++){\rif(lsStandValue.indexOf(strValue.charAt(i))<0){\rbNum=false;\rbreak;\r}\r}\r return bNum;\r}\r";
                strJS += "function changePage(loObj){//更换页码\r";
                strJS += "    var loPage = document.getElementById(\"RetoPage\");\r";
                strJS += "    var lsPage = loPage.value; \r";
                strJS += "    if(!isNum(lsPage)){alert(\"请检查跳转页面的输入是否正确！\");loPage.focus();return false;}\r";
                strJS += "    var nPage = parseInt(loPage.value);\r";
                strJS += "    if(nPage<1 || nPage>" + nTotalPage
                        + "){alert(\"请确保跳转页面的输入在允许范围(1-" + nTotalPage
                        + ")之内！\");loPage.focus();return false;}\r";
                strJS += "    loObj.href = loObj.href + (nPage-1)*" + nNumPer
                        + ";\r";
                strJS += "    return true;\r";
                strJS += "}\r";
                strJS += "\r";
                strJS += "</script>\r";
                strNavigation = strJS + strNavigation;
            }
        }
        return strNavigation;
        //+ "（信息量：前1000条）";
    }

    /**
     * 设置每页的显示数
     */
    public void SetPerNum(int nlPerNum) {
        this.nDefaultPerNum = nlPerNum;
    }

    /**
     * 对传入的页面地址进行处理，产生类似于"start="的结构字符串
     */
    public void FromatHref(String strUrl, String strPara, String sCurPos,
                           String sPerNum) {

        if (strPara == null || strPara.equals("")) {
            // 没有参数
            strHref = strUrl + "?EgecStart=";
        } else {
            // 有参数
            if (strPara.indexOf("EgecStart") >= 0) {
                strPara = strPara.replaceAll("&EgecStart=" + sCurPos, "");
                strPara = strPara.replaceAll("EgecStart=" + sCurPos, "");
            }
            if (strPara.indexOf("EgecPerNum") >= 0) {
                strPara = strPara.replaceAll("&EgecPerNum=" + sPerNum, "");
                strPara = strPara.replaceAll("EgecPerNum=" + sPerNum, "");
            }
            if (strPara.equals("")) {
                if (nNumPer != 20) {
                    strHref = strUrl + "?EgecPerNum=" + nNumPer + "&EgecStart=";
                } else {
                    strHref = strUrl + "?EgecStart=";
                }
            } else {
                if (strPara.startsWith("&")) {
                    strPara = strPara.substring(1, strPara.length());
                }
                if (nNumPer != 20) {
                    strHref = strUrl + "?" + strPara + "&EgecPerNum=" + nNumPer
                            + "&EgecStart=";
                } else {
                    strHref = strUrl + "?" + strPara + "&EgecStart=";
                }
            }
        }
    }

    /**
     * 提供安全的字符串转换为数字，如果有异常就返回0
     */
    public int GetInt(String strTemp, int nDefault) {
        int nReturn = nDefault;
        try {
            if (strTemp != null && !strTemp.equals("")) {
                nReturn = Integer.parseInt(strTemp, 10);
            }
        } catch (Exception e) {
            nReturn = nDefault;
            m_log.debug("将字符串转换为数字时出现异常:" + e.getMessage());
        }
        return nReturn;
    }

    /**
     * 获得起始记录位置
     */
    public int getStartPos() {
        return this.nCurStart;
    }

    /**
     * 获得当前页的结束记录位置
     */
    public int getEndPos() {
        return nCurEndPos;
    }

    /**
     * 获取每页的显示
     */
    public int getPerPageNum() {
        return this.nDefaultPerNum;
    }

    /**
     * 获得此次需要申请的数组长度
     *
     * @author Administrator
     */
    public int getMallocNum() {
        return nCurEndPos - nCurStart;
    }

    /**
     * @return 返回 strNavStyle。
     */
    public String getStrNavStyle() {
        return strNavStyle;
    }

    /**
     * @param strNavStyle 要设置的 strNavStyle。
     */
    public void setStrNavStyle(String strNavStyle) {
        this.strNavStyle = strNavStyle;
    }

    /**
     * 获取总页数
     */
    public int getTotalPage() {
        return this.nTotalPage;
    }

    /**
     * 获取当前页数
     */
    public int getCurPage() {
        return this.nCurPage;
    }
}
