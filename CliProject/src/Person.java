package CliProject.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String id;


    public Person(String firstName, String lastName, String username, String password, String id , List people) throws DoublicateException {
        List<Person> personList = new ArrayList<Person>();
        for (Object i:people){
            personList.add((Person) i);
        }
//        System.out.println(personList);
        if(!personList.isEmpty()){
            for(Person i : personList){
                if(i.id.equals(id)){
                    throw new DoublicateException();
                }
            }
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id = id;
    }
    public Person(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
