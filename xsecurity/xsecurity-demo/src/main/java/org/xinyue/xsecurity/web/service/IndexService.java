package org.xinyue.xsecurity.web.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    private Log log = LogFactory.getLog(IndexService.class);
    public void valid(String msg) {
        log.info(msg);
    }
}
