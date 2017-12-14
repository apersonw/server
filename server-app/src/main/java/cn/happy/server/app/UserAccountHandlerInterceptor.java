package cn.happy.server.app;


import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.spring.AccountHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAccountHandlerInterceptor extends AccountHandlerInterceptor<UserAccount> {

    @Value("${management.context-path}")
    private String managementContextPath;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isNotEmpty(managementContextPath)) {
            if (request.getRequestURI().startsWith(managementContextPath)) {
                return true;
            }
        }
        if (request.getRequestURI().startsWith("/error")) {
            return true;
        }
        if (request.getRequestURI().startsWith("/swagger-resources")) {
            return true;
        }
        if (request.getRequestURI().startsWith("/ws")) {
            return true;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    protected UserAccount getAccountObject(HttpServletRequest httpServletRequest) throws Exception {
        return null;
    }

}
