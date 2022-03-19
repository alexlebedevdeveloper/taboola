package tasks.questionone;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @InjectMocks
    private Main main;

    @Test
    public void testNotFailingMainWithEntry() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("i = 9\ri = i + 2\r".getBytes());
        System.setIn(input);
        main.main(null);
    }

    @Test
    public void testFailingMainWithoutEntry() throws IOException {
        main.main(null);
    }
}
