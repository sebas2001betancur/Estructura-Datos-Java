package doublelist;
import java.time.LocalDate;

public class Advice {
    private int code;
    private String name;
    private String id;
    private String theme;
     private String subject;
    private LocalDate date;

    public Advice(int code, String name, String id,
            String theme, String subject, LocalDate date) {
        this.code = code;
        this.name = name;
        this.id = id;
        this.theme = theme;
        this.subject = subject;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Advice{" + "code=" + code + ", name=" + name + ", id=" + id + ", theme=" + theme + ", subject=" + subject + ", date=" + date + '}';
    }
   
    
    
}
