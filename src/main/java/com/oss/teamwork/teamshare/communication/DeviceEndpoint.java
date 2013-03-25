package com.oss.teamwork.teamshare.communication;

import java.io.Closeable;

import com.oss.teamwork.teamshare.sync.Revision;

public interface DeviceEndpoint extends Closeable {

  void notifyRevision(Revision revision, byte[] swarmId);
}
