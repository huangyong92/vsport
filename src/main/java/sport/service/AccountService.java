package sport.service;

public interface AccountService {

    void getSmsCodes(String mobile);

    boolean isDayLimit(String mobile);

    boolean isIntervalOk(String mobile);

    int sendSmsCode(String mobile, String smsType);

    int verifySmsCode(String mobile, String smsCode);



}
