package com.zxit.tools;

/**
 * 时间:2006-03-24
 * 功能:提供网站的一些实用处理函数
 *
 * @version 1.0
 */

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UtilTools {
    // 日志
    private static Logger m_log = null;

    static {
        m_log = Logger.getLogger("com.eastwind.tools.UtilTools");
    }

    /**
     * 构造函数
     */
    public UtilTools() {
    }

    /**
     * 转换字符为中文
     */
    public static String toChString(String strConvert) {
        String strResult = "";
        if (strConvert != null) {
            try {
                strResult = new String(strConvert.getBytes("ISO8859_1"), "GBK");
            } catch (Exception e) {
                strResult = "";
                // m_log.debug("转换字符串" + strConvert + "出现异常:" + e.getMessage());
            }
        }
        return strResult;
    }

    /**
     * 转换字符为中文
     */

    public static String toGBK(String strConvert) {
        String strResult = "";
        if (strConvert != null) {
            try {
                strResult = new String(strConvert.getBytes("ISO8859_1"), "GBK");
            } catch (Exception e) {
                strResult = "";
                // m_log.debug("转换字符串" + strConvert + "出现异常:" + e.getMessage());
            }
        }
        return strResult;
    }

    /**
     * 字符替换
     *
     * @param rStr 被替换的字符串
     * @param rFix 被替代的字符串
     * @param rRep 替代的字符串
     * @return String 将字符串rStr中的字符串rFix替换成rRep
     */
    public static String replaceAll(String rStr, String rFix, String rRep) {
        int idx1 = 0;
        int idx2 = rStr.indexOf(rFix, idx1);
        int len = rFix.length();
        StringBuffer sb = new StringBuffer();
        while (true) {
            if (idx2 == -1) {
                sb.append(rStr.substring(idx1, rStr.length()));
                break;
            }
            sb.append(rStr.substring(idx1, idx2));
            sb.append(rRep);
            idx1 = idx2 + len;
            idx2 = rStr.indexOf(rFix, idx1);
        }
        String gRtnStr = sb.toString();
        return gRtnStr;
    }

    /**
     * 将字符串转换为整数
     *
     * @param strSource 欲转换的字符串
     * @param nDefault  出现异常时的默认返回值
     */
    public static int toInt(String strSource, int nDefault) {
        int nReturn = nDefault;
        try {
            if (strSource != null && !strSource.equals("")) {
                nReturn = Integer.parseInt(strSource);
            }
        } catch (Exception e) {
            m_log.debug("将字符串转换为整数时出现异常:" + e.getMessage());
            nReturn = nDefault;
        }
        return nReturn;
    }

    /**
     * 返回随机数
     *
     * @param strBaseStr 随即选择的字符串根数据
     * @param nLen       需要返回的随即字符串长度
     */
    public static String getRandStr(String strBaseStr, int nLen) {
        if (strBaseStr == null) {
            strBaseStr = "abcdefghijklmnopqrstuvwxyz1234567890";
        }
        if (strBaseStr.equals("")) {
            strBaseStr = "abcdefghijklmnopqrstuvwxyz1234567890";
        }
        String strResultStr = "";
        Random random = new Random();
        int nstrLen = strBaseStr.length() - 1;
        int n = 0;
        for (; n < nLen; n++) {
            strResultStr += strBaseStr.charAt(random.nextInt(nstrLen));
        }
        return strResultStr;
    }

    /**
     * 返回当前时间
     *
     * @return String
     */
    public static String getCurTime(String strFormat) {
        String strTime = "";
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat, Locale.US);
        Date currentTime = new Date();
        strTime = formatter.format(currentTime);
        return strTime;
    }

    /**
     * 返回request参数
     *
     * @param strSource String
     * @return String
     */
    public static String getStr(String strSource) {
        String strResult = "";
        if (strSource != null) {
            try {
                strResult = new String(strSource.getBytes("ISO8859_1"), "GBK"); // 2006-09-06
                // 因为web.xml加了过虑器导致转换后变成乱码，所以删除
                strResult.replaceAll("'", "''");
                // strResult = strResult.replaceAll(" ","");
            } catch (Exception e) {
                strResult = "";
                m_log.debug("转换字符串" + strSource + "出现异常:" + e.getMessage());
            }
        }
        return strResult;
    }

    /**
     * 检查字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean IsEmpty(String str) {
        boolean bFlag = false;
        try {
            String strTemp = "";
            if (str == null)
                bFlag = true;
            else {
                strTemp = str.trim();
                if (strTemp.equals("") || strTemp.equals("null"))
                    bFlag = true;
            }
        } catch (Exception e) {
            bFlag = true;
            m_log.debug("检查字符串是否为空出现异常:" + e.getMessage());
        }
        return bFlag;
    }

    /**
     * 判断输入的字符串是否为数字格式
     *
     * @param str
     * @param nNumType 0:整数 1:小数 2:数字范围
     * @return
     */
    public static boolean IsNumber(String str, int nNumType) {
        boolean bFlag = true;
        if (nNumType == 0) {
            try {
                Integer.parseInt(str);
            } catch (Exception e) {
                bFlag = false;
                // m_log.debug("检查字符串是否为整数类型出现异常:"+e.getMessage());
            }
        }
        if (nNumType == 1) {
            try {
                Float.parseFloat(str);
            } catch (Exception e) {
                bFlag = false;
                // m_log.debug("检查字符串是否为小数类型出现异常:"+e.getMessage());
            }
        }
        if (nNumType == 2) {
            try {

            } catch (Exception e) {
                bFlag = false;
                // m_log.debug("检查字符串是否为数字范围出现异常:"+e.getMessage());
            }
        }
        return bFlag;
    }

    // 返回指定字符串的占位长度
    public static int getShowLength(String str) {
        if (str == null || str.equals(""))
            return 0;

        boolean Winnt_Chinese = ("中国".length() == 2);
        if (!Winnt_Chinese)
            return str.length();

        int nLong = 0;
        char ch;
        int nch;
        try {
            for (int i = 0; i < str.length(); i++) {
                ch = str.charAt(i);
                nch = (int) ch;
                if (nch < 0)
                    nch = nch + 65536;
                if (nch > 255)
                    nLong++;
            }
        } catch (Exception e) {
            m_log.debug("取字符串的占位长度出现异常:" + e.getMessage());
        }
        return nLong;
    }

    public static String getLimitStr(String str, int nLimit) {
        if (str == null || str.equals(""))
            return "";

        boolean Winnt_Chinese = ("中国".length() == 2);
        if (!Winnt_Chinese)
            return str;

        String strResult = "";
        int t = 0, i = 0;
        int nCh;
        try {
            while (t < nLimit) {
                nCh = (int) str.charAt(i);
                if (nCh < 0)
                    nCh += 65536;
                if (nCh > 255)
                    t += 2;
                else
                    t++;
                if (t < nLimit)
                    i++;
            }
            strResult = str.substring(0, t);
        } catch (Exception e) {
            m_log.debug("截取指定长度字符串出现异常:" + e.getMessage());
        }
        return strResult;
    }

    // 输出指定长度的字符串
    public static String toOutStr(String str, int nLong, String strDefault) {
        String strResult = "";
        try {
            if (str == null || str.equals(""))
                strResult = strDefault;
            else {
                if (getShowLength(str) > nLong) {
                    // 需要截取
                    strResult = getLimitStr(str, nLong);
                } else {// 不需要截取
                    strResult = str;
                }
            }
        } catch (Exception e) {
            m_log.debug("获得输出字符串出现异常:" + e.getMessage());
        }
        return strResult;
    }


    /**
     * 对字符串进行处理，如果给定字符串为空或null，则返回默认字符串
     *
     * @param str    要处理字符串
     * @param defStr 默认值
     * @return
     */
    public static String formatString(String str, String defStr) {
        if (str != null && !"".equals(str))
            return str;
        else
            return defStr;
    }


    /**
     * 格式化实型数，保留两位小数
     *
     * @param f
     * @return
     */
    public static String getDouble(double f) {
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal b = new BigDecimal(Double.toString(f));
        BigDecimal one = new BigDecimal("1");
        return df.format(b.divide(one, 2, BigDecimal.ROUND_HALF_UP)
                .doubleValue());
    }

    /**
     * 格式化实型数，保留7位小数
     *
     * @param f
     * @return
     */
    public static String getDouble7(double f) {
        DecimalFormat df = new DecimalFormat("0.0000000");
        return df.format(f);
    }

    /**
     * 对一个对象中字符串域为null值的进行替换
     *
     * @param obj  对象
     * @param show 替换值
     */
    public static Object killNull(Object obj, String show) {
        if (obj == "null") {
            return show;
        } else if ("null".equals(obj)) {
            return show;
        } else if (null == obj) {
            return show;
        } else
            return obj;
    }

    /**
     * 功能描述：防止字符串为空的形式，导致输出为null
     *
     * @param srcStr
     * @param defStr
     * @return
     */
    public static String KillNull(String srcStr, String defStr) {
        if (srcStr == "null") {
            return defStr;
        } else if ("null".equals(srcStr)) {
            return defStr;
        } else if (null == srcStr) {
            return defStr;
        } else if (srcStr.length() == 0) {
            return defStr;
        } else if (StringUtils.isEmpty(srcStr)) {
            return defStr;
        } else {
            return srcStr;
        }
    }

    /**
     * 过滤number的null
     *
     * @param i
     * @return
     */
    public static Integer killNullNumber(Integer i) {
        if (i == null) {
            return 0;
        } else {
            return i;
        }

    }

    /**
     * 获得两个数的百分比
     */
    public static String getPercent(int x, int y, String fmtStr) {
        if (y == 0) {
            return " ";
        }
        String baifenbi = "";// 接受百分比的值
        double baix = x * 1.0;
        double baiy = y * 1.0;
        double fen = baix / baiy;
        DecimalFormat df1 = new DecimalFormat(fmtStr); // ##.00%
        // 百分比格式，后面不足2位的用0补齐
        baifenbi = df1.format(fen);
//		if (".00%".endsWith(baifenbi)) {
//			baifenbi = "";
//		}
        return baifenbi.toString();
    }

    /**
     * 手机号码验证
     */
    public static boolean checkMobPhone(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(phone);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return
     */
    public static boolean checkTelPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 邮箱验证
     */
    public static boolean checkMail(String str) {
        boolean b = false;
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
        Matcher m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * IP验证
     */
    public static boolean checkIP(String str) {
        boolean b = false;
        Pattern p = Pattern
                .compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        Matcher m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证闰月
     */
    public static boolean checkAddMonth(String str) {
        boolean b = false;
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher matcher = pattern.matcher(str);
        b = matcher.matches();
        return b;
    }

    /**
     * 阿拉伯数字转大写
     *
     * @param d
     * @return
     */
    public static String fmtNumToTxt(int d) {
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String s = String.valueOf(d);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
        }
        return sb.toString();
    }

    /**
     * Json转实体类
     *
     * @param jsonObject
     * @param cla
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToObj(JSONObject jsonObject, Class<T> cla) {

        if (jsonObject == null)
            return null;
        T t;
        try {
            t = (T) JSONObject.toBean(jsonObject, cla);
            return t;

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json集合转实体集合
     *
     * @param jsonArray
     * @param cla
     * @return
     */
    public static <T> List<T> convertToList(JSONArray jsonArray, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        if (jsonArray == null)
            return list;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t = UtilTools.convertToObj(jsonObject, cla);
                list.add(t);
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将字符串数组组装成字符串
     *
     * @param strings 源数组
     * @param split   分隔符
     * @return
     */
    public static String join(String[] strings, String split) {
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                result += strings[i];
            } else {
                result += strings[i] + split;
            }
        }
        return result;
    }

    /**
     * 给不够长的添加个0
     *
     * @param param
     * @return
     */
    public static String formatRdm(Object param, int length) {
        String returnStr = "";
        try {
            returnStr = String.valueOf(param);
            while (returnStr.length() < length) {
                returnStr = "0" + returnStr;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 汉字转拼音
     */
    public static String converterToFirstSpell(String chines) {
        chines = chines.replace("（", "").replace("）", "");
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > '')
                try {
                    pinyinName = pinyinName + net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    return "";
                }
            else {
                pinyinName = pinyinName + nameChar[i];
            }
        }
        return pinyinName;
    }

    public static String converterToSpell(String chines) {
        chines = chines.replace("（", "").replace("）", "");
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > '')
                try {
                    pinyinName = pinyinName + net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            else {
                pinyinName = pinyinName + nameChar[i];
            }
        }
        return pinyinName;
    }


}