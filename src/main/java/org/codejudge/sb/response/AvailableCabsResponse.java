package org.codejudge.sb.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codejudge.sb.entities.AvailableDriver;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AvailableCabsResponse extends Response {
    private List<AvailableDriver> available_cabs;
}
