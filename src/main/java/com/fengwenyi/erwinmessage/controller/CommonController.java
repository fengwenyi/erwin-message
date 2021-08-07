package com.fengwenyi.erwinmessage.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.erwinmessage.service.IMailService;
import com.fengwenyi.erwinmessage.service.IRedisService;
import com.fengwenyi.erwinmessage.util.CacheKeyUtils;
import com.fengwenyi.erwinmessage.util.CodeUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
@Tag(name = "通用 API")
@RestController
@RequestMapping("/common")
public class CommonController {

    private Producer producer;
    private IRedisService redisService;
    private IMailService mailService;

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    public void setRedisService(IRedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    // 图形验证码
    @Operation(summary = "图形验证码")
    @GetMapping("/captcha.jpg")
    public void captcha(String account, HttpServletResponse response) throws IOException {

        //生成文字验证码
        String code = producer.createText();

        redisService.set(CacheKeyUtils.genCaptchaKey(account), code);

        //redisService.set(uuid, code);
        //session.setAttribute("code",code);
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");

        BufferedImage image = producer.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @Operation(summary = "发送邮箱验证码")
    @GetMapping("/sendMailCode")
    public ResultTemplate<Void> sendMailCode(String account, String captcha) {
        String cacheCaptcha = redisService.get(CacheKeyUtils.genCaptchaKey(account));
        if (!StringUtils.hasText(cacheCaptcha) || !cacheCaptcha.equals(captcha)) {
            if (StringUtils.hasText(cacheCaptcha)) {
                redisService.delete(CacheKeyUtils.genCaptchaKey(account));
            }
            return ResultTemplate.fail("图形验证码不正确");
        }
        String mailCode = CodeUtils.gen(4);
        redisService.set(CacheKeyUtils.genMailCodeKey(account), mailCode);
        boolean rs = mailService.sendText(account, "验证码", mailCode);
        if (rs) {
            return ResultTemplate.success();
        }
        return ResultTemplate.fail("验证码发送失败");
    }

}
