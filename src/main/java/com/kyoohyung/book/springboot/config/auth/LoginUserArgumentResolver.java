package com.kyoohyung.book.springboot.config.auth;

import com.kyoohyung.book.springboot.config.auth.Dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * HandlerMethodArgumentResolver 는 한가지 기능을 지원한다.
     * 바로 조건에 맞는 경우 메소드가 있다면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로 해당 매소드의 파라미터로 넘길 수 있다.
     * cf. LoginUserArgumentResolver를 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가 해야한다.
     */


   private final HttpSession httpSession;

    /**
     * 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단한다.
     * 여기서는 파라미터에 @LoginUser 어노테이션이 붙어 있고, 지
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;// 여기서는 파라미터에 @LoginUser 어노테이션이 붙어 있는지 확인

        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // 파라미터에 @LoginUser 어노테이션이 붙어 있는지 확인

        return isLoginUserAnnotation && isUserClass;
    }

    /**
     * 파라미터에 전달할 객체를 생성 한다. 여기서는 세션에서 객체를 가져온다.
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
