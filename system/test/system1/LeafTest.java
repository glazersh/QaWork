package system1;

import org.junit.Before;
import org.junit.Test;
import system.FileSystem;
import system.Leaf;
import system.OutOfSpaceException;

import static org.junit.Assert.*;

public class LeafTest {
    Leaf leaf;

    @Before
    public void initialize () throws OutOfSpaceException {
        leaf = new Leaf("file1",5);
        assertNotNull(leaf);
    }


    @Test
    public void initialize1 () throws OutOfSpaceException {
        assertNotNull(leaf);
    }





}