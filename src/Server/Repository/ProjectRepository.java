package Server.Repository;

import Server.Entity.Project;
import Server.Entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectRepository extends AbstractRepository {
    public ProjectRepository() {
        super("Project");
    }

    public Project getProjectById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List project = read(params);

        return project != null && project.size() == 1 ? (Project) project.get(0) : null;
    }

    public Project getProjectbyName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        List project = read(params);

        return project != null && project.size() == 1 ? (Project) project.get(0) : null;
    }

    public List getProjectByTask(Integer taskId) {
        TaskRepository taskRepository = new TaskRepository();
        Task task = taskRepository.getTaskById(taskId);

        return new ArrayList(task.getProjects());
    }
}
