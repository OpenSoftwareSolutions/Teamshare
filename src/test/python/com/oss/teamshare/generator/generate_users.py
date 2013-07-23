#! /usr/bin/python

import sys
import random
import getopt
import string
import json
from util import *

def usage(argv):
    """ Print help screen
        @param argv: Passed arguments
    """
    print "Usage: python %s [OPTIONS]" % argv[0]
    print "Options:"
    print "\t-p, --min-port\t\tminimum port value, defaults to 6000"
    print "\t-P, --max-port\t\tmaximum port value, defaults to 7000"
    print "\t-l, --min-length\tminimum length of names and IDs, defaults to 5"
    print "\t-L, --max-length\tmaximum length of names and IDs, defaults to 10"
    print "\t-n, --number\t\tnumber of users to be generated, defaults to 5"
    print "\t-o, --output\t\t"
    print "file in which to generate data, defaults to users"
    print "\t-i, --indent\t\tindentation width, defaults to 2"
    print "\t-h, --help\t\tprint help screen"

def generate_users(num_users, min_port, max_port,
                   min_length, max_length, indent):
    """ Generate random users data and formats it to a JSON file
        @param num_users: Number of users to generate
        @param min_port: Minimum port value
        @param max_port: Maximum port value
        @param min_length: Minimum length of a string field
        @param max_length: Maximum length of a string field
        @param indent: Indentation width
    """
    
    """ Initialize lists for random data"""
    user_ids = []
    usernames = []
    device_ids = []
    device_names = []
    device_addresses = []
    device_iceports = []
    device_swiftports = []
    
    """ Initialize prefixes for fields"""
    no_prefix = ""
    username_prefix = "u_"
    user_id_prefix = "idu_"
    device_name_prefix = "dev_"
    device_id_prefix = "idd_"
    
    """Hack for randrange"""
    max_length += 1
    max_port += 1
    
    for i in xrange(0, num_users):
        """ Add non-repeating element"""
        add_element(generate_id, user_ids, user_id_prefix,
                    (random.randrange(min_length, max_length),))
        add_element(generate_name, usernames, username_prefix,
                    (random.randrange(min_length, max_length),))
        add_element(generate_id, device_ids, device_id_prefix,
                    (random.randrange(min_length, max_length),))
        add_element(generate_name, device_names, device_name_prefix,
                    (random.randrange(min_length, max_length),))
        add_element(generate_address, device_addresses, no_prefix, ())
        
        """ Add possibly repeating element"""
        device_iceports.append(generate_port(min_port, max_port))
        device_swiftports.append(generate_port(min_port, max_port))
        
    print_json(num_users, indent, user_ids, usernames, device_ids, device_names,
               device_addresses, device_iceports, device_swiftports)
    


def print_json(num_users, indent, user_ids, usernames, device_ids, device_names,
               device_addresses, device_iceports, device_swiftports):
    """ Format and output data to a JSON file
        @param num_users: Number of users
        @param indent: Indentation width
        @param user_ids: User IDs
        @param usernames: Usernames
        @param device_ids: Device IDs
        @param device_names: Device names
        @param device_addresses: Device IP addresses
        @param device_iceports: Device ice ports
        @param device_swiftports: Device swift ports
    """
    
    data = {}
    data["users"] = []
    for i in xrange(0,num_users):
        """Transform individual data to a JSON format"""
        user_data = {}
        user_data["id"] = user_ids.pop()
        user_data["username"] = usernames.pop()
        device_data = {}
        device_data["id"] = device_ids.pop()
        device_data["name"] = device_names.pop()
        device_data["address"] = device_addresses.pop()
        device_data["icePort"] = device_iceports.pop()
        device_data["swiftPort"] = device_swiftports.pop()
        user_data["devices"] = [device_data]
        data["users"].append(user_data)
    
    o_file.write(json.dumps(data, indent = indent))



if __name__ == "__main__":
    
    short_options = "p:P:l:L:n:o:i:h"
    long_options = ["min-port=", "max-port=", "min-length=", "max-length=",
                   "number=", "output=", "indent=", "help"]
    
    """Get arguments and handle errors"""
    try:
        opts, args = getopt.getopt(sys.argv[1:], short_options, long_options)
    except getopt.GetoptError, err:
        print str(err)
        usage(sys.argv)
        sys.exit(2)
    
    min_port = 6000
    max_port = 7000
    min_length = 5
    max_length = 10
    num_users = 5
    output_path = "users"
    indent = 2
    
    """Handle arguments and eventual errors"""
    for o, a in opts:
        try:
            if o in ("-p", "--min-port"):
                min_port = int(a)
            elif o in ("-P", "--max-port"):
                max_port = int(a)
            elif o in ("-l", "--min-length"):
                min_length = int(a)
            elif o in ("-L", "--max-length"):
                max_length = int(a)
            elif o in ("-n", "--number"):
                num_users = int(a)
            elif o in ("-o", "--output"):
                output_path = str(a)
            elif o in ("-i", "--indent"):
                indent = int(a)
            elif o in ("-h", "--help"):
                usage(sys.argv)
                sys.exit(0)
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
        if min_port > max_port:
            raise InputArgError("The port range is incorrect")
        if min_length > max_length:
            raise InputArgError("The length range is incorrect")
        if check_validity((min_port, min_length, num_users, )) == False:
            raise InputArgError("Arguments must be positive")
        o_file = open(output_path, "w")
    except InputArgError as err:
        print err
        sys.exit(2)
    except Exception:
        print "Output path not valid"
        sys.exit(2)
    
    random.seed()

    generate_users(num_users, min_port, max_port,
                   min_length, max_length, indent)
    o_file.close()
