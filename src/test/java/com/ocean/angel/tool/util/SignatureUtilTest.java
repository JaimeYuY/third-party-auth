package com.ocean.angel.tool.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SignatureUtilTest {

    @Test
    void generateSignature() {
        String appSecret = "udgd4u77udcelsuw8zu5de2sdot5oair";
        String random = "hb@jk2ejw#";
        String signature = SignatureUtil.generateSignature(appSecret, random);
        log.info("{}", signature);
    }

    @Test
    void checkSignature() {
        String appSecret = "udgd4u77udcelsuw8zu5de2sdot5oair";
        String random = "hb@jk2ejw#";
        String signature = "f06e97e1780955420c47f7f4784def13";
        boolean result = SignatureUtil.checkSignature(appSecret, random, signature);
        log.info("{}", result);
    }

    @Test
    void getRandom() {
        String appSecret = "udgd4u77udcelsuw8zu5de2sdot5oair";
        String signature = "f06e97e1780955420c47f7f4784def13";
        String random = SignatureUtil.getRandom(appSecret, signature);
        log.info("{}", random);
    }
}
