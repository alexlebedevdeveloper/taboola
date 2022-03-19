package tasks.questiontwo.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MyClass
{
    private LocalDateTime time; // needs to be final, however i want to test it, so i will leave open
    private final String name;
    private List<Long> listOfNumbers;
    private List<String> listOfStrings;

    public MyClass(LocalDateTime time, String name, List<Long> numbers, List<String> strings) {
        this.time = time;
        this.name = name;
        listOfNumbers = numbers;
        listOfStrings = strings;
    }

    @Override
    public boolean equals(Object o) {
        // Here is a question what we declare of an equals objects.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return Objects.equals(time, myClass.time) &&
                Objects.equals(name, myClass.name) &&
                Objects.equals(listOfNumbers, myClass.listOfNumbers) &&
                Objects.equals(listOfStrings, myClass.listOfStrings);
    }


    @Override
    public int hashCode() {
        return Objects.hash(time, name, listOfNumbers, listOfStrings);
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(name);
        for (long item : listOfNumbers) {
            out.append(" ");
            out.append(item);
        }
        return out.toString();
    }

    public void removeAllStringOccurrences(String str) {
        // Performance: A for loop through an array is extremely lightweight both in terms of heap and CPU usage.
        // If raw speed and memory thriftiness is a priority, using a stream is worse.
        // Here can be used filter and then findAny, however it will be anyway less sufficient
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).equals(str)) {
                listOfStrings.remove(i);
                i--;
            }
        }
    }

    public void removeFirstStringOccurrence(String str) {
        // Performance: A for loop through an array is extremely lightweight both in terms of heap and CPU usage.
        // If raw speed and memory thriftiness is a priority, using a stream is worse.
        // Here can be used filter and then findAny, however it will be anyway less sufficient
        for (int i = 0; i < listOfStrings.size() - 1; i++) {
            if (listOfStrings.get(i).equals(str)) {
                listOfStrings.remove(i);
                return;
            }
        }
    }

    public boolean containsNumber(long number) {
        // Performance: A for loop through an array is extremely lightweight both in terms of heap and CPU usage.
        // If raw speed and memory thriftiness is a priority, using a stream is worse.
        // Here can be used filter and then findAny, however it will be anyway less sufficient
        for (long num : listOfNumbers) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isHistoric() {
        //swapped to more up-to-date library - java.time
        return time.isBefore(LocalDateTime.now());
    }

    public List<Long> getListOfNumbers() {
        return listOfNumbers;
    }

    public List<String> getListOfStrings() {
        return listOfStrings;
    }

    // For test only
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
