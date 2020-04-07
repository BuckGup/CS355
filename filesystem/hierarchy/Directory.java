/*
 * class Directory - represent directory type of file system object
 *
 * Created - Paul J. Wagner, 4/10/2013
 * Modified - Sam Strecker, 4/4/2020 - updated methods
 */
package filesystem.hierarchy;


import filesystem.general.FileSystem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author paul
 */
public class Directory extends FileSystemObject {
    // data
    ArrayList<FileSystemObject> children;        // list of children file system objects

    // methods
    // constructors
    // -- default constructor
    public Directory() {
        this("default");
    }

    // -- name constructor
    public Directory(String name) {
        this.name = name;
        this.size = 0;
        children = new ArrayList<FileSystemObject>();
    }


    public int getSize() {
        return this.size;
    }

    // other methods
    // -- getChildren() - get the children list of this directory
    public ArrayList<FileSystemObject> getChildren() {
        return children;
    }

    public ArrayList<FileSystemObject> removeChild(String child) {

        Iterator<FileSystemObject> childIterator = children.iterator();

        while (childIterator.hasNext()) {
            FileSystemObject currentChild = childIterator.next();
            if (currentChild.name.equals(child)) {
                childIterator.remove();
            }
        }
        return null;
    }


    public ArrayList<FileSystemObject> removeDirectory(String directory) {

        Iterator<FileSystemObject> directoryIterator = children.iterator();

        while (directoryIterator.hasNext()) {
            FileSystemObject currentChild = directoryIterator.next();
            if (currentChild.name.equals(directory)) {
            }
            directoryIterator.remove();
        }
        return null;
    }


    public FileSystemObject getChild(String fileName) {

        Iterator<FileSystemObject> directoryIterator = children.iterator();
        while (directoryIterator.hasNext()) {
            FileSystemObject currentChild = directoryIterator.next();
            if (currentChild.name.equals(fileName)) {
                return currentChild;
            }
        }
        return null;
    }

    // -- addChild(child) - add a child to this directory
    public void addChild(FileSystemObject child) {
        children.add(child);
    }

    // -- countChildren - count the number of children in the directory
    public int countChildren() {
        return children.size();
    }

    // -- toString - convert the directory to a string
    public String toString() {
        return "dir : " + name + "\n";
    }

    // -- display() - display this directory and children (possibly recursively) to a String
    public String display(int level) {
        String displayString = "";

        displayString += printHelper.genSpaces(level);
        displayString += (name + "/" + "\n");
        for (FileSystemObject fso : children) {
            displayString += fso.display(level + 1);
        }

        return displayString;
    }

}    // end - class Directory
