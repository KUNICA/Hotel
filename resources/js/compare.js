/**
 * Created by user on 07.08.2016.
 */


$(document).on('click', '.checkBoxField', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var flatId = null;
    if (CSE != null && CSE.id.length > 14 && CSE.id.substr(0, 14) == 'check_compare_') {
        flatId = CSE.id.substr(14, CSE.id.length);
    }
    var checkBox = document.getElementById("check_compare_" + flatId);

    if(flatId!=null) {
        var urlObjects = "/compare/isflat/" + flatId;
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (checkFlat) {

            if(checkFlat==undefined || !checkFlat){
                urlObjects = "/compare/save/" + flatId;
            }else{
                urlObjects = "/compare/remove/" + flatId;
            }
            jQuery.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "POST",
                url: urlObjects,
                dataType:'json',
                async: false
            }).done(function( check ) {
                if(checkFlat==undefined || !checkFlat){
                    checkBox.checked = "checked";
                }else{
                    checkBox.checked = "";
                }
            });
            

        });
    }


});


$(document).on('click', '.buttonCompare', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var flatId = null;
    if (CSE != null && CSE.id.length > 8 && CSE.id.substr(0, 8) == 'compare_') {
        flatId = CSE.id.substr(8, CSE.id.length);
    }

    window.open("compare/table/" + flatId);

});