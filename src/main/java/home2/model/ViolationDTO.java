package home2.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationDTO implements Comparable<BigDecimal>{
  String type;
  BigDecimal fine;

  @Override
  public int compareTo(BigDecimal o) {
    return fine.compareTo(o);
  }
}
