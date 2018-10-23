package sport.service;

import java.util.Set;

public interface DeviceService {

    void updateDeviceStatu(String userId, String deviceId, String statu);

    String getCurrentDevice(String userId);

    Set<String> getDevices(String userId);
}
