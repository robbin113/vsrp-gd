package com.zxit.interceptor;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Hibernate
 * 监听器注册
 *
 * @author Administrator
 */
//@Component
public class HibernateIntegrator {

    @Autowired
    private HibernateEventListener cacheEventListener;
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);
        //registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(cacheEventListener);  
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(cacheEventListener);
        //registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(cacheEventListener);  
    }

}  