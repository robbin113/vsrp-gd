package com.zxit.share;

/**
 * session监听器
 */

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static int count = 0;

    public void sessionCreated(HttpSessionEvent se) {
        count++;
        System.out.println("SessionListener开始访问" + count);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
        System.out.println("SessionListener结束访问" + count);
    }

    /**
     */
    public static int getCount() {

        return (count);
    }
}
