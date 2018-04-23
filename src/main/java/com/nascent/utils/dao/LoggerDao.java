package com.nascent.utils.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils.dao
 * @Description: TODO
 * @date 2018/4/20 9:29
 */
public class LoggerDao {

    private static final Logger log = LoggerFactory.getLogger(LoggerDao.class);

    public void test() {
        log.error("dao错误");
        log.info("dao信息");
        log.warn("dao警告");
    }
}
