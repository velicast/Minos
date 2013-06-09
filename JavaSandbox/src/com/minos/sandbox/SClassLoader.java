/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.sandbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
public class SClassLoader extends ClassLoader {
  
  public SClassLoader() {
    super();
  }
  
  @Override
  public Class loadClass(String name) throws ClassNotFoundException {
    
    if(!validClass(name)) {
        // LOG Here!
      throw new ClassNotFoundException(name);
    }
    if (name.indexOf(".") == -1) {
      return findClass(name + ".class");
    }
    return super.loadClass(name);
  }
  
  @Override
  public Class findClass(String name) throws ClassNotFoundException {
      // get Class name
    String className = name;
    
    if (name.endsWith(".class")) {
      className = name.substring(0, name.length() - 6);
    }
    
    int i = name.lastIndexOf("/");
    if (i != -1) {
      className = className.substring(i + 1);
    }
    if(className.indexOf('.', 0) >= 0) {
        // LOG Here!
      throw new ClassNotFoundException(name);
    }
    
    byte d[] = null;
    InputStream in = null;
    try {
      in = new FileInputStream(name);
      d = new byte[in.available()];
      in.read(d);
      in.close();
    } catch (IOException ex) {
        // LOG here!
      throw new ClassNotFoundException(name);
    }
    return defineClass(className, d, 0, d.length);
  }
  
  private boolean validClass(String name) {
    
    if(name.equals("java.lang.Thread")) {
      return false;
    }
    if(name.startsWith("java.lang.") && name.indexOf('.', 10) >= 0) {
      return false;
    }
    if(name.endsWith(".ClassNotFoundException")) {
      return false;
    }
    if(name.startsWith("java.lang.")
      || name.startsWith("java.io.")
        || name.startsWith("java.nio.")
          || name.startsWith("java.util.")
            || name.startsWith("java.awt.geom.")
              || name.startsWith("java.math.")
                || name.startsWith("java.text.")
                  || name.indexOf(".") == -1) {
      return true;
    }
    return false;
  }
}
