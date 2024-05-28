package pl.jnwol.insulinintake.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InsulinIntake {
    int id;
    float amount;
    int day;
    int month;
    day_time day_time;
}
