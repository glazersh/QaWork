package system1;

import org.junit.Before;
import org.junit.Test;
import system.Leaf;
import system.OutOfSpaceException;
import system.Space;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SpaceTest {

    Space space;
    Leaf[] blocks;
    LinkedList<Integer> freeBlocks;
    Leaf file;



    @Before
    public void initialize() throws OutOfSpaceException, NullPointerException {
        space = new Space(5);
        assertNotNull(new Leaf("file", 5));
    }




    @Test
    public void checkAlloc() throws OutOfSpaceException {

        space.Alloc(5, file);
    }
    //exception?
    @Test
    public void checkDealloc(){

        space.Dealloc(file);
        assertEquals(0,space.countFreeSpace());
    }

    @Test
    public void checkCountFreeSpace() throws OutOfSpaceException {
        space.Dealloc(file);
        space.Alloc(5, file);
        assertEquals(5,space.countFreeSpace());

    }



}