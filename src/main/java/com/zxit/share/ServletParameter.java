
package com.zxit.share;

import com.zxit.tools.ParameterTools;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

//-------------------------------------------------------------------

/**
 * <PRE>
 * 在 Servlet/JSP 技术中大量使用表单和超级链接等等技术提交参数，这些参数有
 * 很多种类型，比如：整数、字符串、布尔值和字符串数组。
 * <p>
 * 在 Servlet/JSP 网页之间传递的参数需要从 HttpServletRequest 隐含变量 request
 * 中提取出来，大量的 request.getParamter( "Name" ) 使得程序晦涩难懂，并且传递
 * 的参数可能为空 (null)。
 * <p>
 * 本包装类的目的就是简化从 request 隐含变量中提取参数的方法。
 * 目前实现的方法可以提取以下类型的参数：
 * boolean
 * int
 * String
 * String []
 * </PRE>
 * <p>
 * <p>
 * Title: 隐含变量 request.getParameter(...) 的包装类
 * </p>
 * <p>
 * Description: 使Servlet的编写与JSP的同样简便，并且更好维护。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: 开祥科技, www.kxkj.com.cn
 * </p>
 *
 * @author 吴建华
 * @version 1.0
 */
public class ServletParameter {
    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值。
     * @return 一个字符串数组。
     */
    public static String[] getParameter(HttpServletRequest httpRequest,
                                        String parameterName, String[] defaultValue) {
        if (httpRequest.getParameterValues(parameterName) != null)
            return httpRequest.getParameterValues(parameterName);

        return defaultValue;
    }

    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值；
     * @return 一个布尔变量。
     */
    public static boolean getParameter(HttpServletRequest httpRequest,
                                       String parameterName, boolean defaultValue) {
        if (httpRequest.getParameter(parameterName) != null)
            return Boolean.valueOf(httpRequest.getParameter(parameterName))
                    .booleanValue();

        return defaultValue;
    }

    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值；
     * @return 一个整数，参数的类型是整数。
     */
    public static int getParameter(HttpServletRequest httpRequest,
                                   String parameterName, int defaultValue) {
        if (httpRequest.getParameter(parameterName) != null
                && !httpRequest.getParameter(parameterName).equals(""))
            return Integer.parseInt(httpRequest.getParameter(parameterName));

        return defaultValue;
    }

    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值；
     * @return 一个整数，参数的类型是整数。
     */
    public static double getParameter(HttpServletRequest httpRequest,
                                      String parameterName, double defaultValue) {
        if (httpRequest.getParameter(parameterName) != null)
            return Double.parseDouble(httpRequest.getParameter(parameterName));

        return defaultValue;
    }

    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值；
     * @return 一个字符串，
     */
    public static String getParameter(HttpServletRequest httpRequest,
                                      String parameterName, String defaultValue) {

        if (httpRequest.getParameter(parameterName) != null
                && !httpRequest.getParameter(parameterName).equals(""))
            try {
                defaultValue = httpRequest.getParameter(parameterName);
                // 处理get方式传送数据乱码，如果你的程序编码集是GBK
//				String method = httpRequest.getMethod();
//				if (method.toUpperCase().equals("GET")) {
//					defaultValue = UtilTools.toGBK(defaultValue);
//				}
            } catch (Exception e) {
                System.err.println(e.toString());
                return defaultValue;
            }
        return defaultValue;
    }

    // ...............................................................

    /**
     * @param httpRequest   通常使用隐含的 Servlet 变量 request；
     * @param parameterName 通过表单等提交的参数名称；
     * @param defaultValue  参数的缺省值；
     * @return 一个字符串，
     */
    /*
     * public static java.util.Date getParameter( HttpServletRequest
	 * httpRequest, String parameterName, java.util.Date defaultValue ) { if (
	 * httpRequest.getParameter( parameterName ) != null ) { // 日期是 yyyy-mm-dd
	 * 格式。 // String myDate = httpRequest.getParameter( parameterName );
	 * 
	 * //DateFormat df = new
	 * 
	 * return java.util.Date( myDate ); }
	 * 
	 * return defaultValue; }
	 */

    // -------------------------------------------------------------------------
    //
    @SuppressWarnings("rawtypes")
    public static void printParameterValues(HttpServletRequest req,
                                            String fileName) {
        String line = ParameterTools.fill(80, '*');
        int len = 50;
        String spc = null;

        String name = null;
        String[] value = null;

        System.out.println();
        System.out.println();
        System.out.println(line);
        //
        System.out.print(new java.util.Date());
        System.out.println("   " + fileName);
        System.out.println();

        Enumeration Enumx = req.getParameterNames();

        while (Enumx.hasMoreElements()) {
            name = (String) Enumx.nextElement();

            // 定义对齐的空格。
            len = name.length() + 8;
            spc = ParameterTools.space(30 - len);

            value = req.getParameterValues(name);

            if (value != null)
                for (int i = 0; i < value.length; i++) {
                    System.out.print("name  = " + name + spc + "\t\t");
                    System.out.print("value = " + value[i]); // web.xml filter
                    // is GBK

                    System.out.println();
                }
            else {
                System.out.print("name  = " + name + spc + "\t\t");
                System.out.print("value =  ");
                System.out.println();
            }
        }

        System.out.println(line);

        return;
    }

    // ...............................................................
    //
}

// -------------------------------------------------------------------
// -------------------------------------------------------------------
