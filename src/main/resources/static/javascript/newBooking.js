var icon;

$(document).ready(function(){
    icon = document.createElement("i");
    icon.className = "fa fa-spinner fa-pulse";
    $("#time, #date, #activityId").change(function (){updateEquipment()});
})

function getActivity(id){
    console.log(id);
    $.getJSON("/booking/returnActivity?activityId="+id, function(data){
        if(data.name != null){$("#name").text(data.name)}else{$("#name").text("None")}
        if(data.ageLimit != null){$("#age").text(data.rules.ageLimit)}else{$("#age").text("None")}
        if(data.rules.heightLimit != null){$("#height").text(data.rules.heightLimit)}else{$("#height").text("None")}
        if(data.rules.duration != null){$("#duration").text(data.rules.duration)}else{$("#duration").text("None")}
        if(data.rules.maxCapacity != null){$("#capacity").text(data.rules.maxCapacity)}else{$("#maxCapacity").text("None")}
        if(data.pricing.standard != null){$("#standardPrice").text(data.pricing.standard)}else{$("#standardPrice").text("None")}
        if(data.pricing.discount != null){$("#discount").text(data.pricing.discount)}else{$("#discount").text("None")}
    });
}

function getEquipment(){

    $.post("/booking/returnBookings",
        {activityId: $("#activityId").text(), date: $("#date"), time: $("#time"), duration: $("#duration")},
        function(data){

            if(data.pricing.availableEquipment != null){
                $("#availableEquipment").text(data.availableEquipment);
            }else{
                $("#availableEquipment").text("None");
            }
        }
        ,"json");
}

function updateEquipment(){
    var shower = $("#avail_container");
    var availtext = $("#availableEquipment");

    var activity = $("#activityId")[0].value;
    var date = $("#date")[0].value;
    var time = $("#time")[0].value;

    if(activity && date && time){
        shower.empty();
        shower.append(icon)

        $.post("/booking/returnEquipment", {activityId: activity, date: date, time: time},
            function(data){
                availtext.text(data.length);
                shower.empty();
                shower.append(availtext);
            }
            ,"json")
    }

    function validateForm(){
        $($("#availableEquipment").text() < $("#a"))
    }
}