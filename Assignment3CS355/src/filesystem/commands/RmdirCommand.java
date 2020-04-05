/*
 * RmdirCommand - Deletes a directory at current level
 *
 * Created - Sam Strecker, 4/4/2020 - format: rm dirname
 */

package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.File;

public class RmdirCommand extends AbstractCommand {

    public RmdirCommand() {

    }

    public RmdirCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {

        String outputString = "rmdir> " + params[1] + " removed";
        Directory currentDir = fs.getCurrentWorkingDirectory();
        currentDir.removeDirectory(params[1]);

        return outputString;
    }

}
