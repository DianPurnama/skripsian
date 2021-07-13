function fetchEvents(startDate, endDate) {
    let apiUrl = baseUrl + "api/hari_libur";
    let params = {
        startDate:startDate,
        endDate:endDate
    };

    $.get(apiUrl, params, function(data){
        let events = [];

        for (let i = 0; i < data.length; i++) {
            let event = {
                id:data[i].id,
                title : data[i].nama,
                start: data[i].startDate,
                end: data[i].endDatePlusOne,
                backgroundColor: "#ff5b57",
            }
            events.push(event);
        }
        $('#calendar').fullCalendar( 'removeEventSources');
        $('#calendar').fullCalendar( 'addEventSource', events );
    });
}

function showModal(id,title,start,end) {
    $("#btnHapus").hide();

    let startDate =$.fullCalendar.moment(start).format();
    let endDate =$.fullCalendar.moment(end).subtract(1,"days").format();

    $("#modalEvent").modal('toggle');
    $("#hariLiburId").val(id);
    $("#title").val(title);
    $("#hariLiburStartDate").val(startDate);
    $("#hariLiburEndDate").val(endDate);

    $("#dateRange").text(startDate + ' - '+endDate);
}

var handleCalendar = function() {
    $('#calendar').fullCalendar({
        defaultView: 'month',
        weekends: false,
        header: {
            left: 'title',
            center: '',
            right: 'today prev,next'
        },
        selectOverlap:false,
        eventOverlap:false,
        selectable: true,
        selectHelper: true,
        select: function(start, end) {
            showModal('','',start,end);
            $('#calendar').fullCalendar('unselect');
        },
        editable: true,
        eventDrop: function(event, delta, revertFunc) {
            showModal(event.id, event.title,event.start, event.end);
            $('#calendar').fullCalendar('unselect');
            $('#modalEvent').on('hidden.bs.modal', function () {
                revertFunc();
            })
        },
        eventResize: function(event, delta, revertFunc) {
            showModal(event.id, event.title,event.start, event.end);
            $('#calendar').fullCalendar('unselect');
            $('#modalEvent').on('hidden.bs.modal', function () {
                revertFunc();
            })
        },
        eventClick: function(event, jsEvent, view) {
            showModal(event.id, event.title,event.start, event.end);
            $("#btnHapus").prop("href",baseUrl+"hari_libur/delete?id="+event.id);
            $("#btnHapus").show();
        },
        viewRender: function(view, element) {
            let startDate =$.fullCalendar.moment(view.start).format();
            let endDate =$.fullCalendar.moment(view.end).subtract(1,"days").format();
           fetchEvents(startDate,endDate);
        }
    });
};

var Calendar = function () {
    "use strict";
    return {
        //main function
        init: function () {
            handleCalendar();
        }
    };
}();