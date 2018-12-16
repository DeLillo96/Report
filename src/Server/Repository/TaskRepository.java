package Server.Repository;

import Server.Entity.Project;
import Server.Entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskRepository extends AbstractRepository {
    public TaskRepository() {
        super("Task");
    }

    public Task getTaskById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List task = read(params);

        return task != null && task.size() == 1 ? (Task) task.get(0) : null;
    }

    public List getTime(HashMap<String, Object> params) {
        setTableName("Time");
        List result = read(params);
        setTableName("Task");
        return result;
    }
    
    public List getCost(HashMap<String, Object> params) {
        setTableName("Cost");
        List result = read(params);
        setTableName("Task");
        return result;
    }
    
    public List getTaskByProject(Integer projectId) {
        ProjectRepository projectRepository = new ProjectRepository();
        Project project = projectRepository.getProjectById(projectId);

        return new ArrayList(project.getTasks());
    }
}
