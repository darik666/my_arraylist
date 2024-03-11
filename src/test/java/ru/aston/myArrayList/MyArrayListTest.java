package test.java.ru.aston.myArrayList;

import main.java.ru.aston.myArrayList.MyArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MyArrayListTest is a JUnit test class for the MyArrayList implementation,
 * covering various scenarios and methods.
 */
class MyArrayListTest {

    private MyArrayList<String> stringList;
    private MyArrayList<Person> personList;

    /**
     * Initial setup for each test
     */
    @BeforeEach
    void setUp() {
        stringList = new MyArrayList<>();
        personList = new MyArrayList<>();
    }


    /**
     * Cleaning resources after each test.
     */
    @AfterEach
    void tearDown() {
        stringList = null;
        personList = null;
    }

    /**
     * Adds eleven strings to the list.
     */
    public void addElevenString() {
        stringList.add("n");
        stringList.add("a");
        stringList.add("u");
        stringList.add("v");
        stringList.add("g");
        stringList.add("h");
        stringList.add("y");
        stringList.add("w");
        stringList.add("f");
        stringList.add("c");
        stringList.add("s");
    }

    /**
     * Tests the constructor with a negative initial capacity, ensuring it throws
     * an IllegalArgumentException.
     */
    @Test
    void testConstructorWithInitialCapacity() {
        int negativeCapacity = -5;

        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(negativeCapacity));
    }

    /**
     * Tests adding strings to the list.
     */
    @Test
    void testAddString() {
        addElevenString();

        assertEquals(stringList.size(), 11);
        assertEquals("n", stringList.get(0));
        assertEquals("a", stringList.get(1));
    }

    /**
     * Tests adding and getting Persons from the list.
     */
    @Test
    void testAddAndGetPersons() {
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);

        personList.add(person1);
        personList.add(person2);

        assertEquals(person1, personList.get(0));
        assertEquals(person2, personList.get(1));
    }

    /**
     * Tests adding elements to specific positions with invalid values.
     */
    @Test
    void testAddToIndexInvalidValues() {
        addElevenString();
        stringList.add(2, "piano");

        assertEquals("piano", stringList.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> stringList.add(33, "guitar"));
        assertThrows(IndexOutOfBoundsException.class, () -> stringList.add(-33, "guitar"));
    }

    /**
     * Tests setting an element at a given index.
     */
    @Test
    void testSet() {
        stringList.add("axe");
        stringList.add("bass");

        assertEquals("axe", stringList.set(0, "oak"));
        assertEquals("oak", stringList.get(0));
    }

    /**
     * Tests removing an element from the list.
     */
    @Test
    void testRemove() {
        stringList.add("axe");
        stringList.add("bass");

        assertEquals("axe", stringList.remove(0));
        assertEquals(1, stringList.size());
        assertEquals("bass", stringList.get(0));
    }

    /**
     * Tests clearing the list.
     */
    @Test
    void testClear() {
        stringList.add("axe");
        stringList.add("bass");

        stringList.clear();

        assertEquals(0, stringList.size());
        assertNull(stringList.get(0));
    }

    /**
     * Tests sorting strings in the list.
     */
    @Test
    void testSortStrings() {
        stringList.add("oak");
        stringList.add("axe");
        stringList.add("bass");
        addElevenString();

        stringList.sort();
        System.out.println(stringList);

        assertEquals("a", stringList.get(0));
        assertEquals("axe", stringList.get(1));
        assertEquals("bass", stringList.get(2));
    }

    /**
     * Tests sorting Persons in the list.
     */
    @Test
    void testSortPersons() {
        personList = new MyArrayList<>(10);
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        Person person3 = new Person("Bob", 22);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        personList.sort(ageComparator);

        assertEquals(person3, personList.get(0));
        assertEquals(person1, personList.get(1));
        assertEquals(person2, personList.get(2));
    }

    /**
     * Tests quicksort for Strings.
     */
    @Test
    void testQuicksortStrings() {
        stringList.add("oak");
        stringList.add("axe");
        stringList.add("bass");
        addElevenString();
        stringList.add("vzzz");

        stringList.quicksort();

        assertEquals("a", stringList.get(0));
        assertEquals("axe", stringList.get(1));
        assertEquals("bass", stringList.get(2));
    }

    /**
     * Tests quicksort for Persons.
     */
    @Test
    void testQuicksortPersons() {
        Person person1 = new Person("John", 12);
        Person person2 = new Person("Jane", 30);
        Person person3 = new Person("Bob", 22);
        Person person4 = new Person("Andrew", 5);
        Person person5 = new Person("Peter", 41);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        personList.quicksort(ageComparator);

        assertEquals(person4.getAge(), personList.get(0).age);
        assertEquals(person1, personList.get(1));
        assertEquals(person3, personList.get(2));
    }

    /**
     * Represents a Person with a name and age for testing purposes.
     */
    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{name, age});
        }
    }
}