package com.fengwenyi.erwinmessage.service;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-08
 */
public interface IRedisService {

    void set(String key, String value);

    String get(String key);

    void delete(String key);

}
