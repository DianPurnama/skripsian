function fetchEvents(startDate, endDate, idKaryawan) {
    $('#calendar').fullCalendar( 'removeEventSources');
    let eventsHariLibur = [];
    let eventsPresensi = [];

    // dapetin hari libur
    let hariLiburUrl = baseUrl + "api/hari_libur";
    let params = {
        startDate:startDate,
        endDate:endDate
    };
    $.get(hariLiburUrl, params, function(data){

        for (let i = 0; i < data.length; i++) {
            let event = {
                id:data[i].id,
                title : data[i].nama,
                start: data[i].startDate,
                end: data[i].endDatePlusOne,
                rendering: 'background',
                color: COLOR_RED_LIGHTER
            }
            eventsHariLibur.push(event);
        }

        $('#calendar').fullCalendar( 'addEventSource', eventsHariLibur );
    });

    // dapetin presensi
    let presensiUrl = baseUrl + "api/presensi";
    let presensiParam = {
        startDate:startDate,
        endDate:endDate,
        idKaryawan:idKaryawan
    };
    $.get(presensiUrl, presensiParam, function(data){
        for (let i = 0; i < data.length; i++) {
            let warna = !data[i].izin && data[i].telat ? COLOR_RED_DARKER : COLOR_GREEN_LIGHTER;
            let event = {
                id:data[i].id,
                izin:data[i].izin,
                waktuAbsen:data[i].waktuAbsen,
                karyawan:data[i].karyawan,
                title :  data[i].waktuAbsen+ ' - '+data[i].karyawan.fullname,
                start: data[i].tanggal,
                color: warna,
            }
            eventsPresensi.push(event);
        }

        $('#calendar').fullCalendar( 'addEventSource', eventsPresensi );
    });
}

$("#filterKaryawan").on("change",function () {
    let idKaryawan = $(this).val();
    let view = $('#calendar').fullCalendar('getView');
    let start =$.fullCalendar.moment(view.start).format();
    let end =$.fullCalendar.moment(view.end).subtract(1,"days").format();
    fetchEvents(start,end,idKaryawan);
});

function showModal(id,karyawan,title,start,waktuAbsen,izin) {

    let startDate =$.fullCalendar.moment(start).format();

    $("#modalEvent").modal('toggle');
    $("#presensiId").val(id);
    $("#title").val(title);
    $("#presensiTanggal").val(startDate);
    if(karyawan != null){
        $("#izin").prop("checked",izin);
        $("#presensiKaryawan").val(karyawan.id);
    }
    $('#timepicker').timepicker('setTime', waktuAbsen);

    $("#dateRange").text(startDate);
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
        eventClick: function(event, jsEvent, view) {
            showModal(event.id,event.karyawan, event.title,event.start, event.waktuAbsen,event.izin);
        },
        viewRender: function(view, element) {
            let idKaryawan = $("#filterKaryawan").val();
            let startDate =$.fullCalendar.moment(view.start).format();
            let endDate =$.fullCalendar.moment(view.end).subtract(1,"days").format();
            fetchEvents(startDate,endDate, idKaryawan);
        }
    });
};

var Presensi = function () {
    "use strict";
    return {
        //main function
        init: function () {
            handleCalendar();
        }
    };
}();