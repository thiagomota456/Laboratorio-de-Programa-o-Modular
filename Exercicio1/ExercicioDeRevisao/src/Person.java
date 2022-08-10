import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private String name;
    private LinkedHashMap<String, Float> skill;

    public Person(String name, LinkedHashMap<String, Float> skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashMap getSkill() {
        return skill;
    }

    public void setSkill(LinkedHashMap<String, Float> skill) {
        this.skill = skill;
    }
    
    public void addSkill(String key, Float value){
        if(this.skill != null){
            this.skill.put(key, value);
        }
    }
    
    public String toString() {

        return this.name + ": " + this.getSkill().toString();
    }
}
