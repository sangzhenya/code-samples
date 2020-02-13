package org.xinyue.xsecurity.web.service.impl;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.xinyue.xsecurity.web.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {
    private Log log = LogFactory.getLog(IndexService.class);

    @Override
    public void valid(String msg) {
        log.info(msg);
    }
}

