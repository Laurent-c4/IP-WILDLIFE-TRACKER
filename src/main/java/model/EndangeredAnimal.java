package model;

public class EndangeredAnimal extends Fauna {
    private String health;
    private String age;
    public static final String ANIMAL_TYPE = "Endangered Species";

    public EndangeredAnimal(String name, String age, String health){
        this.name=name;
        this.age=age;
        this.health=health;
        this.type=ANIMAL_TYPE;
    }
}
