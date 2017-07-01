package com.zxit.tools;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener
        implements HttpSessionListener {
    private static int count = 0;

    public void sessionCreated(HttpSessionEvent se) {
        count += 1;
        System.out.println("在线人数：" + count);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        count -= 1;
        System.out.println("在线人数：" + count);
    }

    public static int getCount() {
        return count;
    }
}