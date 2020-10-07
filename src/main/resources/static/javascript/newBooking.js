var icon = "<i class='fa fa-spinner fa-pulse'></i>";

$(document).ready(function(){
    $("#time, #date, #activityId").change(function (){updateEquipment()});
    $("#time, #date").change(function (){getInstructors()});

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

function getInstructors(){
    var selector = $("#pickInstructor");

    var activity = $("#activityId")[0].value;
    var date = $("#date")[0].value;
    var time = $("#time")[0].value;

    if(date && time){
        $.post("/booking/returninstructors", {activityId: activity, date: date, time: time},
            function (data) {
                selector.empty();
                for(var i = 0; i < data.length; i++){
                    var option = document.createElement("option");
                    option.value = data[i].id;
                    option.innerHTML = data[i].name;
                    selector.append(option);
                }
            }, "json")
    }

}

function updateEquipment() {
    var availtext = $("#availableEquipment");
    availtext[0].innerHTML = "<i class='fa fa-spinner fa-pulse'> </i>";

    var activity = $("#activityId")[0].value;
    var date = $("#date")[0].value;
    var time = $("#time")[0].value;

    if (activity && date && time) {
        $.post("/booking/returnEquipment", {activityId: activity, date: date, time: time},
            function (data) {
                availtext[0].innerHTML = data.length;
            }
            , "json")
    }
}