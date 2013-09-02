
EXPECTED_ARGS2=3

if [ $# -eq 3 ] 
then
mvn exec:java -Dexec.mainClass="com.oss.teamshare.Main" -Dteamshare.user="$1" -Dteamshare.device="$2" -Duser.folder="$3"
echo $1 $2 $3
else
echo Usage: "$0" username device-name teamshare-folder
fi

