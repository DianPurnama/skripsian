$(document).ready(function() {

    var checkPwConf = true; var checkPw = true; var checkEmail = true, checkPhone = true;

    $("#confirmPassword").keyup(function () {
        checkPwConfirm();
    });

    function checkPwConfirm(){
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (confirmPassword != password) {
            checkPwConf = false;
            $("#errorConfirmPassword").css('display', 'block');
            checkError(checkPw, checkPwConf, checkEmail,checkPhone);
        } else {
            $("#errorConfirmPassword").css('display', 'none');
            checkPwConf = true;
            checkError(checkPw, checkPwConf, checkEmail,checkPhone);
        }
    }

    function checkError(pw, pwConf, email,phone) {
        if (pw && pwConf && email && phone) {
            $('#btnSubmit').prop('disabled', false);
        } else {
            $('#btnSubmit').prop('disabled', true);
        }
    }

    $("#password").keyup(function () {
        $("#confirmPassword").val("");
        checkPwConf = false;
        checkPwConfirm();
        validatePassword(this);
        checkError(checkPw, checkPwConf, checkEmail,checkPhone);
    });

    function validatePassword(field) {

        var validateLength = true; var validateUppercase = true; var validateLowercase = true; var validateNumber = true;

        var lowerCaseLetters = /[a-z]/g;
        var upperCaseLetters = /[A-Z]/g;
        var numbers = /[0-9]/g;

        if (field.value.match(lowerCaseLetters)) {
            $("#pwValidateLowerCase").hide();
            validateLowercase = true;
        } else {
            $("#pwValidateLowerCase").show();
            validateLowercase = false;
        }
        if (field.value.match(upperCaseLetters)) {
            $("#pwValidateUpperCase").hide();
            validateUppercase = true;
        } else {
            $("#pwValidateUpperCase").show();
            validateUppercase = false;
        }
        if (field.value.match(numbers)) {
            $("#pwValidateNumber").hide();
            validateNumber= true;
        } else {
            $("#pwValidateNumber").show();
            validateNumber= false;
        }
        //validate length

        if (field.value.length >= 8) {
            $("#pwValidateLength").hide();
            validateLength= true;
        } else {
            $("#pwValidateLength").show();
            validateLength= false;
        }

        checkValidationPassword(validateLength,validateUppercase,validateLowercase,validateNumber);
    }

    function checkValidationPassword(length,uppercase,lowercase,number) {
        if (length && uppercase && lowercase && number) {
            checkPw = true;
        } else {
            checkPw = false;
        }
    }
});