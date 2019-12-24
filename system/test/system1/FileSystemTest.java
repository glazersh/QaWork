package system1;

import org.junit.Before;
import org.junit.Test;
import system.BadFileNameException;
import system.FileSystem;
import system.OutOfSpaceException;

import java.nio.file.DirectoryNotEmptyException;

import static org.junit.Assert.*;

public class FileSystemTest {

    FileSystem fileSystem;
    String[] rootName = {"root"};
    String[] file1 = {"root", "file1"};
    String[] file2 = {"root", "file2"};
    String[] file3 = {"root", "file3"};
    String[] file1extra = {"root", "dir1"};
    String[] wrongFile1 = {"root", "file2"};
    String[] directory1 = {"root", "dir1", "dir2"};
    String[] deleteDirectory = {"root", "dir1"};
    String[] wrongDirectory1 = {"root", "dir3", "dir4"};
    String[] notRootName = {"noroot"};
    int numberOfStorage;

    @Before
    public void initialize(){
        numberOfStorage = 5;
        fileSystem = new FileSystem(numberOfStorage);
    }

    /* Create new directory - function dir */
    @Test
    public void dirFunction() throws BadFileNameException {
        fileSystem.dir(directory1);
    }

    @Test (expected = BadFileNameException.class)
    public void dirFunctionBadFileNameException() throws BadFileNameException{
        fileSystem.dir(notRootName);
    }

    /* function lsdir */
    @Test
    public void lsdirFunction() throws BadFileNameException {
        fileSystem.dir(directory1);
        String [] lsdir = fileSystem.lsdir(rootName);
        assertEquals("dir1", lsdir[0]);
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
        assertNotNull(fileSystem.DirExists(directory1));
    }

    /* Create new file - function file*/
    @Test
    public void fileFunction() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 1);
    }

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
    public void fileFunctionBadFileNameException2() throws OutOfSpaceException, BadFileNameException{
        fileSystem.file(file1, 3);
        fileSystem.dir(file1extra);
        fileSystem.file(file1extra, 2);
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
    public void rmfileFunction() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 3);
        fileSystem.rmfile(file1);
        assertNull(fileSystem.FileExists(file1));
    }

    @Test
    public void rmfileFunctionNull() throws OutOfSpaceException, BadFileNameException {
        fileSystem.file(file1, 3);
        fileSystem.rmfile(wrongFile1);
        assertNotNull(fileSystem.FileExists(file1));
    }
}