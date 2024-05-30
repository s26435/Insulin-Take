package pl.jnwol.insulinintake.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jnwol.insulinintake.Exceptions.DataBaseIsEmpty;
import pl.jnwol.insulinintake.Model.InsulinIntake;
import pl.jnwol.insulinintake.Services.IntakeService;

import java.util.List;

@RestController
@RequestMapping("/intake")
public class InsulinIntakeController {

    public final IntakeService intakeService;

    public InsulinIntakeController(IntakeService intakeService) {
        this.intakeService = intakeService;
    }


    @Operation(summary = "Get all insulin takes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insulin intakes correctly retrieved from database"),
            @ApiResponse(responseCode = "418", description = "I am a teapot"),
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<InsulinIntake>> getAll() throws DataBaseIsEmpty {
        return intakeService.getAll();
    }

    @Operation(summary = "Get insulin intake by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insulin intake correctly retrieved from database"),
            @ApiResponse(responseCode = "404", description = "No insulin download found in database with specified id"),
    })
    @GetMapping("/insulin/{id}")
    public ResponseEntity<InsulinIntake> getIntakeByID(@PathVariable int id){
        try {
            return ResponseEntity.ok(intakeService.getIntakeById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Post new intake by given json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insulin intake correctly saved to database"),
            @ApiResponse(responseCode = "400", description = "Incorrect syntax of insulin intake"),
    })
    @PostMapping("/newIntake")
    public ResponseEntity<Void> newInstulinIntake(@RequestBody InsulinIntake insulinIntake){
        if(insulinIntake != null){
            intakeService.addNewInsulin(insulinIntake);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Put new insulin intake")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insulin intake correctly saved to database"),
            @ApiResponse(responseCode = "400", description = "Incorrect amount of insulin"),
    })
    @PutMapping("/new/{amount}")
    public ResponseEntity<Void> newInstulinIntake(@PathVariable float amount){
        intakeService.addNew(amount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInstulinIntake(@PathVariable int id){
        intakeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/last")
    public ResponseEntity<Void> deleteLastInstulinIntake(){
        intakeService.deleteLast();
        return ResponseEntity.ok().build();
    }

}
