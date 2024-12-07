import org.example.Person;
import org.example.Subdivision;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonCreation() {
        Subdivision subdivision = new Subdivision("123", "IT");
        Person person = new Person(1, "Alice", "Female", subdivision, 5000.0, "12.05.1990");

        assertEquals(1, person.getId());
        assertEquals("Alice", person.getName());
        assertEquals("Female", person.getGender());
        assertEquals("IT", person.getSubdivision().getName());
        assertEquals(5000.0, person.getSalary());
        assertEquals("12.05.1990", person.getBirthDate());
    }

    @Test
    void testToString() {
        Subdivision subdivision = new Subdivision("123", "HR");
        Person person = new Person(2, "Bob", "Male", subdivision, 4000.0, "01.01.1980");

        String expected = "Person{id=2, name='Bob', gender='Male', subdivision=Subdivision{id='123', name='HR'}, salary=4000,00, birthDate='01.01.1980'}";
        assertEquals(expected, person.toString());
    }
}
