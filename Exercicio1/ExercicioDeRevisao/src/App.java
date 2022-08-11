import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class App {

    private static final String DEFALUT_SKILL_PATH = "/home/thiago/Desktop/Laboratorio-de-Programa-o-Modular/Exercicio1/competencias";
    private static final String DEFALUT_PROFILE_PATH = "/home/thiago/Desktop/Laboratorio-de-Programa-o-Modular/Exercicio1/candidatos";
    
    //Meus testes
    public static void main(String[] args) throws Exception {

        var people = readFiles();

        for(Person p : people){
            System.out.println(p.toString());
        }

        System.out.println();
        System.out.println();

        System.out.println(melhoEmUmaHabilidade("Gerência de projetos", people));

        System.out.println();
        System.out.println();

        System.out.println(umaHabilidadeObrigatoriaEUmaImportante("Gerência de projetos", "Java", people));

        System.out.println();
        System.out.println();

        System.out.println(melhorComConjuntoDeHabilidades(people));
    }

    private static String melhorComConjuntoDeHabilidades(List<Person> people){
        List<Ability> skillMediaPonderada = new ArrayList<>();
        
        for(Person p : people){

            System.out.println(p.getName());
            Float media = (float) 0.0;

            for (Map.Entry<String, Float> entry : ((LinkedHashMap<String, Float>)p.getSkill()).entrySet()) {
                media +=  entry.getValue();
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            
            media = media/p.getSkill().size();
            System.out.println("Media: "+ media);
            skillMediaPonderada.add(new Ability(p.getName(), media));

            System.out.println();
            System.out.println();
        }

        return selectBestProfile(skillMediaPonderada);
    }

    private static String umaHabilidadeObrigatoriaEUmaImportante(String habilidadeObrigatoria, String habilidadeImportante, List<Person> people){
        
        List<Ability> skillMediaPonderada = new ArrayList<>();
        
        for(Person p : people){
            Float mediaPonderada = ((Float)p.getSkill().get(habilidadeObrigatoria) * 2 + (Float)p.getSkill().get(habilidadeImportante) * 1) / 3;
            skillMediaPonderada.add(new Ability(p.getName(), mediaPonderada));    
        }

        return selectBestProfile(skillMediaPonderada);
    }

    private static String selectBestProfile(List<Ability> skillMediaPonderada) {
        int i = 0;
        float value = skillMediaPonderada.get(i).getNumber();
        String name = skillMediaPonderada.get(i).getName();

        for(i=0; i<skillMediaPonderada.size(); i++){
            if(skillMediaPonderada.get(i).getNumber() > value){
                value = skillMediaPonderada.get(i).getNumber();
                name = skillMediaPonderada.get(i).getName();
            }
            else if(skillMediaPonderada.get(i).getNumber() == value){
                name += " e " + skillMediaPonderada.get(i).getName();
            }
        }
        return name;
    }

    private static String melhoEmUmaHabilidade(String habilidade, List<Person> people){
        
        int i = 0;
        var name = people.get(i).getName();
        var value = (Float)people.get(i).getSkill().get(habilidade);

        for(i=1; i < people.size(); i++){

            var v1 = (Float)people.get(i).getSkill().get(habilidade);

            if( ((Float)people.get(i).getSkill().get(habilidade)) > value){
                name = people.get(i).getName();
                value = (Float)people.get(i).getSkill().get(habilidade);
            }
            else if( Objects.equals((Float)people.get(i).getSkill().get(habilidade), value)){
                name += " e " + people.get(i).getName();
            }
        }

        return name;
    }

    private static List<Person> readFiles(String pathSkill, String pathProfile) throws FileNotFoundException {
        
        List<Person> peopleList = new ArrayList<>();

        //Two Files and two Scaners is the best way ?
        //Tratar erros
        var abilitysFile = new File(pathSkill);
        var peopleFile = new File(pathSkill);
        
        var abilitysReader = new Scanner(abilitysFile);
        var peopleReader = new Scanner(peopleFile);

        List<Ability> abilitysList = new ArrayList<Ability>();

        while(abilitysReader.hasNext()){
            String abilityName = abilitysReader.nextLine();
            Ability a = new Ability(abilityName, 0);
            abilitysList.add(a);
        }

        abilitysReader.close();

        while(peopleReader.hasNext()){

            String personAndAtributes = peopleReader.nextLine();
            var array = personAndAtributes.split(";");
            String name = array[0];

            for(int i=1; i < array.length; i++){
                abilitysList.get(i-1).setNumber(Integer.parseInt(array[i]));
            }

            LinkedHashMap<String, Float> Skill = new LinkedHashMap<>(); 
            for(Ability a : abilitysList){
                Skill.put(a.getName(), a.getNumber());
            }

            Person p = new Person(name, Skill);
            peopleList.add(p);
        }

        peopleReader.close();
        
        return peopleList;
    }

}
