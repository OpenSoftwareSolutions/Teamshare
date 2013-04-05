/**
 * 
 */
package com.oss.teamshare.sync;

import com.oss.teamshare.io.TeamFile;

/**
 * 
 *
 */
public interface PushStrategy {

  void push(TeamFile file);
  
}
