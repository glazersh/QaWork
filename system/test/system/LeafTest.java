package system;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeafTest {
    private Leaf leaf;

    @Before
    public void initialize (){
        FileSystem.fileStorage = new Space(5);
    }

    @Test
    public void checkLeafAndStorage () throws OutOfSpaceException {
        assertEquals(5, FileSystem.fileStorage.countFreeSpace());
        leaf = new Leaf("file1",2);
        assertEquals(3, FileSystem.fileStorage.countFreeSpace());
    }
}