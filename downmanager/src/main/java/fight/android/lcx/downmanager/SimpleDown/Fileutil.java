package fight.android.lcx.downmanager.SimpleDown;

import java.io.File;
import java.io.IOException;

/**
 * Created by lichenxi on 2017/10/11.
 */

public class Fileutil {
    private final static String FILENAME_SEQUENCE_SEPARATOR = "-";
    public   static  File  handleFileName(String fullFilename) throws IOException {
        File file=new File(fullFilename);
        if(!file.exists()) {
            file.createNewFile();
            return file;
        }
      //存在的操作
        String fileNamePath=file.getAbsolutePath();
        String filename = fileNamePath.substring(0, fileNamePath.lastIndexOf("."));
        String extension = fileNamePath.substring(fileNamePath.lastIndexOf("."), fileNamePath.length());
        int index=1;
        while (file.exists()){
            file=new File(filename+FILENAME_SEQUENCE_SEPARATOR+index+extension);
            index++;
        }
        file.createNewFile();
        return file;
    }
}

