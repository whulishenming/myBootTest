/*
package com.lsm.boot.shiro.config;

import com.lsm.boot.shiro.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap();
        attributes.put("code", ResultCode.C500.getCode());
        attributes.put("msg", ResultCode.C500.getDesc());
        log.error("内部异常", ex);
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }

    @Bean
    public ExceptionHandler createExceptionHandler() {
        return new ExceptionHandler();
    }
}
*/
