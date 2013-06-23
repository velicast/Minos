$(function(){
    $('*[name=starttime]').appendDtpicker({
        "dateFormat": "YY/MM/DD h:m"
    });
    
    $('*[name=endtime]').appendDtpicker({
        "dateFormat": "YY/MM/DD h:m"
    });
});