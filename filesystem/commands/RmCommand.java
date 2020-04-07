/*
 * RmCommand - Deletes a file at current level
 *
 * Created - Sam Strecker, 4/4/2020 - format: rm "filename"
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.File;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;

public class RmCommand extends AbstractCommand {

    public RmCommand() {

    }

    public RmCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {

        String outputString = "rm> " + params[1] + " removed";
        Directory currentDir = fs.getCurrentWorkingDirectory();
        currentDir.removeChild(params[1]);

        return outputString;
    }

}
