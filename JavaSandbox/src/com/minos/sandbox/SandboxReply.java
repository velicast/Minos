/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.sandbox;

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
public interface SandboxReply {
  
  int SUCCESS        = 0;
  int INTERNAL_ERROR = 1;
  int RUNTIME_ERROR  = 2;
  int TIME_LIMIT_EXCEEDED   = 3;
  int MEMORY_LIMIT_EXCEEDED = 4;
  int OUTPUT_LIMIT_EXCEEDED = 5;
  int ILLEGAL_INSTRUCTION   = 6;
}
