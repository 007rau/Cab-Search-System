package org.codejudge.sb.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AvailableDriver {
    private String name;
    private String car_number;
    private BigDecimal phone_number;
}
