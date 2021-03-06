function fetchEvents(startDate, endDate, idKaryawan) {
    $('#calendar').fullCalendar( 'removeEventSources');
    let eventsHariLibur = [];
    let eventsPresensi = [];
    let eventsCuti = [];

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
                color: "#ff5b57",
                editable:false,
                overlap:false
            }
            eventsHariLibur.push(event);
        }

        $('#calendar').fullCalendar( 'addEventSource', eventsHariLibur );
    });

    // dapetin karyawan cuti
    let cutiUrl = baseUrl + "api/cuti_karyawan";
    let cutiParam = {
        startDate:startDate,
        endDate:endDate,
        idKaryawan:idKaryawan
    };
    $.get(cutiUrl, cutiParam, function(data){
        for (let i = 0; i < data.length; i++) {
            let event = {
                id:data[i].id,
                karyawanId:data[i].karyawan.id,
                keterangan:data[i].keteranganCuti,
                title : data[i].karyawan.fullname + ' - '+data[i].keteranganCuti,
                start: data[i].tanggalCuti,
                backgroundColor: "#8753de",
                editable:false,
                overlap:true
            }
            eventsCuti.push(event);
        }

        $('#calendar').fullCalendar( 'addEventSource', eventsCuti );
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
                izinType:data[i].izinType,
                waktuAbsen:data[i].waktuAbsen,
                karyawan:data[i].karyawan,
                title :  data[i].waktuAbsen+ ' - '+data[i].karyawan.fullname,
                start: data[i].tanggal,
                color: warna,
                editable:true,
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

function showModal(id,karyawan,title,start,waktuAbsen,izin,izinType) {

    let startDate =$.fullCalendar.moment(start).format();

    $("#modalEvent").modal('toggle');
    $("#presensiId").val(id);
    $("#title").val(title);
    $("#presensiTanggal").val(startDate);
    if(karyawan != null){
        $("#izin").prop("checked",izin);
        $("#presensiKaryawan").val(karyawan.id);
    }
    if(izin){
        $("#izinType").val(izinType);
        $("#izinGroup").show();
    }else{
        $("#izinGroup").hide();
    }
    $('#timepicker').timepicker('setTime', waktuAbsen);

    $("#dateRange").text(startDate);
}

$("#izin").on("change",function () {
   $("#izinGroup").toggle($(this).prop("checked"));
});

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
        selectOverlap: function(event) {
            return event.overlap;
        },
        selectAllow: function (e) {
            let start = $.fullCalendar.moment(e.start);
            let end = $.fullCalendar.moment(e.end).subtract(1,"days");
            return end.isSame(start);
        },
        select: function(start) {
            showModal('','', '',start, '08:00:00',false,null);
            $('#calendar').fullCalendar('unselect');
        },
        eventClick: function(event, jsEvent, view) {
            if(!event.editable) return false;
            showModal(event.id,event.karyawan, event.title,event.start, event.waktuAbsen,event.izin,event.izinType);
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