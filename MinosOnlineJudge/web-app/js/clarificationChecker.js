/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function checkQuestion() {
  
  var MAX_LENGTH = 512;  // max 512 characters
  var question = document.forms['clarificationForm']['question'].value;
  
  if (question.length == 0 || question.length > MAX_LENGTH) {
    return false;
  }
  document.forms['clarificationForm']['submitDate'].value = new Date();
  return true;
}
