package com.fengwenyi.erwinmessage.security.util;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.javalib.convert.JsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
public class MyUtils {

    public static void renderError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ResultTemplate<Map<String, String>> resultModel = ResultTemplate.fail(message);
        String str = JsonUtils.convertString(resultModel);
        out.write(str);
        out.flush();
        out.close();
    }

}
