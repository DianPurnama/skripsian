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
                overlap:false,
                title : data[i].nama,
                start: data[i].startDate,
                end: data[i].endDatePlusOne,
                color: "#ff5b57"
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
            let warna = !data[i].izin && data[i].telat ? "#f59c1a" : "#00acac";
            let event = {
                id:data[i].id,
                izin:data[i].izin,
                waktuAbsen:data[i].waktuAbsen,
                karyawan:data[i].karyawan,
                title :  data[i].waktuAbsen+ ' - '+data[i].karyawan.fullname,
                start: data[i].tanggal,
                color: warna,
                overlap:true
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
        selectable: true,
        selectHelper: true,
        selectOverlap: function(event) {
            return event.overlap;
        },
        selectAllow: function (e) {
            let start = $.fullCalendar.moment(e.start);
            let end = $.fullCalendar.moment(e.end).subtract(1,"days");
            return end.isSame(start);
        },
        select: function(start) {
            showModal('','', '',start, '08:00:00',false);
            $('#calendar').fullCalendar('unselect');
        },
        eventClick: function(event, jsEvent, view) {
            if(!event.overlap) return false;
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