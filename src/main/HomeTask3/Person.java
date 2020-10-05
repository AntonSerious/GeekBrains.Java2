package HomeTask3;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String surname;
    private int phoneNumber;
    private String email;


    public Person(String surname, int phoneNumber, String email){
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getSurname(){
        return this.surname;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmail(){
        return this.email;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return phoneNumber == person.phoneNumber &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(email, person.email);
    }
    //тут считаю, что человек однозначно определяется по фамилии и номеру телефона
    //соответсвенно переписываю HashCode
    @Override
    public int hashCode() {
        return Objects.hash(surname, phoneNumber);
    }

    @Override
    public int compareTo(Person p) {
        if(this.surname.length() > p.surname.length() ) return 1;
        if(this.surname.length() < p.surname.length() ){
            return -1;
        }
        else{
            return 0;
        }


    }

}
