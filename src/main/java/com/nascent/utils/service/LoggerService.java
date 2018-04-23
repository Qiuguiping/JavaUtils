package com.nascent.utils.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils.service
 * @Description: TODO
 * @date 2018/4/20 9:29
 */
public class LoggerService {

    private static final Logger log = LoggerFactory.getLogger(LoggerService.class);

    public void test() {
        log.error("service错误");
        log.info("service信息");
        log.warn("service警告");
    }
}
