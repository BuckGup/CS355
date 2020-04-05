/*
 * class FileSystem - overall class for holding the entire file system
 *
 * Created - Paul J. Wagner, 4/11/2013
 * Modified - Paul J. Wagner, 4/24/2014 - updated comments
 */
package filesystem.general;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

import filesystem.commands.ErrorCommand;
import filesystem.hierarchy.Directory;
import filesystem.hierarchy.FileSystemObject;

public class FileSystem {
    // data
    private Directory root;                            // root of the file system
    private String currentWorkingDirectoryString;    // current working directory as String in the file system
    private Directory currentWorkingDirectory;        // current working directory object
    private ArrayList<Directory> parentDirectoryStack;    // stack of parent directories
    private int parentDirectoryStackTop = -1;        // top of parent directory stack


    // methods
    // constructors
    // -- default constructor
    public FileSystem() {
        root = new Directory("");
        currentWorkingDirectoryString = "/";
        currentWorkingDirectory = root;
        parentDirectoryStack = new ArrayList<Directory>();
        parentDirectoryStack.add(new Directory("Dummy"));
        parentDirectoryStackTop = 0;

    }

    // other methods
    // -- toString() - overall top-level file system display
    public String toString() {
        String outputString = "";
        outputString += "fs: cwd is: " + currentWorkingDirectoryString;
        return outputString;
    }

    public String genDisplay() {
        String displayString = "";
        displayString = root.display(0);
        return displayString;
    }

    // -- getCurrentWorkingDirectoryString - return the current working directory as a string
    public String getCurrentWorkingDirectoryString() {
        return currentWorkingDirectoryString;
    }

    // -- getCurrentWorkingDirectory - return the current working directory as a Directory object
    public Directory getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }

    // -- setCurrentWorkingDirectory - set the current working directory and string to the new directory
    // TODO: allow return of error (code or text) if problems
    public void setCurrentWorkingDirectory(String newDirString) {
        if (newDirString.charAt(0) == '.' && newDirString.charAt(1) == '.') {// .. - change to parent
            if (!currentWorkingDirectoryString.equals("/")) {
                int lastSlashPos = currentWorkingDirectoryString.lastIndexOf('/');
                currentWorkingDirectoryString = currentWorkingDirectoryString.substring(0, lastSlashPos);
                if (currentWorkingDirectoryString.equals("")) {
                    currentWorkingDirectoryString = "/";
                }
            }
            currentWorkingDirectory = parentDirectoryStack.get(parentDirectoryStackTop);
            parentDirectoryStack.remove(parentDirectoryStackTop);
            parentDirectoryStackTop--;


            // else - error
        } else if (newDirString.charAt(0) == '~') {        // ~ - change to root
            currentWorkingDirectoryString = "/";
            currentWorkingDirectory = root;
            parentDirectoryStack.clear();
            parentDirectoryStack.add(new Directory("Dummy"));
            parentDirectoryStackTop = 0;
        } else {                                        // change to specified sub-directory if it exists
            // if child exists
            // TODO: find child with this name and change to it
            ArrayList<FileSystemObject> children = currentWorkingDirectory.getChildren();
            int index = 0;
            FileSystemObject child = null;
            while (index < children.size()) {
                child = children.get(index);
                //System.out.println("checking child: " + child.getName());
                if (child.getName().equals(newDirString)) {
                    //System.out.println("found dir: " + child.getName());
                    break;
                }
                index++;
            }

            if (index != children.size()) {
                parentDirectoryStack.add(currentWorkingDirectory);
                parentDirectoryStackTop++;
                currentWorkingDirectory = (Directory) child;

                if (parentDirectoryStackTop == 1) {
                    currentWorkingDirectoryString = currentWorkingDirectoryString + newDirString;
                } else {
                    currentWorkingDirectoryString = currentWorkingDirectoryString + "/" + newDirString;
                }

            } else {
                System.out.println("Error: Invalid child directory");
            }


            // else if child doesn't exist
            // error
        }
    }

    public ArrayList<Directory> getParentWorkingDirectory() {
        return parentDirectoryStack;
    }

    // -- findDir - find a given directory by name

    // -- getRoot - return the root directory of the file system
    public Directory getRoot() {
        return root;
    }

}    // end - class FileSystem
