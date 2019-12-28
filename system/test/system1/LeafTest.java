package system1;

import org.junit.Before;
import org.junit.Test;
import system.Leaf;
import system.OutOfSpaceException;

import static org.junit.Assert.*;

public class LeafTest {
    Leaf leaf;

    @Before
    public void checkLeaf () throws OutOfSpaceException {
    leaf= new Leaf("file", 5);
    assertNotNull(leaf);
    }




}