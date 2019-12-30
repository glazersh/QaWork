package system1;

import org.junit.Before;
import org.junit.Test;
import system.*;

import static org.junit.Assert.*;

public class SpaceTest {

    private Leaf file;

    @Before
    public void initialize() throws NullPointerException {
        FileSystem.fileStorage=new Space(5);
    }

    @Test
    public void checkCountFreeSpace() throws OutOfSpaceException {
        file = new Leaf("file1",5);
        file.parent=new Tree("file");
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
        FileSystem.fileStorage.Dealloc(file);
        assertEquals(0, tree.children.size());
    }

    @Test
    public void checkGetAlloc() {
        Space space = new Space(5);
        assertEquals(5, space.getAlloc().length);
    }
}