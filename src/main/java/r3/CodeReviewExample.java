package r3;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


// Imagine you are a peer of the developer who committed this (syntactically correct) Java code and asked you to review
// their pull request. You work on the same product but are not familiar with this piece of work or its associated
// requirements.
//
// Please use Java comments for your review feedback, putting them on separate lines around the code. Do not modify the
// code itself.

public class CodeReviewExample {
    volatile Integer totalAge = 0;
    /*
    Why it is the variable volatile? if it is going to be changed by different thread the whole approach needed to be changed.
    Why it is built inside constructor method? Constructor method is used for initialization not to write business logic.

    Even though with this dummy way of doing it, it is better to remove it and create a method like:

    public Integer calculateTotalAge(PersonDatabase<Person> personPersonDatabase)--> return total age for all persons
     */

    CodeReviewExample(PersonDatabase<Person> personPersonDatabase) {
        Person[] persons = null;
        try {
            persons = personPersonDatabase.getAllPersons();
        } catch (IOException e) {
            /*
               Again the whole logic is wrong so man place like here won't  make sense.
               There is an IOException happen and needed to be properly handle and proper messaged get logged for whom is troubleshooting later on.
               Can't ignore this exception and move one
               Variable name should be at least 3 characters change it to (IOException ioex)
             */
        }

        List<Person> personsList = new LinkedList();//Even using linkedlist it should be  new LinkedList<>()
        //No need to use LinkedList, it is good if you have too many remove and add but here is about calculation so best option is using an ArrayList<>
        //    List<Person> personsList = new ArrayList<>();

        /* Regarding the loop:
          This is the worst way to convert an array to a List
          Use     List<Person> personsList = Arrays.asList(persons);
         */
        for (int i = 0; i <= persons.length; i++) {//persons.length could cause NPE!
            personsList.add(persons[i]);
        }
        /*
        If you were using list in the interface or any collection you could use java 8 to iterate over those item in the stream and calculate the age very easy.
                totalAge= personsList.parallelStream().mapToInt(Person::getAge).sum();
         */
        personsList.parallelStream().forEach(person -> {
            totalAge += person.getAge();
        });


        /* Same issue using LinkedList here
        It could use a simple filter to get the males and females.
        List<Person> males =personsList.stream().filter(p->p.gender.equalsIgnoreCase(MALE)).collect(Collectors.toList());
        List<Person> females =personsList.stream().filter(p->p.gender.equalsIgnoreCase(FEMALE)).collect(Collectors.toList());

        If it is about the number could only do a dif(females=personsList.size()-males.size())


        or just count the maile or female the mines the whole list size.

         */
        List<Person> males = new LinkedList<>();
        for (Person person : personsList) {
            switch (person.gender) {
                case "Female":
                    personsList.remove(person);
                case "Male":
                    males.add(person);
            }
        }


        /*Should not be using any   System.out.println
         Should be suing a logger like this:
        private final Logger logger = Logger.getLogger(CodeReviewExample.class.getName());
         logger.log(Level.INFO,"Total age ={}", totalAge);
         */

        System.out.println("Total age =" + totalAge);
        System.out.println("Total number of females =" + personsList.size());
        System.out.println("Total number of males =" + males.size());
    }

}


class Person {

    private int age;
    private String firstName;
    private String lastName;
    String gender;//Missing access level
    // private String gender;

    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        return this.lastName == ((Person) obj).lastName;
        /*
        If you are going to override the equal it needed to be both equal and hash together. Something like this:
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender);
            }

            @Override
            public int hashCode() {
                return Objects.hash(age, firstName, lastName, gender);
            }
         */

    }


}


interface PersonDatabase<E> {

    Person[] getAllPersons() throws IOException;
    /*
    It is better to use Optional<List<E>> Optional<Collection<E>> or at least a List<E> for return rather than an array.
     */

}

/*
Again the whole login needed to be refactored and break it down to small functions that returning on single tasks.

findsFemales();
findMales();
calculateTotalAges();
loadPersons();



Classes naming convention.
 */