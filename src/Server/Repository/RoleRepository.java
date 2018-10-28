package Server.Repository;

import Server.Entity.Role;

import java.util.HashMap;
import java.util.List;

public class RoleRepository extends AbstractRepository {
    public RoleRepository() {
        super("Role");
    }

    public Role getRoleById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List role = read(params);

        return role != null && role.size() == 1 ? (Role) role.get(0) : null;
    }

    public Role getRolebyName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        List role = read(params);

        return role != null && role.size() == 1 ? (Role) role.get(0) : null;
    }
}
