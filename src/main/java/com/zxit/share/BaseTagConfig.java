package com.zxit.share;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * tag服务基础类
 * 不实现具体的tag服务
 * 交给实现类
 * 当前类只实现spring的上下文解析
 *
 * @author nanxiaofeng
 */
public class BaseTagConfig extends TagSupport implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**************** ApplicationContextAware ********************/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        BaseTagConfig.applicationContext = applicationContext; // NOSONAR
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

    /**
     * 清除applicationContext静态变量.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }


}
