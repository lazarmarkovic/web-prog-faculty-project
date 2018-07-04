function setUser(response){
  user = response;
  if (user != null && user.vrsta != "ADMIN") {
    $(".user-option").show();
    $(".admin-option").hide();

    if (user.stanjeVolontera == "NORMALNO") {
      $(".blocked-user-text").hide();
    } 
    
  } else {
    $(".blocked-user-text").hide();
    $(".user-option").hide();
  }

  console.log(user);
}

$(document).ready(function(){

  $.ajax({
    type: "GET",
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    dataType: 'json',
    url: "api/auth/user",
    success: function(response) {
        setUser(response);
    },
    error: function(response) {
      alert(response.responseJSON.message);
      $(window).attr('location','signin.html');
    }
  });

  $("#signout").click( function(event) {
    event.preventDefault();

    $.ajax({
      type: "GET",
      url: "api/auth/logout",
      success: function(response) {
        setUser(null)
        $(window).attr('location','signin.html');
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      },
    });
  });

  $("#podesavanjam").click(function(){
    event.preventDefault();

    $.ajax({
      type: "GET",
      url: "api/teritorije",
      dataType: 'json',
      data: {},
      success: function(response) {
          var teritorije = response;
         
          $.each(teritorije, function (i, item) {
              $('#ter13').append($('<option>', { 
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


    $.ajax({
      type: "GET",
      url: "api/korisnici/" + user.korisnickoIme,
      dataType: 'json',
      data: {},
      success: function(response) {
          var u = response;
          $("#inputUsername13").val(u.korisnickoIme);
          $("#ime13").val(u.ime);
          $("#prezime13").val(u.prezime);
          $("#telefon13").val(u.telefon);
          $("#email13").val(u.email);
          if (user.vrsta != "ADMIN"){
            $("#ter13").val(u.teritorija.id);
          }
          
          $("#telefon13").val(u.telefon);
          $("#avatarSlika13").attr("src", "api/download/" + u.pathSlike);
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      },
    });

  });


  $("#updateAvatarSlika13").click( function(event) {
    event.preventDefault();

    var input = $( "input:file" );
    var file = input[1].files[0];

    var formData = new FormData();
    formData.append("file", input[1].files[0]);
    
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
            updateImage(response.fileName);
        },
        error: function(response) {
            console.log(response)
            alert(response.responseJSON.message);
        },
    });
  });

  $("#updateUserForm").submit(function(){
    var username = $('#inputUsername13').val();
    var password = $('#inputPassword13').val();
    var ime = $('#ime13').val();
    var prezime = $('#prezime13').val();
    var telefon = $('#telefon13').val();
    var email = $('#email13').val();
    var teritorijaId = null;
    if (user.vrsta != "ADMIN"){
      teritorijaId =  $('#ter13').val();
    } else {
      teritorijaId = -1;
    }

    var data2 = {
        'korisnickoIme': username,
        'password': password,
        'ime': ime,
        'prezime': prezime,
        'telefon': telefon,
        'email': email,
        'teritorijaId': teritorijaId,
    }

    $.ajax({
        method: 'PUT',
        type: 'PUT',
        url: 'api/korisnici/' + user.korisnickoIme,
        dataType: 'json',
        data: JSON.stringify(data2),
        processData: false,
        contentType: 'application/json',
        success: function(response) {
            console.log(response)
            $("#podesavanjam").click();
        },
        error: function(response) {
            console.log(response)
            alert(response.responseJSON.message);
        },
    });
  });

});

function updateImage(pathSlike){
  var data2 = {
    'pathSlike': pathSlike
  };

  $.ajax({
    method: 'PUT',
    type: 'PUT',
    url: 'api/korisnici/' + user.korisnickoIme + '/update-avatar',
    dataType: 'json',
    data: JSON.stringify(data2),
    processData: false,
    contentType: 'application/json',
    success: function(response) {
        console.log(response)
        $("#avatarSlika13").attr("src", "api/download/" + response.pathSlike);
    },
    error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
    },
});
}
