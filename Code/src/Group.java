public class Group {
    private int id;
    private  String name;
    private Group superGroup;
    boolean isRoot;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSuperGroup(Group superGroup) {
        this.superGroup = superGroup;
    }

    public Group getSuperGroup() {
        return superGroup;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
