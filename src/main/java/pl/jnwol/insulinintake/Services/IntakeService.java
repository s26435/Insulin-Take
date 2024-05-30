package pl.jnwol.insulinintake.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.jnwol.insulinintake.Exceptions.DataBaseIsEmpty;
import pl.jnwol.insulinintake.Exceptions.InsulinIntakeNotFoundException;
import pl.jnwol.insulinintake.Model.InsulinIntake;
import pl.jnwol.insulinintake.Repositories.InsulinRepository;

import java.util.List;

@Service
public class IntakeService {
    private final InsulinRepository insulinRepository;

    public IntakeService(InsulinRepository insulinRepository) {
        this.insulinRepository = insulinRepository;
    }

    public InsulinIntake getIntakeById(int id) throws InsulinIntakeNotFoundException {
        return insulinRepository.findByID(id).orElseThrow(InsulinIntakeNotFoundException::new);
    }

    public void addNew(float amount) {
        insulinRepository.addNewInsulinIntake(amount);
    }

    public void addNewInsulin(InsulinIntake insulinIntake) {
        if(insulinIntake.getId() < insulinRepository.getNextId()) throw new IllegalArgumentException("Wrong id!");
        if(insulinIntake.getAmount() < 0) throw new IllegalArgumentException("Wrong amount!");
        if(insulinIntake.getDay()>31||insulinIntake.getDay()<0) throw new IllegalArgumentException("Wrong day!");
        if(insulinIntake.getMonth()>12||insulinIntake.getMonth()<=0) throw new IllegalArgumentException("Wrong month!");
        if(insulinIntake.getDay_time() == null) throw new IllegalArgumentException("Wrong day time!");
        insulinRepository.add(insulinIntake);
    }

    public ResponseEntity<List<InsulinIntake>> getAll() throws DataBaseIsEmpty {
        if(insulinRepository.getAll().isEmpty()) throw new DataBaseIsEmpty();
        return ResponseEntity.ok(insulinRepository.getAll());
    }

    public void delete(int id){
        insulinRepository.delete(id);
    }

    public void deleteLast() {
        insulinRepository.deleteLast();
    }
}
