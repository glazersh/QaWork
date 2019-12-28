package system1;

import org.junit.Before;
import org.junit.Test;
import system.Tree;

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
    }

}