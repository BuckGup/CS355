/*
 * DuCommand - Deletes a file at current level
 *
 * Created - Sam Strecker, 4/5/2020 - format: du "dirname"
 */


package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.File;
import filesystem.hierarchy.FileSystemObject;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

public class DuCommand extends AbstractCommand {

    public DuCommand() {

    }

    public DuCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {
        int count = 0;

        String outputString = "du> ";

        ArrayList<FileSystemObject> childObject = fs.getCurrentWorkingDirectory().getChildren();

        Iterator<FileSystemObject> childIterator = childObject.iterator();

        while (childIterator.hasNext()) {
            FileSystemObject currentChild = childIterator.next();
            count += currentChild.getSize();

        }
        outputString = "du> " + count;

        return outputString;
    }
}




