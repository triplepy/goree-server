package com.goree.api.auth;

import com.goree.api.domain.Member;
import com.goree.api.util.HttpHeaderConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 원영 on 2015-07-31.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        registerTokenToAuthContext(request);
        registerMemberInfoToAuthContext();

        return true;
    }

    private void registerMemberInfoToAuthContext() {
        Member member = authService.findMemberLoggedIn();
        AuthContext.memberInfo(member);
    }

    private void registerTokenToAuthContext(HttpServletRequest request) {
        String tokenRecieved = request.getHeader(HttpHeaderConstants.AUTH_TOKEN);
        AuthContext.token(tokenRecieved);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AuthContext.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // do nothing
    }
}
