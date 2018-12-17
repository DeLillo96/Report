package Server.Repository;

import Server.Entity.Report;

import java.util.HashMap;
import java.util.List;

public class ReportRepository extends AbstractRepository {
    public ReportRepository() {
        super("Report");
    }

    public Report getReportById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List report = read(params);

        return report != null && report.size() == 1 ? (Report) report.get(0) : null;
    }
}
