/**
 * 
 */
package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

/**
 * 
 *
 */
public interface VersioningStrategy {

  Version chooseVersion(Collection<Version> versions);
  
}
