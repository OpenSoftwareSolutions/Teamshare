#! /usr/bin/python

import os
import sys
import random
import getopt
import json
from util import *

def usage(argv):
    """ Print help screen
        @param argv: Passed arguments
    """
    print "Usage: python %s [OPTIONS]" % argv[0]
    print "Options:"
    print "\t-d, --different\t\tdifferent owner for every team"
    print "\t-h, --help\t\tprint help screen"
    print "\t-i, --input\t\tinput file; if given, -n argument is ignored"
    print "\t-I, --indent\t\tindentation width, defaults to 2"
    print "\t-l, --min-length\tminimum length of names and IDs, defaults to 5"
    print "\t-L, --max-length\tmaximum length of names and IDs, defaults to 10"
    print "\t-n, --number\t\tnumber of users to be generated, defaults to 5"
    print "\t-N, --Number\t\tnumber of teams to be generated, defaults to 5"
    print "\t-o, --output\t\toutput file, defaults to teams"
    print "\t-u, --min-users\t\tminimum user members per team, defaults to 1"
    print "\t-U, --max-users\t\tmaximum user members per team, defaults to 3"

def get_usernames():
    """ Parses a JSON file and retrieves the usernames
        @return: List of usernames
    """
    data = i_file.read()
    data = json.loads(data)
    
    users = data["users"]
    usernames = []
    for user in users:
        usernames.append(user["username"])
    return usernames

def generate_teams(different, indent, min_length, max_length,
                   min_users, max_users, num_users, num_teams, usernames):
    team_ids = []
    team_names = []
    team_owners = []
    team_users = []
    
    team_id_prefix = "tid_"
    team_name_prefix = "t_"
    
    """Hack for randrange"""
    max_length += 1
    max_users += 1
    
    for i in xrange(num_teams):
        add_element(generate_id, team_ids, team_id_prefix,
                    (random.randrange(min_length, max_length),))
        add_element(generate_name, team_names, team_name_prefix,
                    (random.randrange(min_length, max_length),))
        team_owners.append(random.choice(usernames))
        team_users.append([])
        team_users[i].append(team_owners[-1])
        num_team_users = random.randrange(min_users, max_users)
        for j in xrange(1, num_team_users):
            team_user = random.choice(usernames)
            while team_user in team_users[i]:
                team_user = random.choice(usernames)
            team_users[i].append(team_user)
        
    print_json(num_teams, indent, team_ids, team_names,
               team_owners, team_users)

def print_json(num_teams, indent, team_ids, team_names,
               team_owners, team_users):
    data = {}
    data["teams"] = []
    for i in xrange(num_teams):
        team_data = {}
        team_data["id"] = team_ids.pop()
        team_data["name"] = team_names.pop()
        team_data["owner"] = team_owners.pop()
        team_data["users"] = team_users.pop()
        data["teams"].append(team_data)
    
    o_file.write(json.dumps(data, indent=indent))

if __name__ == "__main__":
    
    """ Default values for arguments"""
    different = False
    indent = 2
    input_path = None
    min_length = 5
    max_length = 10
    min_users = 1
    max_users = 3
    num_users = 5
    num_teams = 5
    output_path = "teams"
    tmp_path = "tmp_users"
    
    short_options = "dhi:I:l:L:n:N:o:u:U:"
    long_options = ["different", "help", "input=", "indent=",
                    "min-length=", "max-length=",
                    "number-users=", "number-teams=",
                    "output=", "min-users=", "max-users="]
    
    """ Get arguments and check validity"""
    try:
        opts, args = getopt.getopt(sys.argv[1:], short_options, long_options)
    except getopt.GetoptError, err:
        print str(err)
        print usage(sys.argv)
        sys.exit(2)
        
    """ Assign arguments and check type validity"""
    for o, a in opts:
        try:
            if o in ("-d", "--different"):
                different = True
            elif o in ("-h", "--help"):
                usage(sys.argv)
                sys.exit(0)
            elif o in ("-i", "--input"):
                input_path = str(a)
            elif o in ("-I", "--indent"):
                indent = int(a)
            elif o in ("-l", "--min-length"):
                min_length = int(a)
            elif o in ("-L", "--max-length"):
                max_length = int(a)
            elif o in ("-n", "--number-users"):
                num_users = int(a)
            elif o in ("-N", "--number-teams"):
                num_teams = int(a)
            elif o in ("-o", "--output"):
                out_path = str(a)
            elif o in ("-u", "--min-users"):
                min_users = int(a)
            elif o in ("-U", "--max-users"):
                max_users = int(a)
            else:
                print "Syntax error"
                sys.exit(2)
        except Exception:
            if o in ("-o", "--output"):
                print "Parameter %s requires a valid path" % o
            else:
                print "Parameter %s requires an integer value." % o
            sys.exit(2)
    
    """Test arguments and read a given JSON file or generate one"""
    try:
        if min_users > max_users:
            raise InputArgError("The number range for users range is incorrect",
                                [min_users, max_users])
        if min_length > max_length:
            raise InputArgError("The length range is incorrect",
                                [min_length, max_length])
        if check_validity((min_length, min_users,
                           num_users, num_teams, indent,)) == False:
            raise InputArgError("Parameters must be positive")
        if input_path != None:
            i_file = open(input_path, "r")
        else:
            os.system("python generate_users.py -o %s -n %d -l %d -L %d"
                       % (tmp_path, num_users, min_length, max_length))
            i_file = open(tmp_path, "r")
        usernames = get_usernames()
        i_file.close()
        num_users = len(usernames)
        if num_users < max_users:
            raise InputArgError("Number of maximum allowed users per team" +
                                "(%d) exceeds number of available users(%d)" 
                                % (max_users,num_users))
        o_file = open(output_path, "w")
    except InputArgError as err:
        print err
        sys.exit(2)
    except IOError:
        print "Input/Output path not valid"
        sys.exit(2)
    except Exception:
        print "Invalid JSON file"
        sys.exit(2)
        
    #if tmp_path != None:
    #    os.remove(tmp_path)

    random.seed()
    
    generate_teams(different, indent, min_length, max_length,
                   min_users, max_users, num_users, num_teams, usernames)
    o_file.close()
