package com.atguigu.hander;

import com.alibaba.fastjson.JSON;
import com.atguigu.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class AtguiguExceptionHandler {
    private static final String PAGE_ERROR = "common/error";

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断是异步请求还是同步请求
        //如果是异步请求，要通过Result。build 通过request.getHeader "accept"是否包含application/json  response回写到客户端 （转成Json字符串的形式）

        if (request.getHeader("accept").contains("application/json")) {
            Result<Object> result = Result.build(null, 201, e.getMessage());
            response.getWriter().write(JSON.toJSONString(result));
            return null;
        }
        //如果是同步请求要放在请求域中
        request.setAttribute("errorMessage",e.getMessage());
        return PAGE_ERROR;

    }
}
