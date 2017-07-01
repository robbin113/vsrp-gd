package com.zxit.tools;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {
    public void init() {
        try {
            System.out.println("log4j init ...");
            String file = getInitParameter("log4j-config");
            if (file == null) file = "log4j.properties";
            InputStream is = getServletContext().getResourceAsStream(file);
            Properties props = new Properties();
            if (file != null) {
                props.load(is);
                PropertyConfigurator.configure(props);
                System.out.println("log4j init finished!");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }

    public static void main(String args[]) {
        Log4jInit Log4jInit = new Log4jInit();
        Log4jInit.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}