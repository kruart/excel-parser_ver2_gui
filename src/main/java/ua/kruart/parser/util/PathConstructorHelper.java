package ua.kruart.parser.util;

import java.io.File;

/**
 * Contains functionality for construct a path
 *
 * @author admin
 */
public class PathConstructorHelper {

    public static File constructPath(String destination, String folderNameByAttr, String link) {
        String imageName = link.substring(link.lastIndexOf("/") + 1);
        int parametersIndexOf = imageName.lastIndexOf("?"); //cuts parameters

        if (parametersIndexOf != -1) {
            imageName = imageName.substring(0, parametersIndexOf);
        }

        String newDest = destination + File.separator + folderNameByAttr;

        while (isDirectoryExists(newDest)) {
            newDest = isFirstDuplicate(newDest);
        }

        return new File(newDest + File.separator + imageName);
    }

    private static String isFirstDuplicate(String newDest) {
        boolean lastLetterIsRoundBrace = newDest.lastIndexOf(")") == newDest.length() - 1;   //если последняя скобка

        if (!lastLetterIsRoundBrace) {
            newDest += "(1)";
        } else {
            int numberOfDuplicate = Integer.parseInt(newDest.substring(newDest.lastIndexOf("(") + 1, newDest.length() - 1));   //получаем число из скобок
            newDest = newDest.substring(0, newDest.lastIndexOf("(") + 1) + (numberOfDuplicate + 1) + ")";
        }
        return newDest;
    }


    private static boolean isDirectoryExists(String path) {
        return new File(path).exists();
    }
}
