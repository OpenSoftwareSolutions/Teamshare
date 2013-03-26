package com.oss.teamshare.communication;

import java.io.Closeable;

import com.oss.teamshare.sync.Revision;

public interface DeviceEndpoint extends Closeable {

  void notifyRevision(Revision revision, byte[] swarmId);
}
