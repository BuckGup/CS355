/*
 * class Link - represent file type of file system object
 *
 * Created - Paul J. Wagner, 4/10/2013
 * Modified - Sam Strecker, 4/5/2020 - updated comments
 */
package filesystem.hierarchy;

import java.util.ArrayList;
import java.util.Iterator;

import filesystem.general.FileSystem;
import filesystem.hierarchy.Directory;

import javax.swing.text.AsyncBoxView;

public class DirectoryLink extends Directory {
    // data
    private String contents;        // simulated contents of link as string
    private ArrayList<FileSystemObject> children;

    // methods
    // constructors
    // -- default constructor

    public DirectoryLink() {
        this("default", 0, null);
    }


    public DirectoryLink(String name, int size, ArrayList<FileSystemObject> children) {
        this.name = name;
        this.size = size;
        this.children = children;
    }

    public ArrayList<FileSystemObject> getChildren() {
        return this.children;
    }

    // other methods
    // -- toString - convert link to a string
    public String toString() {


        return "dir: " + name + " (" + size + ")\n";
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
