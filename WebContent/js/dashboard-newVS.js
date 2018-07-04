$(document).ready(function(){

  var uluru = null;;
  // The map, centered at Uluru
  var map = null;
  // The marker, positioned at Uluru
  var marker = null;

  $("#novaSituacijam").click( function(event){
    event.preventDefault();
    $.ajax({
      type: "GET",
      url: "api/teritorije",
      success: function(response) {
          var teritorije = response;
          $("#ter1 option").remove();
          $.each(teritorije, function (i, item) {
              $('#ter1').append($('<option>', { 
                  value: item.id,
                  text : item.naziv, 
              }));
          });
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      }
    });

    if (user.vrsta == "ADMIN"){
      $.ajax({
        type: "GET",
        url: "api/volonteri",
        success: function(response) {
            var users = response;
            $("#volonter1 option").remove();
  
            $('#volonter1').append($('<option>', {
              value: -1,
              text : "None",
            }));
  
            $.each(users, function (i, item) {
                $('#volonter1').append($('<option>', {
                    value: item.korisnickoIme,
                    text : item.korisnickoIme,
                }));
            });
        },
        error: function(response) {
            console.log(response)
            alert(response.responseJSON.message);
        }
      });
    }

  });

  $("#createVSform").submit( function(event) {
    event.preventDefault();

    var teritorijaId = $('#ter1').val();
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
        createVS(response.fileName);
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      },
    });
  });
});


function createVS(pathSlikee) {
  var nazivMesta = $('#nazivMesta1').val();
  var opstina = $('#opstina1').val();
  var opis = $('#opis1').val();
  var tacnaLokacija = marker.getPosition().lat() + " " + marker.getPosition().lng()
  var teritorijaId = $('#ter1').val();
  if (teritorijaId == null){
    alert("No teritorija selected.");
    return null;
  }
  var nivoHitnosti = $('#hitnost1').val();
  var pathSlike = pathSlikee;
  var volonterKime =  $('#volonter1').val(); // Nesigurno da radi
  

  var data2 = {
    'nazivMesta': nazivMesta,
    'opstina': opstina,
    'opis': opis,
    'tacnaLokacija': tacnaLokacija,
    'teritorijaId': teritorijaId,
    'nivoHitnosti': nivoHitnosti,
    'pathSlike': pathSlike,
    'volonterKime': volonterKime,
  };

  $.ajax({
    method: 'POST',
    type: 'POST',
    url: 'api/vsituacije',
    dataType: 'json',
    data: JSON.stringify(data2),
    processData: false,
    contentType: 'application/json',
    success: function(response) {
      console.log(response)
      alert("Vanredna situacija kreirana.")
    },
    error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
    }
  });
}

function initMap() {
  uluru = {lat: 44.0165, lng: 21.0059};
  // The map, centered at Uluru
  map = new google.maps.Map( document.getElementById('map'), {zoom: 7, center: uluru});
  // The marker, positioned at Uluru
  marker = new google.maps.Marker({position: uluru, map: map, draggable: true});
}