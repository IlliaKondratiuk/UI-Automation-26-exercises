//This class contains methods for managing file checks.

package utils;

import java.io.File;

public class FileTestUtil {

    public static boolean isFileDownloaded(String downloadDir, String fileName) {
        File dir = new File(downloadDir);
        File[] dirContents = dir.listFiles();

        if (dirContents != null) {
            for (File file : dirContents) {
                if (file.getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
