var tableD;

$(document).ready(function () {
    var tables = $(".datatable");
    for (var i = 0; i < tables.length; i++) {
        $(tables[i]).DataTable();
    }
    showTable($(".w3-bar-item")[0]);
})

function reCheck(elem, id){
    tableD = elem;
    $("#modal")[0].style.display = "block";
    $("#confirm")[0].value = id;
}

function remove(id){
    $.post("/booking/delete/", {bookingId: id});
    $("#modal")[0].style.display = "none";
    tableD.style.display = "none";
}


function showTable(element) {
    var x = document.getElementsByClassName("table");
    for (var i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(element.innerText).style.display = "block";
}