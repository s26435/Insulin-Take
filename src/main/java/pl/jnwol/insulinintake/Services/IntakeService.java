package pl.jnwol.insulinintake.Services;

import org.springframework.stereotype.Service;
import pl.jnwol.insulinintake.Exceptions.InsulinIntakeNotFoundException;
import pl.jnwol.insulinintake.Model.InsulinIntake;
import pl.jnwol.insulinintake.Repositories.InsulinRepository;

@Service
public class IntakeService {
    private final InsulinRepository insulinRepository;

    public IntakeService(InsulinRepository insulinRepository) {
        this.insulinRepository = insulinRepository;
    }

    public InsulinIntake getIntakeById(int id) throws InsulinIntakeNotFoundException {
        return insulinRepository.findByID(id).orElseThrow(InsulinIntakeNotFoundException::new);
    }

    public void addNew(InsulinIntake insulinIntake) {


    }
}
