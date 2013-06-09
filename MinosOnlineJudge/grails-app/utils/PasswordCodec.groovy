/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
import java.security.MessageDigest
import sun.misc.BASE64Encoder

class PasswordCodec {
  
  static encode = { String s ->
     MessageDigest md = MessageDigest.getInstance('SHA')
     md.update s.getBytes('UTF-8')
     (new BASE64Encoder()).encode(md.digest())
  }
}