
$(document).ready(function(){

    $("#signinform").submit( function(event) {
        event.preventDefault();

        var $form = $(this);
        var username = $('#inputUsername').val();
        var password = $('#inputPassword').val();
        
        var data = {
            'username': username,
            'password': password
        }

        $.ajax({
            type: "POST",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            dataType: 'json',
            url: "api/auth/signin",
            data: JSON.stringify(data),
            success: function(response) {
                setUser(response)
                $(window).attr('location','index.html');
            },
            error: function(response) {
                alert(response.responseJSON.message);
                console.log(response);
            }
        });
    });
});

function setUser(response){
    user = response;
    console.log(user);
}
