/**
 * 
 */
package com.oss.teamshare.sync;

import java.util.Collection;

/**
 * 
 *
 */
public interface VersioningStrategy {

  Revision chooseVersion(Collection<Revision> versions);
  
}
