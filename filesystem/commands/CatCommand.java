/*
 * CatCommand - Reads file content at current directory
 *
 * Created - Sam Strecker, 4/5/2020 - format: cat "filename"
 */


package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.File;
import filesystem.hierarchy.FileSystemObject;
import filesystem.hierarchy.FileLink;


public class CatCommand extends AbstractCommand {

    public CatCommand() {

    }

    public CatCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {

        String outputString = "cat> " + params[1];

        FileSystemObject childObject = fs.getCurrentWorkingDirectory().getChild(params[1]);

        outputString = "";

        if (childObject instanceof FileLink) {

            if (childObject != null) {
                outputString = "cat> " + ((FileLink) childObject).getContents();
            } else {
                System.out.println("Link does not exist");
            }

        } else if (childObject instanceof File) {

            if (childObject != null) {
                outputString = "cat> " + ((File) childObject).getContents();
            } else {
                System.out.println("File does not exist");
            }

        }

        return outputString;
    }
}
