import collections
import getopt
import os
import platform
import random
import string
import sys
from time import sleep

class Directory(object):
    def __init__(self, name, parent = None):
        self.name = name
        self.parent = parent
        self.dirs = []
        self.files = []
        self.dir_names = []
        self.file_names = []
        
    def addDir(self, new_dir):
        self.dirs.append(new_dir)
        self.dir_names.append(new_dir.name)
        
    def addFile(self, new_file):
        self.files.append(new_file)
        self.file_names.append(new_file.name)
        
    def rmDir(self, old_dir):
        self.dirs.remove(old_dir)
        self.dir_names.remove(old_dir.name)
        
    def rmFile(self, old_file):
        self.files.remove(old_file)
        self.file_names.remove(old_file.name)

class File(object):
    def __init__(self, name, parent = None):
        self.name = name
        self.parent = parent

class InputArgError(Exception):
    def __init__(self, msg):
        self.msg = msg

    def __str__(self):
        return self.msg

def usage(argv):
    """ Print help screen
    @param argv: Passed arguments
    """
    print "Usage: python %s" % argv[0]
    print "Options:"
    print "\t-m, --min-time\t\tminimum time between operations, defaults to 0"
    print "\t-M, --max-time\t\tmaximum time between operations, defaults to 10"
    print "\t-l, --min-length\tminimum length of names and IDs, defaults to 5"
    print "\t-L, --max-length\tmaximum length of names and IDs, defaults to 10"
    print "\t-l, --min-size\t\tminimum size of files in bytes, defaults to 1"
    print "\t-L, --max-size\t\tmaximum size of files in bytes, defaults to 10"
    print ("\t-o, --output\t\t" +
          "directory in which to simulate events, defaults to test")
    print "\t-r, --read\t\treads the current structure of the output directory"
    print "\t-h, --help\t\tprint help screen"
    print "Operations - can be stacked"
    print "\t-a, --all\t\tuses all the operations"
    print "\t-1, --mkfile\t\tadds creating files to the permitted operations"
    print "\t-2, --mkdir\t\tadds creating directories to the permitted operations"
    print "\t-3, --cpfile\t\tadds copying files to the permitted operations"
    print "\t-4, --cpdir\t\tadds copying directories to the permitted operations"
    print "\t-5, --mvfile\t\tadds moving files to the permitted operations"
    print "\t-6, --mvdir\t\tadds moving directories to the permitted operations"
    print "\t-7, --renfile\t\tadds renaming files to the permitted operations"
    print "\t-8, --rendir\t\tadds renaming directories to the permitted operations"
    print "\t-9, --rmfile\t\tadds removing files to the permitted operations"
    print "\t-0, --rmdir\t\tadds removing directories to the permitted operations"

def read_content(current):
    """ Read existing filesystem structure and saves it in a tree
        @param root: Working directory
    """
    for content in os.listdir(current.name):
        name = os.path.join(current.name, content)
        if os.path.isdir(name) == True:
            dir = Directory(name)
            current.addDir(dir)
            read_content(dir)
        else:
            file = File(name)
            current.addFile(file)

def simulate(min_time, max_time, current):
    """ Performs random operations
        @param min_time: Minimum time to wait between operations
        @param max_time: Maximum time tot wait between operations
        @current: Directory instance for the working Directory
    """
    while True:
        operation = random.choice(operations)
        operate(operation, ["here", "descend"], current, current.name)
        sleep(random.uniform(min_time, max_time))

def operate(operation, where, current, path):
    """ Performs given operation
        @param operation: Operations to perform
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    if operation == "mkfile":
        name = generate_name(min_length, max_length) + ".txt"
        size = random.randrange(min_size, max_size)
        mkfile(name, size, where, current, path)
    elif operation == "mkdir":
        name = generate_name(min_length, max_length)
        mkdir(name, where, current, path)
    elif operation == "cpfile":
        cpfile(where, current, path)
    elif operation == "cpdir":
        cpdir(where, current, path)
    elif operation == "mvfile":
        mvfile(where, current, path)
    elif operation == "mvdir":
        mvdir(where, current, path)
    elif operation == "renfile":
        renfile(where, current, path)
    elif operation == "rendir":
        rendir(where, current, path)
    elif operation == "rmfile":
        rmfile(where, current, path)
    elif operation == "rmdir":
        rmdir(where, current, path)

def mkfile(name, size, where, current, path):
    """ Performs a file creation
        @param name: Name of the file
        @param size: Size of the file
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    """ If random.choice returns "here" or the current directory
    has no subdirectories the file is created in the current directory
        Else it chooses a subdirectory in which to create the file
    """ 
    if random.choice(where) == "here" or current.dirs == []:
        file_path = os.path.join(path, name)
        file = File(name, current)
        current.addFile(file)
        file = open(file_path, "w")
        file.write(os.urandom(size))
        file.close()
        write_log("mkfile", (file_path,))
    else:
        dir = random.choice(current.dirs)
        mkfile(name, size, where, dir, os.path.join(path, dir.name))

def mkdir(name, where, current, path):
    """ Performs a directory creation
        @param name: Name of the directory
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    """ If random.choice returns "here" or the current directory
    has no subdirectories the directory is created in the current directory
        Else it chooses a subdirectory in which to create the directory
    """ 
    if random.choice(where) == "here" or current.dirs == []:
        dir_path = os.path.join(path, name)
        dir = Directory(name, current)
        current.addDir(dir)
        os.mkdir(dir_path)
        write_log("mkdir", (dir_path,))
    else:
        dir = random.choice(current.dirs)
        mkdir(name, where, dir, os.path.join(path, dir.name))

def cpfile(where, current, path):
    """ Performs a file copy
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    src_file, src = get_file_path(where, current, path)
    dst_dir, dst = get_dir_path(where, current, path)
    """ If the file is already in the destination directory,
        another destination directory is chosen.
    """
    while src_file.name in dst_dir.file_names:
        """ To avoid infinite loops, every time another directory
        is chosen as a destination, a new directory is created,
        so that an empty directory can be chosen as the destination.
        """
        operate("mkdir", where, current, path)
        dst_dir, dst = get_dir_path(where, current, path)

    os.system(copy_file_cmd(src, dst))
    file = File(src_file.name, dst_dir)
    dst_dir.addFile(file)
    write_log("cpfile", (src, dst,))

def copy_file_cmd(src, dst):
    """ Returns the corresponding copy file command depending on the OS
        @param src: Path of the file to be copied
        @param dst: Path of the directory in which to copy the file
        @return: Command to be executed for the file copy
    """
    if(platform.system() == "Windows"):
        return "xcopy " + src + " " + dst
    else:
        return "cp " + src + " " + dst
    
def cpdir(where, current, path):
    """ Performs a recursive directory copy
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    src_dir, src = get_dir_path(where, current, path)
    """ Don't choose the root as the source."""
    while src == root.name:
        operate("mkdir", where, current, path)
        src_dir, src = get_dir_path(where, current, path)
    dst_dir, dst = get_dir_path(where, current, path)
    while src in dst or src_dir.name in dst_dir.dir_names:
        """ To avoid infinite loops, every time another directory
        is chosen as a destination, a new directory is created,
        so that an empty directory can be chosen as the destination.
        """
        operate("mkdir", where, current, path)
        dst_dir, dst = get_dir_path(where, current, path)
    os.system(copy_dir_cmd(src, dst, src_dir.name))
    dir = Directory(src_dir.name, dst_dir)
    dst_dir.addDir(dir)
    write_log("cpdir", (src, dst,))

def copy_dir_cmd(src, dst, src_name):
    """ Returns the corresponding copy directory command depending on the OS
        @param src: Path of the directory to be copied
        @param dst: Path of the directory in which to copy the directory
        @return: Command to be executed for the recursive directory copy
    """
    if(platform.system() == "Windows"):
        os.mkdir(dst + "\\" + src_name)
        return "xcopy " + src + " " + dst + "\\" + src_name
    else:
        return "cp -r " + src + " " + dst

def mvfile(where, current, path):
    """ Performs a file move
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    src_file, src = get_file_path(where, current, path)
    src_parent = src_file.parent
    
    dst_dir, dst = get_dir_path(where, current, path)
    while src_file.name in dst_dir.file_names:
        """ To avoid infinite loops, every time another directory
        is chosen as a destination, a new directory is created,
        so that an empty directory can be chosen as the destination.
        """
        operate("mkdir", where, current, path)
        dst_dir, dst = get_dir_path(where, current, path)

    os.system(move_cmd(src, dst))
    src_parent.rmFile(src_file)
    src_file.parent = dst_dir
    dst_dir.addFile(src_file)
    write_log("mvfile", (src, dst,))
    
def mvdir(where, current, path):
    """ Performs a recursive directory move
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    src_dir, src = get_dir_path(where, current, path)
    """ Don't choose the root as the source."""
    while src == root.name:
        operate("mkdir", where, current, path)
        src_dir, src = get_dir_path(where, current, path)
    src_parent = src_dir.parent
    dst_dir, dst = get_dir_path(where, current, path)
    while src in dst or src_dir.name in dst_dir.dir_names:
        """ To avoid infinite loops, every time another directory
        is chosen as a destination, a new directory is created,
        so that an empty directory can be chosen as the destination.
        """
        operate("mkdir", where, current, path)
        dst_dir, dst = get_dir_path(where, current, path)
        
    os.system(move_cmd(src, dst))
    src_parent.rmDir(src_dir)
    src_dir.parent = dst_dir
    dst_dir.addDir(src_dir)
    write_log("mvdir", (src, dst,))
    
def move_cmd(src, dst):
    """ Returns the corresponding move file/directory command depending on the OS
        @param src: Path of the file/directory to be moved
        @param dst: Path of the directory in which to move the file/directory
        @return: Command to be executed for the file/directory move
    """
    if(platform.system() == "Windows"):
        return "move " + src + " " + dst
    else:
        return "mv " + src + " " + dst

def renfile(where, current, path):
    """ Performs a file rename
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    file, file_path = get_file_path(where, current, path)
    name = generate_name(min_length, max_length) + ".txt"
    ren_file_path = file_path[:-len(file.name)] + name
    os.system(rename_cmd(file_path, ren_file_path))
    parent = file.parent
    parent.rmFile(file)
    file.name = name
    parent.addFile(file)
    write_log("renfile", (file_path, ren_file_path,))

def rendir(where, current, path):
    """ Performs a directory rename
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    dir, dir_path = get_dir_path(where, current, path)
    """ Don't choose the root directory to rename."""
    while dir_path == root.name:
        """ If the root directory has no subdirectories,
            it creates one, to avoid an infinte loop.
        """
        if current.dirs == []:
            operate("mkdir", where, current, path)
        dir, dir_path = get_dir_path(where, current, path)
        
    name = generate_name(min_length, max_length)
    ren_dir_path = dir_path[:-len(dir.name)] + name
    os.system(rename_cmd(dir_path, ren_dir_path))
    parent = dir.parent
    parent.rmDir(dir)
    dir.name = name
    parent.addDir(dir)
    write_log("rendir", (dir_path, ren_dir_path,))
    
def rename_cmd(src, dst):
    """ Returns the corresponding rename file/directory command depending on the OS
        @param src: Path of the file/directory to be renamed
        @param dst: Path of the renamed file/directory
        @return: Command to be executed for the file/directory rename
    """
    if(platform.system() == "Windows"):
        return "move " + src + " " + dst
    else:
        return "mv " + src + " " + dst
    
def rmfile(where, current, path):
    """ Performs a file remove
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    file, file_path = get_file_path(where, current, path)
    os.system(remove_file_cmd(file_path))
    parent = file.parent
    parent.rmFile(file)
    del file
    write_log("rmfile", (file_path,))
    
def remove_file_cmd(src):
    """ Returns the corresponding remove file command depending on the OS
        @param src: Path of the file to be removed
        @return: Command to be executed for the file remove
    """
    if(platform.system() == "Windows"):
        return "del " + src
    else:
        return "rm " + src

def rmdir(where, current, path):
    """ Performs a a recursive directory remove
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
    """
    dir, dir_path = get_dir_path(where, current, path)
    """ Don't choose the root directory to rename."""
    while dir_path == root.name:
        """ If the root directory has no subdirectories,
            it creates one, to avoid an infinte loop.
        """
        if current.dirs == []:
            operate("mkdir", where, current, path)
        dir, dir_path = get_dir_path(where, current, path)
    os.system(remove_dir_cmd(dir_path))
    parent = dir.parent
    parent.rmDir(dir)
    del dir
    write_log("rmdir", (dir_path,))

def remove_dir_cmd(src):
    """ Returns the corresponding remove directory command depending on the OS
        @param src: Path of the file to be removed
        @return: Command to be executed for the directory remove
    """
    if(platform.system() == "Windows"):
        return "rd /s /q " + src
    else:
        return "rm -r " + src

def get_file_path(where, current, path):
    """ Chooses a random file path
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
        @return: The File instance of the chosen file and its path
    """
    """ If random.choice returns "here" or the current directory
    has no subdirectories the file is chosen from the current directory
        Else it chooses a subdirectory from which to choose the file
    """ 
    if random.choice(where) == "here" or current.dirs == []:
        """ If the current directory has no files, it creates one."""
        if current.files == []:
            operate("mkfile", ["here"], current, path)
        file = random.choice(current.files)
        return file, os.path.join(path, file.name)
    else:
        dir = current.dirs[random.randrange(0, len(current.dirs))]
        return get_file_path(where, dir, os.path.join(path, dir.name))

def get_dir_path(where, current, path):
    """ Chooses a random file path
        @param where: List of where to perform operations:
                        here - in the current directory
                        deeper - in a subdirectory of the current one
        @param current: Directory instance for the current directory
        @param path: Path of the current directory
        @return: The File instance of the chosen file and its path
    """
    """ If random.choice returns "here" or the current directory
    has no subdirectories the current directory is chosen
        Else it chooses a subdirectory from which to choose the directory
    """ 
    if random.choice(where) == "here" or current.dirs == []:
        return current, path
    else:
        dir = current.dirs[random.randrange(0, len(current.dirs))]
        return get_dir_path(where, dir, os.path.join(path, dir.name))

def generate_name(min_length, max_length):
    """ Generates random name
        @param min_length: minimum length of the name
        @param max_length: maximum length of the name
        @return: Random string
    """
    length = random.randrange(min_length, max_length)
    alphabet = string.digits + string.letters
    return ''.join(random.choice(alphabet) for x in range(length))

def check_positive(args):
    """ Checks that the given arguments are positive
        @param args: List of arguments to check
        @return: True if positive. False if not positive.
    """
    for arg in args:
        if arg < 0:
            return False
    return True

def print_operations(operations):
    """ Prints the operations that will be used in the simulation
        @param operations: List of the used operations
    """
    print "Used operations: "
    counter = collections.Counter(operations)
    keys = counter.keys()
    values = counter.values()    
    for i in xrange(0, len(keys)):
        print str(keys[i]) + "\tx " + str(values[i])

def write_log(operation, args):
    """ Writes a log of all the operations made on the files and directories
        @param operation: Name of the last performed operation
        @param args: Tuple of the file/directory paths modified by the operation
    """
    if operation == "mkfile":
        log_file.write("Mkfile\nCreated file " + args[0])
    elif operation == "mkdir":
        log_file.write("Mkdir\nCreated directory " + args[0])
    elif operation == "cpfile":
        log_file.write("Cpfile\nCopied file " + args[0] + " to " + args[1])
    elif operation == "cpdir":
        log_file.write("Cpdir\nCopied directory " + args[0] + " to " + args[1])
    elif operation == "mvfile":
        log_file.write("Mvfile\nMoved file " + args[0] + " to " + args[1])
    elif operation == "mvdir":
        log_file.write("Mvdir\nMoved directory " + args[0] + " to " + args[1])
    elif operation == "renfile":
        log_file.write("Renfile\nRenamed file " + args[0] + " to " + args[1])
    elif operation == "rendir":
        log_file.write("Rendir\nRenamed directory " + args[0] + " to " + args[1])
    elif operation == "rmfile":
        log_file.write("Rmfile\nRemoved file " + args[0])
    elif operation == "rmdir":
        log_file.write("Rmdir\nRemoved directory " + args[0])
    log_file.write("\n")
    
def print_filesystem_structure(current):
    """ Prints the current filesystem structure in a dfs format.
    Used for debugging.
        @param current: Directory instance corresponding to the current directory
    """
    print current.name
    for dir in current.dirs:
        print_filesystem_structure(dir)
    for file in current.files:
        print file.name

if __name__ == "__main__":
    short_options = "m:M:l:L:s:S:o:rha1234567890"
    long_options = ["min-time=", "max-time=", "min-length=", "max-length=",
                    "min-size=", "max-size=", "output=", "read", "help",
                    "all", "mkfile", "mkdir", "cpfile", "cpdir",
                    "mvfile", "mvdir", "renfile", "rendir", "rmfile", "rmdir"]
    
    """Get arguments and handle errors"""
    try:
        opts, args = getopt.getopt(sys.argv[1:], short_options, long_options)
    except getopt.GetoptError, err:
        print str(err)
        usage(sys.argv)
        sys.exit(2)
    
    global operations, min_length, max_length, min_size, max_size
    global copycmd, movecmd, removecmd, renamecmd, log_file
    all_operations = ["mkfile", "mkdir", "cpfile", "cpdir", "mvfile", "mvdir",
                      "renfile", "rendir", "rmfile", "rmdir"]
    log_file = open("log.txt", "w")
    operations = []
    copycmd = "cp -r"
    movecmd = "mv"
    removecmd = "rm -r"
    renamecmd = "mv"
    min_time = 0
    max_time = 10
    min_length = 5
    max_length = 10
    min_size = 1
    max_size = 10
    time = 10
    root_path = None
    read = False
    
    """Handle arguments and eventual errors"""
    for o, a in opts:
        try:
            if o in ("-m", "--min-time"):
                min_time = int(a)
            elif o in ("-M", "--max-time"):
                max_time = int(a)
            elif o in ("-l", "--min-length"):
                min_length = int(a)
            elif o in ("-L", "--max-length"):
                max_length = int(a)
            elif o in ("-s", "--min-size"):
                min_size = int(a)
            elif o in ("-S", "--max-size"):
                max_size = int(a)
            elif o in ("-t", "--time"):
                time = int(a)
            elif o in ("-o", "--output"):
                root_path = str(a)
            elif o in ("-r", "--read"):
                read = True
            elif o in ("-h", "--help"):
                usage(sys.argv)
                sys.exit(0)
            elif o in ("-a", "--all"):
                operations += all_operations
            elif o in ("-1", "--mkfile"):
                operations.append("mkfile")
            elif o in ("-2", "--mkdir"):
                operations.append("mkdir")
            elif o in ("-3", "--cpfile"):
                operations.append("cpfile")
            elif o in ("-4", "--cpdir"):
                operations.append("cpdir")
            elif o in ("-5", "--mvfile"):
                operations.append("mvfile")
            elif o in ("-6", "--mvdir"):
                operations.append("mvdir")
            elif o in ("-7", "--renfile"):
                operations.append("renfile")
            elif o in ("-8", "--rendir"):
                operations.append("rendir")
            elif o in ("-9", "--rmfile"):
                operations.append("rmfile")
            elif o in ("-0", "--rmdir"):
                operations.append("rmdir")
            else:
                print "Syntax error"
                sys.exit(2)
        except Exception:
            if o in ("-o", "--output"):
                print "Argument %s requires a valid path." % o
            else:
                print "Argument %s requires an integer value." % o
            sys.exit(2)
    
    """Test arguments and open output file"""
    try:
        if min_time > max_time:
            raise InputArgError("The time range is incorrect")
        if min_length > max_length:
            raise InputArgError("The length range is incorrect")
        if min_length > max_length:
            raise InputArgError("The size range is incorrect")
        if check_positive((min_time, min_length, min_size, )) == False:
            raise InputArgError("Arguments must be positive")
        if root_path != None:
            os.path.exists(root_path)
        else:
            root_path = "test"
            if not os.path.exists(root_path):
                os.mkdir(root_path)
    except InputArgError as err:
        print err
        sys.exit(2)
    except Exception as err:
        print err
        sys.exit(2)
    root = Directory(root_path)
    if read == True:
        read_content(root)
    else:
        print "Removing content in output directory..."
        os.system(remove_dir_cmd(root.name))
        os.mkdir(root.name)
        print "Simulating events...\n"
        
    
    """ Hack for randomrange"""
    max_length += 1
    max_size +=1
    
    print_operations(operations)
        
    if operations == []:
        print "\nNo operations selected.\n"
        usage(sys.argv)
        print_filesystem_structure(root)
        sys.exit(2)
    else:
        print "\nTo stop, press Ctrl+C"
        simulate(min_time, max_time, root)