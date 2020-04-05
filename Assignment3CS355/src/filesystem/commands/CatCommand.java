/*
 * CatCommand - Reads file content at current directory
 *
 * Created - Sam Strecker, 4/5/2020 - format: cat "filename"
 */


package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.File;
import filesystem.hierarchy.FileSystemObject;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Iterator;


public class CatCommand extends AbstractCommand {

    public CatCommand() {

    }

    public CatCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {

        String outputString = "cat> " + params[1];

        File childObject = (File)fs.getCurrentWorkingDirectory().getChild(params[1]);

        outputString = "";

        if (childObject != null) {
            outputString = "cat> " + childObject.getContents();
        } else {
            System.out.println("File does not exist");
        }

        return outputString;
    }
}
