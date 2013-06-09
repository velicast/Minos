/* 
 * File:   main.cpp
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 6:13 AM
 */

#include <cstdlib>
#include <cstring>

#include "CheckerReply.h"
#include "TokenChecker.h"

using namespace std;

/*
 * argv[0] - this program executable
 * argv[1] - test output file (Original file provided by de problem)
 * argv[2] - user output file (File provided by user submission)
 * argv[3] - Token separator (use "\0" for complete matching)
 */
int main(int argc, char** argv) {
    // no parameters founds
  if (argc < 3) {
    return CheckerReply::INTERNAL_ERROR;
  }
  if (argc == 3) {
    return TokenChecker::check(argv[1], argv[2], "\0");
  }
  return TokenChecker::check(argv[1], argv[2], argv[3]);
}
