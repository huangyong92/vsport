package sport.service.impl;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.service.TokenService;
import sport.util.TokenUtil;

@Service
public class TokenImpl implements TokenService {

    @Autowired
    private TokenUtil mTokenUtil;

    @Override
    public String createToken(String userId) {
        try {
            return mTokenUtil.createToken(userId);

            //todo 日志记录和常见异常处理
        } catch (JoseException e) {
            e.printStackTrace();
        }

        return null;

    }
}
