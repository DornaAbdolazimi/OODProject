package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public static int findGroupIdByName(String name, Connection myCon){
        try{
            Statement myState = myCon.createStatement();
            ResultSet myRes = myState.executeQuery("select * from item_group");
            while(myRes.next()) {
                if (myRes.getString("name").equals( name))
                    return myRes.getInt("id");

            }

        }catch (Exception e){

        }
        return -1;
    }

}
