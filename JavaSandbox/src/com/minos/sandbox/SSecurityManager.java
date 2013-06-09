/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.sandbox;

import java.io.FilePermission;
import java.security.Permission;
import java.util.PropertyPermission;

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
public class SSecurityManager extends SecurityManager {
  
  public static Thread targetThread = null;
  
  public SSecurityManager() {
    super();
  }
  
  @Override
  public void checkPermission(Permission perm) {
    customCheckPermission(perm);
  }
  
  @Override
  public void checkPermission(Permission perm, Object context) {
    customCheckPermission(perm);
  }
  
  public void customCheckPermission(Permission perm) {  
    
    if(Thread.currentThread() == targetThread) {
      if(perm instanceof FilePermission) {
          // only reads are allowed
        String name = perm.getName();
        if (name.length() > 1
          && name.charAt(0) != '.'
            && name.charAt(0) != '/'
              && perm.getActions().equals("read"))
        {
          return;
        }
      }
      if(perm instanceof RuntimePermission) {
        if(perm.getName().equals("exitVM.0")) {
          return;
        }
        if(perm.getActions().startsWith("getProperty")) {
          return;
        }
      }
      else if(perm instanceof PropertyPermission) {
          // Only reads are allowed
        if(perm.getActions().equals("read")) {
          return;
        }
      }
      throw new SecurityException(perm.toString());
    }
  }
}
