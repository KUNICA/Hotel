/**
 * Created by user on 10.08.2016.
 */

$(document).on('click', '.RemoveButton', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var flatId = null;
    if (CSE != null && CSE.id.length > 7 && CSE.id.substr(0, 7) == 'remove_') {
        flatId = CSE.id.substr(7, CSE.id.length);
    }
    if(flatId!=null) {
        var urlObjects = "/admin/remove/" + flatId;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
        });

        var url = "/admin/run";
        $( location ).attr("href", url);
    }

});
