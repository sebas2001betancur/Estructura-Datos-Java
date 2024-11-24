package queue;

public class Process {
    private int code;
    private String description;
    private double timer;
    private double timea;
    private int priority;

    public Process(int code, String description, 
            double timer, int priority) {
        this.code = code;
        this.description = description;
        this.timer = timer;
        this.priority = priority;
        timea=0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public double getTimea() {
        return timea;
    }

    public void setTimea(double timea) {
        this.timea = timea;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Process{" + "code=" + code + ", description=" + description + ", timer=" + timer + ", timea=" + timea + ", priority=" + priority + '}';
    }
    
    
    
    
}
