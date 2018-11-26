package Server.Repository;

import Server.Entity.Location;

import java.util.HashMap;
import java.util.List;

public class LocationRepository extends AbstractRepository {
    public LocationRepository() {
        super("Location");
    }

    public Location getLocationById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List location = read(params);

        return location != null && location.size() == 1 ? (Location) location.get(0) : null;
    }

    public Location getLocationbyName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        List location = read(params);

        return location != null && location.size() == 1 ? (Location) location.get(0) : null;
    }
}
