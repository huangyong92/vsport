package sport.util;

import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.domain.BatchObject;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushUtilTest {

    @Autowired
    private PushUtil pushUtil;

    private String clientId = "1df8559716e00195db2b1a0eeba283ce";
    private String yClientId = "afa4b62008f1376205717e3578587f93";

    @Test
    public void siglePush() {
//        TransmissionTemplate template =
//                pushUtil.transmissionTemplateProduce("就这样吧", 1);
//        pushUtil.siglePush(template, clientId);

        Style0 style0 = new Style0();
        style0.setTitle("周末一起打羽毛球");
        style0.setText("快点击看看");

        NotificationTemplate notificationTemplate =
                pushUtil.notificationTemplateProduct(style0, "运动", 1);

        pushUtil.siglePush(notificationTemplate, clientId);
    }

    @Test
    public void listPush() {
    }

    @Test
    public void batchPush() {
        List<BatchObject> batchObjectList = new ArrayList<>();

        Style0 style0 = new Style0();
        style0.setTitle("约你一同运动");
        style0.setText("小丫头约你周末一起锻炼");

        LinkTemplate linkTemplate = pushUtil.linkTemplateProduce(style0, "www.baidu.com");

        BatchObject batchObject = new BatchObject(linkTemplate, clientId);
        batchObjectList.add(batchObject);

        BatchObject batchObject1 = new BatchObject(linkTemplate, yClientId);
        batchObjectList.add(batchObject1);

        pushUtil.batchPush(batchObjectList);
    }
}