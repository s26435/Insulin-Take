package pl.jnwol.insulinintake.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jnwol.insulinintake.Model.InsulinIntake;
import pl.jnwol.insulinintake.Services.IntakeService;

@RestController
@RequestMapping("/intake")
public class InsulinIntakeController {

    public final IntakeService intakeService;

    public InsulinIntakeController(IntakeService intakeService) {
        this.intakeService = intakeService;
    }

    @GetMapping("/insulin/{id}")
    public ResponseEntity<InsulinIntake> getIntakeByID(@PathVariable int id){
        try {
            return ResponseEntity.ok(intakeService.getIntakeById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/newIntake")
    public ResponseEntity<Void> newInstulinIntake(@RequestBody InsulinIntake insulinIntake){
        if(insulinIntake != null){
            intakeService.addNew(insulinIntake);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World!");
    }

}
