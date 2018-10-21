package sport.util;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.lang.JoseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenUtilTest {

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void testToken() {
//        String token = null;
//        try {
////            token = tokenUtil.createToken();
////            System.out.println(token);
//            token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDAxMjA5NzQsImp0aSI6IkhIWUF0N0taejljemlhYkdObEoyMEEiLCJpYXQiOjE1NDAxMjA5MTQsIm5iZiI6MTU0MDEyMDc5NCwiZW1haWwiOiJtYWlsQGV4YW1wbGUuY29tIn0.rdRnQQVCcFf_EUlDJw0eZU_Q3IVdvskpluMh9dL_lqo";
//            tokenUtil.paraseToken(token);
////        } catch (JoseException e) {
////            e.printStackTrace();
//        } catch (InvalidJwtException e) {
//            e.printStackTrace();
//        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    JwtConsumer jwtConsumer = tokenUtil.getConsumer();
//                    String token5 = tokenUtil.createToken(3);
////                    String token10 = tokenUtil.createToken(4);
////                    String token15 = tokenUtil.createToken(6);
////                    String token20 = tokenUtil.createToken(8);
//                    Thread.sleep(28 * 1000);
//                    jwtConsumer.processToClaims(token5);
//                    System.out.println("~~~~1~~~~~~~~~");
////                    Thread.sleep(2 * 60 * 1000);
////                    jwtConsumer.processToClaims(token10);
////                    System.out.println("~~~~2~~~~~~~~~");
////                    Thread.sleep(2 * 60 * 1000);
////                    jwtConsumer.processToClaims(token15);
////                    System.out.println("~~~~3~~~~~~~~~");
////                    Thread.sleep(2 * 60 * 1000);
////                    jwtConsumer.processToClaims(token20);
////                    System.out.println("~~~~4~~~~~~~~~");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (JoseException e) {
//                    e.printStackTrace();
//                } catch (InvalidJwtException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        try {
//            CountDownLatch countDownLatch = new CountDownLatch(1);
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String token = null;

        try {
//            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

            token = tokenUtil.createToken("abc123");
            String uid = tokenUtil.paraseToken(token);

            assertEquals("abc123", uid);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }
}