package sport.dto;

import lombok.Data;
import sport.domain.BodilyForm;
import sport.domain.Weight;

import java.util.List;

@Data
public class BodilyFormDto {

    public List<Weight> weights;

    public List<BodilyForm> bodilyForms;
}
