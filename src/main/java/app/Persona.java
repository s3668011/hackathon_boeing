package app;

public class Persona {
    private String name;
    private String[] attributes;
    private String[] goals;

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes.split(",");
    }
    public void setGoals(String goals) {
        this.goals = goals.split(",");
    }

    public String[] getGoals() {
        return goals;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }
}
