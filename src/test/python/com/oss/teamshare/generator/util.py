#! /usr/bin/python

import random
import string


alphabet = str(string.letters + string.digits )

class InputArgError(Exception):

    def __init__(self, msg):
        self.msg = msg

    def __str__(self):
        return self.msg

def add_element(generate_function, field, prefix, args):
    """ Generate unique element in the field list and append it to list
        @param generate_function: Function used for generation
        @param field: List of previously generated data
        @param prefix: Prefix for the string in the field
        @param args: Arguments for generate_function
    """
    random_element = prefix + generate_function(*args)
    while random_element in field:
        random_element = prefix + generate_function(*args)
    field.append(random_element)
        
def generate_id(length):
    """ Generate random ID
        @param length: Length of the ID
        @return: Random string
    """
    return "".join([random.choice(alphabet) for i in xrange(length)])

def generate_name(length):
    """ Generate random name
        @param length: Length of the name
        @return: Random string
    """
    return "".join([random.choice(alphabet) for i in xrange(length)])

def generate_address():
    """ Generate random IP address
        @return: IP address
    """
    ip = []
    for i in xrange(4):
        ip.append(str(random.randrange(0, 256)))
    return ".".join(ip)

def generate_port(min_port, max_port):
    """ Generate random port
        @param min_port: Minimum port value
        @param max_port: Maximum port value
        @return: Random port
    """ 
    return str(random.randrange(min_port, max_port))

def check_validity(args):
    """ Verifies that the given arguments are positive
        @param args: List of arguments to check
        @return: True if valid. False if not valid.
    """
    for arg in args:
        if arg <= 0:
            return False
    return True