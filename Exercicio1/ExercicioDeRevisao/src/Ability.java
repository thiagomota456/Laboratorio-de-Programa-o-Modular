public class Ability {
    private String name;
    private float number;

    public Ability(String name, float number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        try {
            return (Ability) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Ability(getName(), getNumber());
        }
    }

    public String toString() { 
        return this.name + ", " + this.number;
    } 
}
