package com.filter;


import com.services.IUserStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Фильтр для обработки аутентификации пользователя.
 *
 * Created with IntelliJ IDEA.
 * User: killy
 * Date: 20.05.13
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class PreAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

    protected static final Logger log = Logger.getLogger(PreAuthentificationFilter.class);

    @Autowired
    private IUserStatusService userStatusService;

    /**
     * Выполняет проверку перед аутентификацией, запрещен ли доступ для пользователя.
     * При выполнении аутентификации, если возникла ошибка BadCredentialsException (неверный логин/пароль), пользователь обрабатывается в userStatusService.
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
        log.debug("attempt authentification");

        String username = obtainUsername(request);
        if(username == null){
            username = "";
        }username = username.trim();

        log.debug("user login is " + username);

        Authentication auth = null;
        try{
            auth = super.attemptAuthentication(request,response);
        }catch(AuthenticationException e){
            throw e;
        }
        return auth;
    }

    public IUserStatusService getUserStatusService() {
        return userStatusService;
    }

    public void setUserStatusService(IUserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }
}
