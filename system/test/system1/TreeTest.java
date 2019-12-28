package system1;

import org.junit.Before;
import org.junit.Test;
import system.Tree;

import java.util.Set;

import static org.junit.Assert.*;

public class TreeTest {

    Tree tree;

    @Before
    public void initialize(){
        tree = new Tree("root");
    }

    @Test
    public void getChildByName() {
        tree.GetChildByName("dir1");
        assertEquals(1,tree.children.size());
        tree.GetChildByName("dir1");
        assertEquals(1,tree.children.size());
    }

    @Test
    public void getChildByName2() {
        tree.GetChildByName("dir1");
        assertEquals(1,tree.children.size());
        tree.GetChildByName("dir2");
        assertEquals(2,tree.children.size());
    }

    @Test
    public void getChildByName3() {
        Tree newTree = tree.GetChildByName("dir1");
        newTree.GetChildByName("dir2");
        assertEquals(1,newTree.children.size());
        assertEquals(1,newTree.parent.children.size());
        assertEquals("dir1",newTree.getPath()[0]);
    }

    @Test
    public void getChildByName4(){
        String [] files = {"file1", "file2"};
        Tree newTree = tree.GetChildByName("dir1");
        newTree.GetChildByName(files[0]);
        newTree.GetChildByName(files[1]);
        Set<String> children = tree.GetChildByName("dir1").children.keySet();
        assertEquals(2, children.size());
    }

}