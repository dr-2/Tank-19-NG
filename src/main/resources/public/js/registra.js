(function ($) {
    "use strict";

    /*==================================================================
    [ Validate ]*/
    let input = $('.validate-input .input100');
    let confrontaPasswords = $('.confronta');


    $('.validate-form').on('submit', function () {
        let check = true;

        for (let i = 0; i < input.length; i++) {
            if (validate(input[i]) === false) {
                showValidate(input[i], "Inserisci una password valida (min 3 simboli)");
                check = false;
            }
        }

        if (confrontaPasswords[0] !== confrontaPasswords[1]) {
            check = false;
            showValidate(input[1], "Le password non corrispondono")
            showValidate(input[2], "Le password non corrispondono")
        }
        return check;
    });


    $('.validate-form .input100').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    function validate(input) {
        if ($(input).attr('type') === 'email' || $(input).attr('name') === 'email') {
            if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        } else {
            if ($(input).val().trim() === '') {
                return false;
            }
        }
    }

    function showValidate(input, messaggio) {
        let thisAlert = $(input).parent();
        window.t = thisAlert
        window.m = messaggio
        thisAlert[0].attributes[1].value = messaggio;

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        let thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }


})(jQuery);