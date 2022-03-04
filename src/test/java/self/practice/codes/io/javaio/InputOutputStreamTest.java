package self.practice.codes.io.javaio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;


public class InputOutputStreamTest {

    @Test
    void readFile() {
        InputOutputStream mock = Mockito.mock(InputOutputStream.class);
        Mockito.when(mock.testMockito(10)).thenReturn(-1);
        assertEquals(-1, mock.testMockito(10));
        assertEquals(1000, mock.testMockito(100));
    }

    @Test
    void classPath() {
    }
}