package system1;

import org.junit.Before;
import org.junit.Test;
import system.Space;

import static org.junit.Assert.*;

public class SpaceTest {

    Space space;

    @Before
    public void initialize(){
        space = new Space(5);
    }

    @Test
    public void checkSpace()  {
        assertEquals(5, space.countFreeSpace());
    }
}