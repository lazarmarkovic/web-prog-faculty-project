$(document).ready(function(){

  function appendData3(k) {
    var toDo = "";
    if (k.stanjeVolontera == "NORMALNO"){
      toDo = "Blokiraj";
    } else {
      toDo = "Deblokiraj";
    }

    $('#korisniciTable').append(`
      <tr>
        <td>` + k.korisnickoIme + `</td>
        <td>` + k.ime + " " + k.prezime + `</td> 
        <td> 
          <a class="userResult" href="#" value="` + k.korisnickoIme + `">` + toDo + `</a>
        </td>
      </tr>
    `);
  }

  $("#korisnicim").click(function(event){
    event.preventDefault();

    $.ajax({
      type: "GET",
      url: "api/volonteri",
      success: function(response) {
        var nizVolontera = response;
        $("#korisniciTable tr").remove();
        $('#korisniciTable').append('<tr><th>Korisnicko ime</th><th>Ime i prezime</th><th>Akcija</th></tr>');
        nizVolontera.forEach(function(k) {
          appendData3(k);
        });
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      }
    });
  });


  $('#korisniciTable').on('click', 'a', function(event) {
    event.preventDefault();
    var element = $(this);
    var korisnickoIme = $(this).attr('value');
    var action = $(this).text();
    var trueAction = "";
    var reverseAction = "";
    if (action == "Blokiraj"){
      trueAction = "block";
      reverseAction = "Deblokiraj";
    } else {
      trueAction = "unblock";
      reverseAction = "Blokiraj";
    }

    $(this).text(reverseAction);

    $.ajax({
      method: 'POST',
      type: 'POST',
      url: 'api/korisnici/' + korisnickoIme +'/' + trueAction,
      data: null,
      processData: false,
      contentType: 'application/json',
      success: function() {
        console.log("uradio akciju sa korisnikom");
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      }
    });

  });

});