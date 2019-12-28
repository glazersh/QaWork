package system1;

import org.junit.Before;
import org.junit.Test;
import system.*;

import static org.junit.Assert.*;

public class LeafTest {
    Leaf leaf;

    @Before
    public void initialize (){
        FileSystem.fileStorage = new Space(5);
    }

    @Test
    public void newLeaf () throws OutOfSpaceException {
        assertEquals(5, FileSystem.fileStorage.countFreeSpace());
        leaf = new Leaf("file1",2);
        assertEquals(3, FileSystem.fileStorage.countFreeSpace());
    }
}