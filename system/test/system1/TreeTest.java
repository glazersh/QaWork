package system1;

import org.junit.Before;
import org.junit.Test;
import system.Tree;

import java.util.Set;

import static org.junit.Assert.*;

public class TreeTest {

    private Tree tree;

    @Before
    public void initialize(){
        tree = new Tree("root");
    }

    @Test
    public void getChildByName3() {
        Tree newTree = tree.GetChildByName("dir1");
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