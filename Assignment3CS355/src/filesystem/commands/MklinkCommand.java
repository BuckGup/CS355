/*
 * MklinkCommand - Creates a link to an existing directory
 *
 * Created - Sam Strecker, 4/5/2020 - format: mklink "filename" <existing location>
 */


package filesystem.commands;

import filesystem.general.FileSystem;
import filesystem.hierarchy.File;
import filesystem.hierarchy.FileSystemObject;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

public class MklinkCommand extends AbstractCommand {

    public MklinkCommand(){
    }

    public MklinkCommand(FileSystem fs) {
        this.fs = fs;
    }

    public String execute(String[] params) {
        int count = 0;

        String outputString = "du> ";


        outputString = "du> " + count;

        return outputString;
    }




}
