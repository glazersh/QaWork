package system1;

import org.junit.Before;
import org.junit.Test;
import system.*;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SpaceTest {

    Leaf file;

    @Before
    public void initialize() throws NullPointerException {
        FileSystem.fileStorage=new Space(5);
    }


    @Test
    public void checkAlloc() throws OutOfSpaceException {
        file = new Leaf("file1",1);
        FileSystem.fileStorage.Alloc(2,file );
        assertEquals(2,FileSystem.fileStorage.countFreeSpace());
    }

    @Test(expected = NullPointerException.class)
    public void checkAllocNullPointerException() throws OutOfSpaceException, NullPointerException {
        FileSystem.fileStorage.Alloc(6,file );
    }

    @Test
    public void checkDealloc()throws OutOfSpaceException{
        file = new Leaf("file1",5);
        Tree tree = new Tree("file");
        file.parent=tree;
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(5,FileSystem.fileStorage.countFreeSpace());
    }

    @Test(expected = NullPointerException.class)
    public void checkDeallocNullPointer()throws OutOfSpaceException, NullPointerException{
        file = new Leaf("file1",5);
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

    @Test(expected = NullPointerException.class)
    public void checkCountFreeSpaceNullPointer() throws OutOfSpaceException,NullPointerException {
        file = new Leaf("file1",5);
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(5,FileSystem.fileStorage.countFreeSpace());
        FileSystem.fileStorage.Alloc(5, file);
        assertEquals(0,FileSystem.fileStorage.countFreeSpace());
    }

    @Test
    public void checkChildren() throws OutOfSpaceException{
        file = new Leaf("file1",2);
        Tree tree = new Tree("file");
        file.parent=tree;
        tree.children.put("file1", file);
        assertEquals(1, tree.children.size());
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(0, tree.children.size());
    }

    @Test
    public void checkGetAlloc() throws OutOfSpaceException {
        file = new Leaf("file1",5);
        Space space = new Space(5);
        space.Alloc(2, file);
        assertEquals(5,space.getAlloc().length);
    }
}