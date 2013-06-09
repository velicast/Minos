/* 
 * File:   SandboxReply.h
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:22 AM
 */

#ifndef SANDBOXREPLY_H
#define	SANDBOXREPLY_H

class SandboxReply {
public:
  static const int INTERNAL_ERROR;
  static const int SUCCESS;
  static const int RUNTIME_ERROR;
  static const int OUPUT_LIMIT_EXCEEDED;
  static const int MEMORY_LIMIT_EXCEEDED;
  static const int TIME_LIMIT_EXCEEDED;
  static const int ILLEGAL_INSTRUCTION;
private:

};

#endif	/* SANDBOXREPLY_H */

