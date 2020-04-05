/*
 * class File - represent file type of file system object
 * 
 * Created - Paul J. Wagner, 4/10/2013
 * Modified - Paul J. Wagner, 4/24/2013 - updated comments
 */
package filesystem.hierarchy;

import java.util.ArrayList;
import java.util.Iterator;

import filesystem.hierarchy.Directory;

public class File extends FileSystemObject {
	// data
	private String contents;		// simulated contents of file as string
	
	// methods
	// constructors
	// -- default constructor
	public File() {
		this("default", 0, "none");
	}
	
	// -- all-arg constructor
	public File(String name, int size, String contents) {
		this.name = name;
		this.size = size;
		this.contents = contents;
	}
	
	// other methods
	// -- toString - convert file to a string
	public String toString() {
		return "File: " + name + " (" + size + ")\n";
	}


	public String getContents(){
		return this.contents;
	}



	// -- display() - display the file information to a string
	public String display(int level) {
		String displayString = "";
		
		displayString += printHelper.genSpaces(level);
		displayString += (name + "\n");
		
		return displayString;	
	}
	

	
}	// end - class File
