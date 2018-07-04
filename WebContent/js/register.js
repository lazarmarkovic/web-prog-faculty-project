
$(document).ready(function(){

    $.ajax({
        type: "GET",
        url: "api/teritorije",
        dataType: 'json',
        data: {},
        success: function(response) {
            var teritorije = response;
           
            $.each(teritorije, function (i, item) {
                $('#ter').append($('<option>', { 
                    value: item.id,
                    text : item.naziv, 
                }));
            });
        },
        error: function(response) {
            console.log(response)
            alert(response.responseJSON.message);
        },
    });

    $("#registerform").submit( function(event) {
        event.preventDefault();

        var teritorijaId = $('#ter').val();
        if (teritorijaId == null){
            alert("No teritorija selected.");
            return null;
        }

        var input = $( "input:file" );
        var file = input[0].files[0];

        var formData = new FormData();
        formData.append("file", input[0].files[0]);
        
        $.ajax({
            method: 'POST',
            type: 'POST',
            url: 'api/upload',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(response) {
                console.log(response)
                register(response.fileName);
            },
            error: function(response) {
                console.log(response)
                alert(response.responseJSON.message);
            },
        });
    });
});

function register(pathSlike){
    var username = $('#inputUsername').val();
    var password = $('#inputPassword').val();
    var ime = $('#ime').val();
    var prezime = $('#prezime').val();
    var telefon = $('#telefon').val();
    var email = $('#email').val();
    var teritorijaId =  $('#ter').val();
    var imagePath = pathSlike;

    var data2 = {
        'korisnickoIme': username,
        'password': password,
        'ime': ime,
        'prezime': prezime,
        'telefon': telefon,
        'email': email,
        'teritorijaId': teritorijaId,
        'pathSlike': imagePath,
    }

    console.log(data2);

    $.ajax({
        method: 'POST',
        type: 'POST',
        url: 'api/korisnici',
        dataType: 'json',
        data: JSON.stringify(data2),
        processData: false,
        contentType: 'application/json',
        success: function(response) {
            console.log(response)
            $(window).attr('location','index.html');
        },
        error: function(response) {
            console.log(response)
            alert(response.responseJSON.message);
        },
    });
}