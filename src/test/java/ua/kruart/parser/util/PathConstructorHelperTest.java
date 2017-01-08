package ua.kruart.parser.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

/**
 * Contain unit-tests for {@link PathConstructorHelper}
 *
 * @author kruart
 */
@RunWith(PowerMockRunner.class) //указывает Junit, чтобы перед или во время запусков тестов использовался специальный класс PowerMockRunner
@PrepareForTest(PathConstructorHelper.class) //указывает PowerMock, что указанный параметром класс будет мокироваться
public class PathConstructorHelperTest {
    private String DESTINATION = "src/test/resources/excel";

    @Test
    public void testConstructPath() {
        String expectedFolderPath = DESTINATION + File.separator + "FUJI2NOOR";
        File actualFolderPathAfterConstruct = PathConstructorHelper.constructPath(DESTINATION, "FUJI2NOOR");

        assertEquals(new File(expectedFolderPath), actualFolderPathAfterConstruct);
    }

    @Test
    public void testConstructPathWhenSuchFolderAlreadyExists() throws Exception {
        spy(PathConstructorHelper.class);

        when(PathConstructorHelper.class, method(PathConstructorHelper.class, "isDirectoryOrFileExists", String.class))
                .withArguments(anyString())
                .thenReturn(true).thenReturn(true).thenReturn(false);

        String expectedFolderPath = DESTINATION + File.separator + "FUJI2NOOR(2)";
        File actualFolderPathAfterConstruct = PathConstructorHelper.constructPath(DESTINATION, "FUJI2NOOR");

        assertEquals(new File(expectedFolderPath), actualFolderPathAfterConstruct);
    }

    @Test
    public void testAddImageNameToPath() {
        String expectedPathToFile = DESTINATION + File.separator + "FUJI2NOOR" + File.separator + "100002.jpg";
        String folderPath = DESTINATION + File.separator + "FUJI2NOOR";
        File actualPathAfterAddImageNameToPath = PathConstructorHelper.addImageNameToPath(folderPath, "http://deltaplus-cei.com/img/b/10/00/100002.jpg");

        assertEquals(new File(expectedPathToFile), actualPathAfterAddImageNameToPath);
    }

    @Test
    public void testAddImageNameToPathAndCutLinkWithParameters() {
        String expectedPathToFile = DESTINATION + File.separator + "FUJI2NOOR" + File.separator + "100002.jpg";
        String folderPath = DESTINATION + File.separator + "FUJI2NOOR";
        File actualPathAfterAddImageNameToPath = PathConstructorHelper.addImageNameToPath(folderPath, "http://deltaplus-cei.com/img/b/10/00/100002.jpg?authuser=0#en/ua/testing%20its");

        assertEquals(new File(expectedPathToFile), actualPathAfterAddImageNameToPath);
    }

    @Test
    public void testAddImageNameToPathWhenSuchNameAlreadyExists() throws Exception {
        spy(PathConstructorHelper.class);

        when(PathConstructorHelper.class, method(PathConstructorHelper.class, "isDirectoryOrFileExists", String.class))
                .withArguments(anyString())
                .thenReturn(true).thenReturn(true).thenReturn(false);

        String expectedPathToFile = DESTINATION + File.separator + "FUJI2NOOR" + File.separator + "100002(2).jpg";
        String folderPath = DESTINATION + File.separator + "FUJI2NOOR";
        File actualPathAfterConstruct = PathConstructorHelper.addImageNameToPath(folderPath, "http://deltaplus-cei.com/img/b/10/00/100002.jpg");

        assertEquals(new File(expectedPathToFile), actualPathAfterConstruct);
    }
}
