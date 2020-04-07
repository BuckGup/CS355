/*
 * MklinkCommand - Creates a link to an existing directory
 *
 * Created - Sam Strecker, 4/5/2020 - format: mklink "filename" <existing location>
 */


package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.*;

public class MklinkCommand extends AbstractCommand {

    public MklinkCommand() {
    }

    public MklinkCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {

        String outputString = "mklink> ";
        outputString = "mklink> " + params[1] + " was linked to " + params[2];

        Directory currentDir = fs.getCurrentWorkingDirectory();

        FileSystemObject paramChild = currentDir.getChild(params[2]);


        if (paramChild instanceof File) {
            FileLink paramChildLink = null;
            paramChildLink = new FileLink(params[1], paramChild.getSize(), ((File) paramChild).getContents());

            currentDir.addChild(paramChildLink);

        } else if (paramChild instanceof Directory) {
            Directory paramChildLink = null;
            paramChildLink = new DirectoryLink(params[1], paramChild.getSize(), ((Directory) paramChild).getChildren());
            currentDir.addChild(paramChildLink);
        }


        return outputString;
    }


}
