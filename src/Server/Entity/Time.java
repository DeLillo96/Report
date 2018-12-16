package Server.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Time")
@DiscriminatorValue("Time")
public class Time extends Task {

    public Time(String description) {
        super(description);
    }

    public Time() {}
}


