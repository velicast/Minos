/* 
 * Verifica la integridad del archivo a enviar
 */
function checkTargetFile() {
  
  var browserInfo = navigator.userAgent.toLowerCase();
  var size, type;
  var MAX_SIZE = 262144;  // 256 kb
  var lang = document.forms['submissionForm']['language'].value;
  
  if(browserInfo.indexOf("msie") > -1) { /* IE */
    var filepath = document.getElementById('targetFile').value;
    var myFSO = new ActiveXObject("Scripting.FileSystemObject");
    file = myFSO.getFile(filepath);
    size = file.size;
    type = file.type;
  } else {
    var file = document.forms['submissionForm']['source'].files[0];
    size = file.size;
    type = file.type;
  }
  
  if ((lang == 0 && type !== "text/x-c++src")
      || (lang == 1 && type !== "text/x-java")) {
    document.getElementById("uploadStatus").innerHTML = "Invalid file type";
    return false;
  }
  else if (size > MAX_SIZE) {
    document.getElementById("uploadStatus").innerHTML = "File is too large";
    return false;
  }
  else if (size == 0) {
    document.getElementById("uploadStatus").innerHTML = "Empty file";
    return false;
  }
  document.forms['submissionForm']['submitDate'].value = new Date();
  return true;
}
