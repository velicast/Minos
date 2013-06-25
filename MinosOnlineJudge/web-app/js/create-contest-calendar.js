$(function(){
    $('*[name=starttime]').appendDtpicker({
        "dateFormat": "yyyy/MM/DD h:m"
    });
    
    $('*[name=endtime]').appendDtpicker({
        "dateFormat": "yyyy/MM/DD h:m"
    });
});