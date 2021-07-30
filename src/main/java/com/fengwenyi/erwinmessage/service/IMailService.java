package com.fengwenyi.erwinmessage.service;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
public interface IMailService {

    /**
     * 发送文本
     * @param receiver 收件箱
     * @param title 标题
     * @param content 内容
     *
     * @return 发送结果，true:发送成功 / false:发送失败
     */
    boolean sendText(String receiver, String title, String content);

}
