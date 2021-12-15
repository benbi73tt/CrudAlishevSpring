package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom",12,"an@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Rob",23,"byr@ya.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Mike",65,"ton@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Katy",32,"kaat@gmail.com"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        //ищем человека с данным id или возвращаем null
        return people.stream().filter(person->person.getId()==id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdate = show(id);

        personToBeUpdate.setName(updatePerson.getName());
        personToBeUpdate.setAge(updatePerson.getAge());
        personToBeUpdate.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
