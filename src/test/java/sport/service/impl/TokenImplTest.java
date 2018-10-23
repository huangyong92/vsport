package sport.service.impl;

import org.jose4j.lang.JoseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.service.TokenService;
import sport.util.TokenUtil;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenImplTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void createToken() {

        try {
            String token = tokenService.createToken("1");
            String uid = tokenUtil.paraseToken(token);

            assertEquals("1", uid);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }
}