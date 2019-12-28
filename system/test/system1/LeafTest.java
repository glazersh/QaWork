package system1;

import org.junit.Before;
import org.junit.Test;
import system.FileSystem;
import system.Leaf;
import system.OutOfSpaceException;
import system.Space;

import static org.junit.Assert.*;

public class LeafTest {
    Leaf leaf;

    @Before
    public void initialize (){
        FileSystem.fileStorage = new Space(5);
    }

    @Test
    public void newLeaf () throws OutOfSpaceException {
        leaf = new Leaf("file1",4);
    }
}