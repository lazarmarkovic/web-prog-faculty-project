var vanSit = null;

function setVanSit(vs){
  vanSit = vs;
  if (vs.stanje == "ARHIVIRANO"){
    $("comment-post").hide();
  }
  loadComments();
}

$(document).ready(function(){

  $('#dataTable').on('click', 'a', function(event) {
    event.preventDefault();

    var VSid = $(this).attr('value');
    console.log("VSid: " + VSid);

    $("#prikazSituacije").click();
    
    if (user.vrsta == "ADMIN"){
      $.ajax({
        type: "GET",
        url: "api/volonteri",
        success: function(response) {
            var users = response;
  
            $("#volonter2-new option").remove();
            $('#volonter2-new').append($('<option>', {
              value: -1,
              text : "None",
            }));
  
            $.each(users, function (i, item) {
                $('#volonter2-new').append($('<option>', {
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
    

    $.ajax({
      type: "GET",
      url: "api/vsituacije/" + VSid,
      success: function(response) {
        var vs = response;
        setVanSit(vs);
        $("#nazivMesta2").text(vs.nazivMesta);
        $("#opstina2").text(vs.opstina);
        $("#opis2").text(vs.opis);
        $("#nazivMesta2").text(vs.nazivMesta);
        $("#opstina2").text(vs.opstina);
        $("#opis2").val(vs.opis);
        var res = vs.tacnaLokacija.split(" ");
        uluru = {lat: parseFloat(res[0]), lng: parseFloat(res[1])};
        map = new google.maps.Map( document.getElementById('map2'), {zoom: 8, center: uluru});
        marker = new google.maps.Marker({position: uluru, map: map, draggable: true});
        $("#teritorija2").text(vs.teritorija.naziv);
        $("#hitnost2").text(vs.nivoHitnosti);
        $("#slika2").attr("src", "api/download/" + vs.pathSlike);
        if(vs.hasOwnProperty('volonter')){
          $("#volonter2").text(vs.volonter.korisnickoIme);
        } else {
          $("#volonter2").text("Not set");
        }
            
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      }
    });
  });


  $("#updateVolonterInVS").click(function(event){
    event.preventDefault();

    var volonterKime = $('#volonter2-new').val();
    var data2 = {
      'volonterKime': volonterKime,
    };
    
    $.ajax({
      method: 'PUT',
      type: 'PUT',
      url: 'api/vsituacije/' + vanSit.id +'/set-volonter',
      dataType: 'json',
      data: JSON.stringify(data2),
      processData: false,
      contentType: 'application/json',
      success: function(response) {
        $("#volonter2").text(response.volonter.korisnickoIme)
        console.log(response)
        alert("Novi volonter je dodat za odabranu VS.");
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      }
    });
  });


  $("#archiveVS").click(function(event){
    event.preventDefault();
    
    $.ajax({
      method: 'PUT',
      type: 'PUT',
      url: 'api/vsituacije/' + vanSit.id +'/archive',
      dataType: 'json',
      data: null,
      processData: false,
      contentType: 'application/json',
      success: function(response) {
        console.log(response)
        alert("Vanredna situacija je arhivirana.");
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      }
    });
  });


  $("#placeCommentInVS").click(function(event){
    event.preventDefault();

    var tekst = $("#novi-komentar").val();
    var korisnickoIme = user.korisnickoIme;
    var vsId = vanSit.id;

    var data2 = {
      "tekst": tekst,
      "korisnikKime": korisnickoIme,
      "vsId": vsId,
    };
    
    $.ajax({
      method: 'POST',
      type: 'POST',
      url: 'api/komentari',
      dataType: 'json',
      data: JSON.stringify(data2),
      processData: false,
      contentType: 'application/json',
      success: function(response) {
        console.log(response)
        loadComments();
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      }
    });
  });
});

function addComment(comment) {
  $("#comment-container").append(`
    <div class="col-sm-8 mb-2">
      <div class="panel panel-white post panel-shadow">
          <div class="post-heading">
              <div class="float-left image">
                  <img src="` + `api/download/` + comment.korisnik.pathSlike + `" class="img-circle avatar" height="40" width="40">
              </div>
              <div class="float-left meta">
                  <div class="title h5">
                      <b>` + comment.korisnik.korisnickoIme + `</b>
                  </div>
                  <h6>` + comment.datum + `</h6>
              </div>
          </div> 
          <div class="post-description"> 
              <p>` + comment.tekst + `</p>
          </div>
      </div>
    </div>
  `);
}

function loadComments() {
  $.ajax({
    type: "GET",
    url: "api/vsituacije/" +  vanSit.id + "/komentari",
    success: function(response) {
        var users = response;

        $("#comment-container div").remove();
        $.each(users, function (i, item) {
          addComment(item);
        });
    },
    error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
    }
  });
}