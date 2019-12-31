package system;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.DirectoryNotEmptyException;

import static org.junit.Assert.*;

public class FileSystemTest {

    private FileSystem fileSystem;
    private String[] rootName = {"root"};
    private String[] file1 = {"root", "file1"};
    private String[] file2 = {"root", "file2"};
    private String[] file3 = {"root", "file3"};
    private String[] file1extra = {"root", "dir1"};
    private String[] wrongFile1 = {"root", "file2"};
    private String[] directory1 = {"root", "dir1", "dir2"};
    private String[] directory2 = {"root", "dir3", "dir4"};
    private String[] deleteDirectory = {"root", "dir1"};
    private String[] wrongDirectory1 = {"root", "dir3", "dir4"};
    private String[] notRootName = {"noroot"};
    private int numberOfStorage;

    @Before
    public void initialize(){
        numberOfStorage = 5;
        fileSystem = new FileSystem(numberOfStorage);
    }

    @Test (expected = BadFileNameException.class)
    public void dirFunctionBadFileNameException() throws BadFileNameException{
        fileSystem.dir(notRootName);
    }

    @Test (expected = BadFileNameException.class)
    public void dirFunctionDuplicateFileNameAndDirectory() throws BadFileNameException, OutOfSpaceException {
        fileSystem.file(file1,3);
        fileSystem.dir(file1);
    }

    /* function lsdir */

    @Test
    public void lsdirFunction2() throws BadFileNameException {
        fileSystem.dir(directory1);
        fileSystem.dir(directory2);
        String [] lsdir = fileSystem.lsdir(rootName);
        assertEquals("dir1", lsdir[0]);
        assertEquals("dir3", lsdir[1]);
    }

    @Test
    public void lsdirFunctionNull() throws BadFileNameException{
        fileSystem.dir(directory1);
        assertNull(fileSystem.lsdir(wrongDirectory1));
    }

    /* Remove directory - function rmdir */
    @Test (expected = DirectoryNotEmptyException.class)
    public void rmdirFunctionDirectoryNotEmptyException() throws BadFileNameException, DirectoryNotEmptyException {
        fileSystem.dir(directory1);
        fileSystem.rmdir(deleteDirectory);
    }

    @Test
    public void rmdirFunction() throws BadFileNameException, DirectoryNotEmptyException {
        fileSystem.dir(directory1);
        fileSystem.rmdir(directory1);
        assertNull(fileSystem.DirExists(directory1));
    }

    @Test
    public void rmdirFunctionNull() throws BadFileNameException, DirectoryNotEmptyException {
        fileSystem.dir(directory1);
        fileSystem.rmdir(file1);
    }

    /* Create new file - function file*/

    @Test(expected = OutOfSpaceException.class)
    public void fileFunctionOutOfSpaceException() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 6);
    }

    @Test(expected = BadFileNameException.class)
    public void fileFunctionBadFileNameException() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(notRootName, 1);
    }

    @Test
    public void fileFunctionNeedExtraSpace() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 3);
        fileSystem.file(file1, 3);
    }

    @Test(expected = BadFileNameException.class)
    public void fileFunctionBadFileNameExceptionDirName() throws OutOfSpaceException, BadFileNameException{
        fileSystem.file(file1, 3);
        fileSystem.dir(file1extra);
        fileSystem.file(file1extra, 2);
    }

    @Test
    public void fileExistsName() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1,3);
        assertEquals("file1",fileSystem.FileExists(file1).getPath()[1]);
    }

    /* Check Disk - function disk */
    @Test
    public void diskFunction() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 2);
        fileSystem.file(file2, 2);
        fileSystem.file(file3, 1);
        fileSystem.rmfile(file2);
        String[][] disk = fileSystem.disk();
        for (int i=0;i<disk.length;i++) {
            if(i!=2 && i!=3){
                assertNotNull(disk[i]);
            }else{
                assertNull(disk[i]);
            }
        }
    }

    /* Remove file - function rmfile */

    @Test
    public void rmfileFunctionNull() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 3);
        fileSystem.rmfile(wrongFile1);
        assertNotNull(fileSystem.FileExists(file1));
    }

    @Test
    public void fileExists() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1,3);
        fileSystem.dir(directory1);
        assertNull(fileSystem.DirExists(file1));
    }
}