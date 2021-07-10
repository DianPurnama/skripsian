$(document).ready(function () {

    $(".phone-mask").inputmask("(999) 999-999999",{removeMaskOnSubmit : true});
    $(".number-mask").inputmask("9999999999999999",{removeMaskOnSubmit : true, "placeholder": ""});
    $(".currency-mask").inputmask({ alias : "currency", prefix: 'Rp. ',removeMaskOnSubmit : true});
    $(".ip-mask").inputmask({ alias : "ip"});
    $(".pin-mask").inputmask("999999",{removeMaskOnSubmit : true});
});
