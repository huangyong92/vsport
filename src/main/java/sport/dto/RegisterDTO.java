package sport.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    public String token;

    public String userId;

    public RegisterDTO() {
    }

    public RegisterDTO(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}
