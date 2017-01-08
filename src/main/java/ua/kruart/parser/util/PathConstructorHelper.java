package ua.kruart.parser.util;

import java.io.File;

/**
 * Contains functionality for construct a path
 *
 * @author admin
 */
public class PathConstructorHelper {

    public static File constructPath(String destination, String folderNameByAttr) {
        String newDest = destination + File.separator + folderNameByAttr;

        while (isDirectoryOrFileExists(newDest)) {
            newDest = changeName(newDest, FileSystemMarker.FOLDER);
        }

        return new File(newDest);
    }

    public static File addImageNameToPath(String path, String link) {
        String imageName = cutLinkFromString(link);

        String newDest = path + File.separator + imageName;

        while (isDirectoryOrFileExists(newDest)) {
            newDest = changeName(newDest, FileSystemMarker.FILE);
        }

        return new File(newDest);
    }

    private static String cutLinkFromString(String link) {
        String imageName = link.substring(link.lastIndexOf("/") + 1);
        int parametersIndexOf = imageName.lastIndexOf("?");

        if (parametersIndexOf != -1) {          //if parameters exists
            imageName = imageName.substring(0, parametersIndexOf);//cuts parameters
        }
        return imageName;
    }

    private static boolean isDirectoryOrFileExists(String path) {
        return new File(path).exists();
    }

    private static String changeName(String pathName, FileSystemMarker marker) {
        if (marker == FileSystemMarker.FOLDER) {
            return countingNameDuplicate(pathName);
        } else {
            String imageName = pathName.substring(0, pathName.lastIndexOf("."));
            return countingNameDuplicate(imageName) + pathName.substring(pathName.lastIndexOf(".", pathName.length()));
        }
    }

    private static String countingNameDuplicate(String pathName) {
        boolean lastLetterIsRoundBrace = pathName.lastIndexOf(")") == pathName.length() - 1;   //если последний символ - скобка

        if (!lastLetterIsRoundBrace) {
            pathName += "(1)";
        } else {
            int numberOfDuplicate = Integer.parseInt(pathName.substring(pathName.lastIndexOf("(") + 1, pathName.length() - 1));   //получаем число из скобок
            pathName = pathName.substring(0, pathName.lastIndexOf("(") + 1) + (numberOfDuplicate + 1) + ")";
        }
        return pathName;
    }


    private enum FileSystemMarker {
        FILE,
        FOLDER
    }
}
