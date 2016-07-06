package ghic;

public class IssueRequest {

    private final String title;
    private int milestone;
    private String[] labels;
    private String assignee;

    public IssueRequest(String title) {
        this.title = title;
    }

    public IssueRequest(String title, int milestone, String[] labels, String assignee) {
        this.title = title;
        this.milestone = milestone;
        this.labels = labels;
        this.assignee = assignee;
    }

    public String getTitle() {
        return title;
    }

    public int getMilestone() {
        return milestone;
    }

    public String[] getLabels() {
        return labels;
    }

    public String getAssignee() {
        return assignee;
    }
}
