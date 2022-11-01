import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        long juvenile = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + juvenile);

        List<String> recruiter = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN &&
                        person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily).toList();
        System.out.println("Количество призывников: " + recruiter.size());

        List<Person> employablePeople = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        person.getAge() >= 18 &&
                        (person.getAge() <= 60 && person.getSex() == Sex.WOMAN
                                || person.getAge() <= 65 && person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + employablePeople.size());

    }
}
