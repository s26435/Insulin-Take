package pl.jnwol.insulinintake.Repositories;

import org.springframework.stereotype.Repository;
import pl.jnwol.insulinintake.Model.InsulinIntake;
import pl.jnwol.insulinintake.Model.day_time;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public class InsulinRepository {
    public List<InsulinIntake>  insulinIntakes;

    public InsulinRepository(){
        insulinIntakes = new ArrayList<>();
    }

    public int getNextId(){
        int highest_id = 0;
        for (InsulinIntake insulinIntake : insulinIntakes) {
            int temp = insulinIntake.getId();
            if (temp > highest_id) {
                highest_id = temp;
            }
        }
        return highest_id+1;
    }

    private static day_time getDayTime(int hour) {
        if (hour >= 6 && hour < 12) {
            return day_time.MORNING;
        } else if (hour == 12) {
            return day_time.NOON;
        } else if (hour > 12 && hour < 18) {
            return day_time.AFTERNOON;
        } else if (hour >= 18 && hour < 21) {
            return day_time.EVENING;
        } else {
            return day_time.NIGHT;
        }
    }

    public void add(InsulinIntake insulinIntake) {
        insulinIntakes.add(insulinIntake);
    }

    public void addNewInsulinIntake(float amount){
        if(amount<=0) throw new IllegalArgumentException("Amount must be greater than zero");
        LocalDateTime now = LocalDateTime.now();
        insulinIntakes.add(new InsulinIntake(getNextId(), amount, now.getDayOfMonth(), now.getMonthValue(), getDayTime(now.getHour())));
    }

    public Optional<InsulinIntake> findByID(int id) {
        for(InsulinIntake insulinIntake : insulinIntakes) {
            if(insulinIntake.getId() == id){
                return Optional.of(insulinIntake);
            }
        }
        return Optional.empty();
    }

    public List<InsulinIntake> getAll() {
        return insulinIntakes;
    }

    public void delete(int id){
        insulinIntakes.removeIf(insulinIntake -> insulinIntake.getId() == id);
    }

    public void deleteLast() {
        insulinIntakes.removeLast();
    }
}
