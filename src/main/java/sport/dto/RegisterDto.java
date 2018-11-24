package sport.dto;

import lombok.Data;

@Data
public class RegisterDto {

    public String token;

    public String userId;

    public RegisterDto() {
    }

    public RegisterDto(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}
