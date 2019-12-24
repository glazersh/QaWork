package system;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileSystemTest {

    FileSystem fileSystem;
    FileSystem fileSystem2;
    String [] correctPath;
    String [] correctPath2;
    String [] wrongPath;
    String [] withOutRootPath;

    @Before
    public void initialize(){
        fileSystem = new FileSystem(5);
        fileSystem2 = new FileSystem(5);
        correctPath = new String[]{"root", "dir1", "file1"};
        correctPath2 = new String[]{"root", "dir11", "file2"};
        wrongPath = new String[]{"root", "dir1", "dir2", "file1"};
        withOutRootPath = new String[]{"dir1", "dir2", "file1"};
    }

    @Test
    public void checkPath() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(correctPath, 3);
        assertNotNull(fileSystem.FileExists(correctPath));
        assertNull(fileSystem.FileExists(wrongPath));
        assertEquals(2,FileSystem.fileStorage.countFreeSpace());
        assertNotEquals(5,FileSystem.fileStorage.countFreeSpace());
    }

    @Test(expected = BadFileNameException.class)
    public void checkFilePathWithOutRoot() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(withOutRootPath,3);
    }

    @Test(expected = OutOfSpaceException.class)
    public void checkFileOutOfSpace() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(wrongPath,10);
    }

    @Test
    public void check() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(correctPath, 3);
        fileSystem2.file(correctPath2, 3);
        String [] tmp = fileSystem.lsdir(new String[]{"root"});
        int x=4;
    }


}