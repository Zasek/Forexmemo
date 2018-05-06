package com.spitfire.forexmemo.config;

import com.spitfire.forexmemo.annotation.AdminRequired;
import com.spitfire.forexmemo.domain.Administrator;
import com.spitfire.forexmemo.domain.TokenModel;
import com.spitfire.forexmemo.manager.TokenManager;
import com.spitfire.forexmemo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by H.W. on 5/6/2018.
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AdministratorService administratorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // check if method is annotated with @AdminRequired
        AdminRequired methodAnnotation = method.getAnnotation(AdminRequired.class);
        if (methodAnnotation != null) {
            // extract token in request header
            String token = request.getHeader("token");
            if (token == null) {
                throw new RuntimeException("No token provided");
            }
            TokenModel tokenModel = tokenManager.decodeToken(token);

            // check token
            if (!tokenManager.checkToken(tokenModel)) {
                throw new RuntimeException("Invalid token");
            }

            // check if the admin account is valid
            Administrator admin = administratorService.searchByAid(tokenModel.getId());
            if (admin == null) {
                throw new RuntimeException("Invalid token");
            }

            request.setAttribute("auth", "admin");
            request.setAttribute("visitor", admin);
        }

        return true;
    }
}
