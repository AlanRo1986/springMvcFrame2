package com.mymvc.system.provider.basic;

import com.mymvc.system.exception.IllegalServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by alan.luo on 2017/9/26.
 */
public interface IRequest {

    String get(String key);

    Object getParameter(String key);

    String getController();

    String getAction();

    String getMethod();

    Cookie getCookie(String key);

    String getHeader(String key);

    String getPathInfo();

    String getQueryString();

    String getRequestedSessionId();

    HttpSession getSession(boolean var1);

    HttpSession getSession();

    String getClientIp();

    String getRequestURL();

    String getContextType();

    int getContentLength();

    String getLocale();

}
