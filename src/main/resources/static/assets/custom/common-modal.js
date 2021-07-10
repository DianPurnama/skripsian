$(".modal-confirmation").on("click", function (event) {
    event.preventDefault();
    var link = $(this).attr("href");
    var message = $(this).data("message");

    swal({
        title: "Konfimasi Message",
        text: message,
        icon: "warning",
        closeOnClickOutside: false,
        closeOnEsc: false,
        buttons:{
            cancel: {
                text: "Tidak",
                value: false,
                visible: true,
                className: "",
                closeModal: true,
            },
            confirm: {
                text: "Ya",
                value: true,
                visible: true,
                className: "",
                closeModal: true
            }
        }
    })
    .then((goToLink) => {
    if (goToLink) {
        swal({
            title: "Loading",
            text: "Harap tunggu...",
            icon: "info",
            closeOnClickOutside: false,
            closeOnEsc: false,
            buttons:false
        })
        window.location.href = link;
    }
});
});

$("form[method='post']:not(.no-prevent-dc)").submit(function(event) {
    swal({
        title: "Loading",
        text: "Harap tunggu...",
        icon: "info",
        closeOnClickOutside: false,
        closeOnEsc: false,
        buttons:false
    })
});

$("form[method='get'].prevent-dc").submit(function(event) {
    swal({
        title: "Loading",
        text: "Harap tunggu...",
        icon: "info",
        closeOnClickOutside: false,
        closeOnEsc: false,
        buttons:false
    })
});