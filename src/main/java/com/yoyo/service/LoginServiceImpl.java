package com.yoyo.service;

import com.yoyo.util.RSASecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RSASecurityUtils rsaSecurityUtils;

    @Override
    public String doLogin(String userName, String password_encrypt) throws Exception {
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAI7p6FnId3e93gEI4kgwpyZ8kVEcQ9uUlcSwK9Z+RUOLUTqXAQs9ua4Q+mBndWH+WRXfN65+DfoNleO/AQFANDLQCn8PKJd8R/YSwLM/Fa+BfWRR5tEtEVLj2Zp6JvFD44YgvlOYP3w73HIpB3xfw7B7i1qYHWvnXmxYtf8IDK/PAgMBAAECgYBIiZDVgVqh/EziWZAA9DDi5/caJC6NFS2vV3voss6VKfFgu6RcAEhugU+T/u8lfePZpiKs/m45rQuXPStVZzEWle0BuO6kQlnsVqsUWOFcRdZUXuoO4Rh+70u39p/kuCpELGLSwLnKI+c9N3SIy2NOWzi6+uX2lqygt1RkSZvEAQJBANaYPTgjsX4aVx1HNlVoLrsrW9xMUi0ClhlnK5X2ZuoqIzDDaqVZgTBYKNpAsDpon1GlsvH/T4uyXlP0v2otsu8CQQCqfQjIPXCDbOxIqFFqT6I2WrJ4UxgYgUD6omaABPxvhu3IpVZEMd875gw+BD2ODSuGr+t4DrUSoKtbKDlaJVEhAkEAuWWVnmbX9YZ0NMtNWcREe1geaNlXNaPCYfgMY+cZSr1U4dAy1t/ZCRdhVA4HMq8o1bU+QGy/IIXdkNMmfGk26QJBAIPM38I1xUrFJTHSdgZzA9tzaG+fBYzEN+DLNeSYdjMeI6uvLi7QQ10CLsqu6otr4Q9h5u7Mp+17qp1xTk1DdiECQQC3asuqrFSUVByxsMaG6QcG+yQm5GdCRe9vjOsXvBERHxRg6LPi0H3A+i6FSrFUmgKOaxzgRqEW/Q7yvPSjZmNp";
        String decrypt_psword = null;
        try {
            decrypt_psword = RSASecurityUtils.decrypt(privateKey, password_encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("密码解密：" + decrypt_psword);
        return decrypt_psword;
    }
}
