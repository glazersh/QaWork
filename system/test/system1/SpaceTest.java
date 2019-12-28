package system1;

import org.junit.Before;
import org.junit.Test;
import system.FileSystem;
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
        FileSystem.fileStorage=new Space(5);
        space= FileSystem.fileStorage;

    }




    @Test
    public void checkAlloc() throws OutOfSpaceException {
        file = new Leaf("file1",3);
        space.Alloc(2,file );
        //space.countFreeSpace();
        assertEquals(0,space.countFreeSpace());
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