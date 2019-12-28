package system1;

import org.junit.Before;
import org.junit.Test;
import system.*;

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
        //space= FileSystem.fileStorage;
        //freeBlocks.add(5);


    }




    @Test
    public void checkAlloc() throws OutOfSpaceException {
        file = new Leaf("file1",1);
        FileSystem.fileStorage.Alloc(2,file );
        //space.countFreeSpace();
        assertEquals(2,FileSystem.fileStorage.countFreeSpace());
    }
    //exception?
    @Test
    public void checkDealloc()throws OutOfSpaceException{
        file = new Leaf("file1",5);
        Tree tree = new Tree("file");
        file.parent=tree;
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(5,FileSystem.fileStorage.countFreeSpace());
    }

    @Test
    public void checkCountFreeSpace() throws OutOfSpaceException {
        file = new Leaf("file1",5);
        Tree tree = new Tree("file");
        file.parent=tree;
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(5,FileSystem.fileStorage.countFreeSpace());
        FileSystem.fileStorage.Alloc(5, file);
        assertEquals(0,FileSystem.fileStorage.countFreeSpace());


    }

    @Test
    public void checkGetAlloc() throws OutOfSpaceException {
        file = new Leaf("file1",5);
        Space space = new Space(5);
        space.Alloc(2, file);

        //FileSystem.fileStorage.Alloc(2, file);
        assertEquals(5,space.getAlloc().length);


    }



}