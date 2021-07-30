package com.fengwenyi.erwinmessage.service;


import com.fengwenyi.erwinmessage.ErwinMessageApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
@Component
public class MailServiceTests extends ErwinMessageApplicationTests {

    @Autowired
    private IMailService mailService;

    @Test
    public void testSendText() {
        boolean result = mailService.sendText("test title", "test content", "xfsy_2015@163.com");
        Assert.isTrue(result, "发送邮箱失败");
    }

}
