package HomeTask3;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //1.Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
        String words[] = {"apple", "phone", "grape", "apple", "job", "offer",
                "vacancy", "job", "salary", "java", "SQL", "mouse", "keyboard", "action",
        "vacancy", "developer", "system", "analyst", "senior", "english", "company", "Yandex", "Google", "middle", "senior",
        "car", "juice", "senior"};
        System.out.println("Исходный список со словами такой:");
        System.out.println(Arrays.toString(words));

        //Найти список слов, из которых состоит текст (дубликаты не считать);
        //Посчитать сколько раз встречается каждое слово (использовать HashMap);
        HashMap<String, Integer> hm = new HashMap<>();
        for (String w: words) {
            if (hm.containsKey(w)){
                hm.put(w, hm.get(w)+1);
            }
            else{
                hm.put(w, 1);
            }
        }

        System.out.println("В списке встречаются следующие слова:");
        for (Map.Entry<String, Integer> k: hm.entrySet()){
            System.out.print(k.getKey()+ " " );
        }
        System.out.println();
        System.out.println("Статистика по словам: ");
        for (Map.Entry<String, Integer> k: hm.entrySet()){
            System.out.println(k.getKey() + " : " + k.getValue());
        }
        //Написать простой класс PhoneBook(внутри использовать HashMap):
        //В качестве ключа использовать фамилию
        //В каждой записи всего два поля: phone, e-mail
        //Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов), и отдельный метод для поиска e-mail по фамилии.
        Person p1 = new Person("Ivanov Vasya", 1234, "iivanov@mail.ru");
        Person p2 = new Person("Petrov", 67, "apetrov@mail.ru");
        Person p3 = new Person("Sidorov", 34, "vsidorov@mail.ru");
        Person p4 = new Person("Taekwon Kim", 10, "tkim@mail.ru");
        Person p5 = new Person("Hvan", 11, "nhvan@mail.ru");
        Person p6 = new Person("Taeil Pak", 12, "tpak@mail.ru");
        Person p7 = new Person("Yuliy Kim", 13, "ykim@mail.ru");
        Person p8 = new Person("Raymond Pak", 14, "rpak@mail.ru");
        Person p9 = new Person("Ivanov Vasya", 1234, "iivanov@mail.ru"); //дубль p1

        PhoneBook ph1 = new PhoneBook();
        ph1.add("Ivanov", p1);
        ph1.add("Petrov", p2);
        ph1.add("Sidorov", p3);
        ph1.add("Kim", p4);
        ph1.add("Hvan", p5);
        ph1.add("Pak", p6);
        ph1.add("Kim", p7);
        ph1.add("Pak", p8);

        ph1.add("Ivanov", p9);

        System.out.println(ph1.findPhoneNumberBySurname("Ivanov"));
        System.out.println(ph1.findEmailBySurname("Ivanov"));
        ph1.getAll();
    }
}