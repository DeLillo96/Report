package Server.Remote;

import Server.Entity.Project;
import Server.Entity.Task;
import Server.Repository.ProjectRepository;
import Server.Repository.TaskRepository;
import Server.Result;
import Shared.RelationService;
import org.json.simple.JSONObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProjectTaskServiceImplementation extends UnicastRemoteObject implements RelationService {
    protected ProjectRepository projectRepository;
    protected TaskRepository taskRepository;

    public ProjectTaskServiceImplementation() throws RemoteException {
        projectRepository = new ProjectRepository();
        taskRepository = new TaskRepository();
    }

    @Override
    public JSONObject assign(Integer taskId, Integer projectId) throws Exception {
        Task task = taskRepository.getTaskById(taskId);
        Project project = projectRepository.getProjectById(projectId);

        for (Project projectTask : task.getProjects()) {
            if (projectTask.getId().equals(projectId)) {
                Result result = new Result();
                result.addData(project);
                return result.toJson();
            }
        }

        project.getTasks().add(task);
        return project.save().toJson();
    }

    @Override
    public JSONObject assign(Integer rightId, List leftIds) throws Exception {
        return null;
    }

    @Override
    public JSONObject assign(List rightIds, Integer leftId) throws Exception {
        return null;
    }

    @Override
    public JSONObject deAssign(Integer taskId, Integer projectId) throws Exception {
        Project project = projectRepository.getProjectById(projectId);

        for (Task task : project.getTasks()) {
            if (task.getId().equals(taskId)) {
                project.getTasks().remove(task);
                return project.save().toJson();
            }
        }

        Result result = new Result();
        result.addData(project);
        return result.toJson();
    }

    @Override
    public JSONObject leftRead(Integer projectId) throws Exception {
        List list = taskRepository.getTaskByProject(projectId);
        return new Result(true, list).toJson();
    }

    @Override
    public Integer rightCount(Integer rightId) throws Exception {
        return null;
    }

    @Override
    public Integer leftCount(Integer leftId) throws Exception {
        return null;
    }

    @Override
    public JSONObject rightRead(Integer taskId) throws Exception {
        List list = projectRepository.getProjectByTask(taskId);
        return new Result(true, list).toJson();
    }
}
