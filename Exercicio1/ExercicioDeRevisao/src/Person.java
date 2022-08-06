import java.util.List;

public class Person {
    private String name;
    private List<Ability> abilities;
    
    public Person(String name, List<Ability> abilities) {
        this.name = name;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
    }
}
