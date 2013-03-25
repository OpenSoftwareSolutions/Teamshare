module com { module oss { module teamwork { module teamshare { module communication { module zerocice {
	
	interface DeviceEndpoint {
		void notifyRevision(string teamId, string filename, string swarmId);

		string getLatestRevisionHash(string teamId, string filename);
	};
};};};};};};
