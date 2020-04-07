/*
 * class Link - represent file type of file system object
 *
 * Created - Paul J. Wagner, 4/10/2013
 * Modified - Sam Strecker, 4/5/2020 - updated comments
 */
package filesystem.hierarchy;

import java.util.ArrayList;

public class FileLink extends File {
    // data
    private String contents;        // simulated contents of link as string
    private ArrayList<FileSystemObject> children;

    // methods
    // constructors
    // -- default constructor
    public FileLink() {
        this("default", 0, "none");
    }

    // -- all-arg constructor
    public FileLink(String name, int size, String contents) {
        this.name = name;
        this.size = size;
        this.contents = contents;
    }

    // other methods
    // -- toString - convert link to a string
    public String toString() {
        return "File: " + name + " (" + size + ")\n";
    }

    public String getContents() {
        return this.contents;
    }


    // -- display() - display the file information to a string
    public String display(int level) {
        String displayString = "";

        displayString += printHelper.genSpaces(level);
        displayString += (name + "*\n");

        return displayString;
    }


}    // end - class Link
