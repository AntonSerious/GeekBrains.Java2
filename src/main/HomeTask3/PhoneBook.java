package HomeTask3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class PhoneBook{
    //каждой фамилии ставится в соответствие сет из Person. В сете может быть несколько Person. Те случаи, когда по одной фамилии в записной книжке записаны две разные личности.
    public HashMap<String, HashSet<Person>> info = new HashMap<>();
    public PhoneBook(){
    }
    // тут вся соль
    public void add(String surname, Person p){
        HashSet<Person> hashSetToUpdate = new HashSet<>();
        if(info.get(surname) != null) {
            hashSetToUpdate = info.get(surname);
        }
        hashSetToUpdate.add(p);
        this.info.put(surname, hashSetToUpdate);
    }

    ////////////
    public HashSet<Integer> findPhoneNumberBySurname(String surname){
        HashSet<Integer> hs = new HashSet<>();
        for (Person p : this.info.get(surname)){
            hs.add(p.getPhoneNumber());
        }
        return hs;
    }
    public HashSet<String> findEmailBySurname(String surname){
        HashSet<String> hs = new HashSet<>();
        for (Person p : this.info.get(surname)){
            hs.add(p.getEmail());
        }
        return hs;
    }
    public void getAll(){
        for (Map.Entry<String, HashSet<Person>> entry: this.info.entrySet()) {
            System.out.println("keyword: "+ entry.getKey());
            for (Person p: entry.getValue() ) {
                System.out.println("       " + entry.getKey() + ", Person: "+ p.getSurname() + ", phoneNumber: " + p.getPhoneNumber() + ", email: " + p.getEmail());
            }
        }

    }
}
