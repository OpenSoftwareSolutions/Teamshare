package filesystem;

import java.util.ArrayList;
import java.util.Date;

public class Folder {
	public String name;
	public String path;
	public long version;
	public Date lastModified;
	public boolean important;
	public ArrayList<File> files;
}
