function fetchEvents(startDate, endDate, idKaryawan) {
    let apiUrl = baseUrl + "api/cuti_karyawan";
    let params = {
        startDate:startDate,
        endDate:endDate,
        idKaryawan:idKaryawan
    };

    $.get(apiUrl, params, function(data){
        let events = [];

        for (let i = 0; i < data.length; i++) {
            let event = {
                id:data[i].id,
                karyawanId:data[i].karyawan.id,
                keterangan:data[i].keteranganCuti,
                title : data[i].karyawan.fullname + ' - '+data[i].keteranganCuti,
                start: data[i].tanggalCuti,
                backgroundColor: "#8753de",
            }
            events.push(event);
        }
        $('#calendar').fullCalendar( 'removeEventSources');
        $('#calendar').fullCalendar( 'addEventSource', events );
    });
}

$("#filterKaryawan").on("change",function () {
    let idKaryawan = $(this).val();
    let view = $('#calendar').fullCalendar('getView');
    let start =$.fullCalendar.moment(view.start).format();
    let end =$.fullCalendar.moment(view.end).subtract(1,"days").format();
    fetchEvents(start,end,idKaryawan);
});

function showModal(id,karyawanId,keteranganCuti,start) {
    $("#btnHapus").hide();

    let startDate =$.fullCalendar.moment(start).format();

    $("#modalEvent").modal('toggle');
    $("#cutiId").val(id);
    $("#cutiTanggal").val(startDate);
    $("#cutiKaryawan").val(karyawanId);
    $("#keteranganCuti").val(keteranganCuti);

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
        selectOverlap:true,
        eventOverlap:true,
        selectable: true,
        selectHelper: true,
        select: function(start, end) {
            showModal('', '','',start);
            $('#calendar').fullCalendar('unselect');
        },
        editable: true,
        eventDrop: function(event, delta, revertFunc) {
            showModal(event.id, event.karyawanId,event.keterangan,event.start);
            $('#calendar').fullCalendar('unselect');
            $('#modalEvent').on('hidden.bs.modal', function () {
                revertFunc();
            })
        },
        eventResize: false,
        eventClick: function(event, jsEvent, view) {
            showModal(event.id, event.karyawanId,event.keterangan,event.start);
            $("#btnHapus").prop("href",baseUrl+"cuti_karyawan/delete?id="+event.id);
            $("#btnHapus").show();
        },
        viewRender: function(view, element) {
            let startDate =$.fullCalendar.moment(view.start).format();
            let endDate =$.fullCalendar.moment(view.end).subtract(1,"days").format();
            let idKaryawan =$("#filterKaryawan").val();
           fetchEvents(startDate,endDate,idKaryawan);
        }
    });
};

var Cuti = function () {
    "use strict";
    return {
        //main function
        init: function () {
            handleCalendar();
        }
    };
}();