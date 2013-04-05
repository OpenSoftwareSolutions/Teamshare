Teamshare 
=========

Teamshare is a decentralized file-sharing application with emphasis on privacy,
security and portability.
 
With Teamshare you can:
* form groups
* invite friends into your groups
* store files inside your groups
* synchronize your files on all your devices in a secure and faster way

No centralized server stores your data because synchronization is performed by
using peer-to-peer technology for each group. All communication is secured and
data is locally stored in an encrypted format. 

For more information please visit TODO site

Licence
-------

Teamshare Licensing Policy

Open Software Solutions Gmbh provides this source code under a dual
license model designed to meet the development and distribution needs of
both commercial distributors (such as OEMs, ISVs and VARs) and open
source projects.

OEMs (Original Equipment Manufacturers), ISVs (Independent Software
Vendors), VARs (Value Added Resellers) and other distributors that
combine and distribute commercially licensed software with this code and
do not wish to distribute the source code for the commercially licensed
software under version 2 of the GNU General Public License (the “GPL”)
must enter into a commercial license agreement with Open Software
Solutions Gmbh.

For non-commercial use and open source development, the source code is
licensed under GPL v2:
http://www.gnu.org/licenses/old-licenses/gpl-2.0.html.

Download
--------

Teamshare source can be downloaded from TODO archive.

The Teamshare GIT repository is available on GitHub at
https://github.com/open-software-solutions/teamshare

Documentation
-------------

The documentation available for the latest version can be found in HTML format
(javadoc) in the docs/ directory and at 
https://koala.cs.pub.ro/teamwork/app-javadoc/

Prerequisites
-------------

Java SE jre >= 1.6
TODO other prerequisites

Installation
------------

On Linux: 
TODO
On Windows:
TODO
On mobile devices:
TODO

Build
-----

Teamshare uses Maven for building, so the project can be compiled from the
command line by running in its root directory:

    mvn compile

However, before doing this, a dependency needs to be manually added to the
local Maven repository. Teamshare uses ZeroC Ice, an object-oriented middleware
for remote procedure calls. Unfortunately, this library is not managed by any
public Maven repository, thus it needs to be downloaded and manually added to
the local repository. To do this, follow the steps:

* Install Ice on your machine from http://www.zeroc.com/download.html
* Locate Ice library JAR. In Ubuntu, if installed through APT, this is
  '/usr/share/java/zeroc-ice-3.4.jar'. TODO: Where would that be in other
  operating systems?
* Add the JAR to the local Maven repository by running on the command line:

    mvn install:install-file -Dfile="$ICE_LIBRARY_JAR" \
        -DgroupId=com.zeroc.ice -DartifactId=zeroc-ice \ -Dversion=3.4
        -Dpackaging=jar

  where `$ICE_LIBRARY_JAR` is the JAR file path previously located.

Bug Reporting
-------------
You can send Teamshare bug reports to TODO. 


Authors
-------

Călin-Andrei Burloiu
Răzvan Deaconescu
Adriana Drăghici

Magnus Karlsson
Donat Mueller

