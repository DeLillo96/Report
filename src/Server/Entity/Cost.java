package Server.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Cost")
@DiscriminatorValue("Cost")
public class Cost extends Task {

    public Cost(String description) {
        super(description);
    }

    public Cost(){}
}


