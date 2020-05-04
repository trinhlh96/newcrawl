package entity;

public class Article {
    private int id;
    private String url;
    private String title;
    private String description;
    private String contest;
    private String source;
    private int status;
    private String urlname;
    private String forder;

    public Article(int id, String title, String description, String forder) {
        this.id = id;
        this.title = title;
        this.forder = forder;
        this.description = description;
    }

    public Article(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Article(int id, String title, String contest) {
        this.id = id;
        this.title = title;
        this.contest = contest;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contest='" + contest + '\'' +
                ", source='" + source + '\'' +
                ", status=" + status +
                ", urlname='" + urlname + '\'' +
                ", forder='" + forder + '\'' +
                '}';
    }

    public Article() {
    }

    public Article(int id, String url, String title, String description, String contest, String source, int status, String urlname, String forder) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.contest = contest;
        this.source = source;
        this.status = status;
        this.urlname = urlname;
        this.forder = forder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContest() {
        return contest;
    }

    public void setContest(String contest) {
        this.contest = contest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    public String getForder() {
        return forder;
    }

    public void setForder(String forder) {
        this.forder = forder;
    }

    public String toNewString() {
        return "Title: " + this.title + "\nContent: "+ this.contest;
    }
}
