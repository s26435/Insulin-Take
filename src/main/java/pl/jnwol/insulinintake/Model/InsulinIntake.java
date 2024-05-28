package pl.jnwol.insulinintake.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(name = "Insulin Intake", description = "Container for data on one insulin intake - amount and time")
public class InsulinIntake {

    @Schema(description = "ID in database")
    int id;

    @Schema(description = "Amount of insulin taken")
    float amount;

    @Schema(description = "Day of the month in which insulin was taken")
    int day;

    @Schema(description = "Month in which insulin was taken")
    int month;

    @Schema(description = "The time of day the insulin was taken")
    day_time day_time;
}
