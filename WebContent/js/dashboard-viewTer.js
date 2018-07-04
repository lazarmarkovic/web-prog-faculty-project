$(document).ready(function(){

  function appendData1(t) {
    $('#teritorijeTable').append('<tr><td> <a class="terResult" href="#" value="' + t.id + '">' + t.naziv + '</a></td></tr>');
  }

  $("#teritorijem").click(function(){
    $.ajax({
      type: "GET",
      url: "api/teritorije",
      dataType: 'json',
      data: {},
      success: function(response) {
          var nizTeritorija = response;
          $("#teritorijeTable tr").remove();
          $('#teritorijeTable').append('<tr><th>Naziv teritorije</th></tr>');
          nizTeritorija.forEach(function(t) {
            appendData1(t);
          });
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      },
    });

  });
 


  $('#teritorijeTable').on('click', 'a', function(event) {
    event.preventDefault();

    var terId = $(this).attr('value');

    $.ajax({
      type: "GET",
      url: "api/teritorije/" + terId,
      dataType: 'json',
      data: {},
      success: function(response) {
          var ter = response;
          $("#terId12").val(ter.id);
          $("#naziv12").val(ter.naziv);
          $("#povrsina12").val(ter.povrsina);
          $("#brojStanovnika12").val(ter.brojStanovnika);
      },
      error: function(response) {
        console.log(response)
        alert(response.responseJSON.message);
      },
    });
  });

  $("#updateTerform").submit( function(event) {
    event.preventDefault();

    var id =  $("#terId12").val();
    var naziv = $("#naziv12").val();
    var povrsina = $("#povrsina12").val();
    var bs = $("#brojStanovnika12").val();

    var data2 = {
      "id": id,
      "naziv": naziv,
      "povrsina": povrsina,
      "brojStanovnika": bs
    };

    $.ajax({
      method: 'PUT',
      type: 'PUT',
      url: 'api/teritorije/' + id,
      dataType: 'json',
      data: JSON.stringify(data2),
      processData: false,
      contentType: 'application/json',
      success: function(response) {
          console.log(response);
          $("#teritorijem").click();
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      },
    });
  });


  $("#deleteTer").click(function(){

    var id =  $("#terId12").val();
    $.ajax({
      method: 'DELETE',
      type: 'DELETE',
      url: 'api/teritorije/' + id,
      success: function(response) {
          console.log(response);
          $("#teritorijem").click();
          $("#terId12").val("");
          $("#naziv12").val("");
          $("#povrsina12").val("");
          $("#brojStanovnika12").val("");
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      },
    });

  });

});