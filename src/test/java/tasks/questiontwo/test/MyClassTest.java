package tasks.questiontwo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    private MyClass myClass;
    private List stringValueList;
    private List longValueList;
    private LocalDateTime nowMinusHour = LocalDateTime.now().minusHours(1);
    @Before
    public void setup(){
        longValueList = new ArrayList<Long>(){{
            add(10l);
            add(11l);
            add(12l);
            add(13l);
            add(14l);
        }};
        stringValueList = new ArrayList<String>(){{
            add("alex test 1");
            add("alex test 2");
            add("alex test 3");
            add("alex test 4");
            add("alex test 4");
        }};
        myClass = new MyClass(nowMinusHour, "alex", longValueList, stringValueList);
    }

    @Test
    public void testRemovalOfAllStringValues(){
        myClass.removeAllStringOccurrences("alex test 4");
        assertFalse(myClass.getListOfStrings().contains("alex test 4"));
    }

    @Test
    public void testRemovalOfOnlyOneStringValues(){
        myClass.removeFirstStringOccurrence("alex test 4");
        assertTrue(myClass.getListOfStrings().contains("alex test 4"));
    }


    @Test
    public void testLongContainsInList(){
        assertTrue(myClass.containsNumber(11L));
    }

    @Test
    public void testLongIsNotContainsInList(){
        assertFalse(myClass.containsNumber(16L));
    }

    @Test
    public void testToString(){
        assertEquals("alex 10 11 12 13 14", myClass.toString());
    }

    @Test
    public void testIsHistoricalFalse(){
        myClass.setTime(LocalDateTime.now().plusHours(2));
        assertFalse(myClass.isHistoric());
    }

    @Test
    public void testIsHistoricalTrue(){
        assertTrue(myClass.isHistoric());
    }

    @Test
    public void testEqualsObjects(){
        MyClass myClassNew = new MyClass(nowMinusHour, "alex", longValueList, stringValueList);
        assertEquals(myClassNew, myClass);
    }

    @Test
    public void testNotEqualsObjects(){
        List stringValueList = new ArrayList<String>(){{
            add("alex test 2");
            add("alex test 3");
            add("alex test 4");
            add("alex test 4");
        }};
        LocalDateTime nowMinusHour = LocalDateTime.now().minusHours(1);
        MyClass myClassNew = new MyClass(nowMinusHour, "alex", longValueList, stringValueList);
        assertNotEquals(myClassNew, myClass);
    }

}
