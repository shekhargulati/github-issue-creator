package ghic;

public class Issue {
    private final String id;
    private final String number;
    private final String title;
    private final String state;
    private final int comments;

    public Issue(String id, String number, String title, String state, int comments) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.state = state;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public int getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", comments=" + comments +
                '}';
    }
}
