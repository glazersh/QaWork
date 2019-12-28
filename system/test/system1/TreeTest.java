package system1;

import org.junit.Before;
import org.junit.Test;
import system.Tree;

public class TreeTest {

    Tree tree;

    @Before
    public void initialize(){
        tree = new Tree("root");
    }

    @Test
    public void getChildByName() {
        tree.GetChildByName("dir1");
        tree.GetChildByName("dir1");
    }
}