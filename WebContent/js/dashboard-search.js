$(document).ready(function(){
  
  $("#vSituacijem").click( function(event){
    event.preventDefault();
    $.ajax({
      type: "GET",
      url: "api/teritorije",
      dataType: 'json',
      data: {},
      success: function(response) {
          var teritorije = response;
          $("#ter option").remove();
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
  });

  $("#vSituacijem").click(); 

  $("#filterform").submit( function(event) {
    event.preventDefault();

    var poDatumu = ($('#filter').val() == 0);
    var poHitnosti = ($('#filter').val() == 1);
    var poTeritoriji = ($('#filter').val() == 2);

    var hitnost = $('#hitnost').val();
    var terId = $('#ter').val();

    var poNazTer = ($('#pretraga').val() == 0);
    var poNazOpst = ($('#pretraga').val() == 1);
    var poOpisu= ($('#pretraga').val() == 2);
    var poVol = ($('#pretraga').val() == 3);
    var poPraz = ($('#pretraga').val() == 4);

    var showMyVS =$('#showMyVS').is(":checked");

    var traziOvo = $('#search').val();

    var data = {
      'poDatumu': poDatumu,
      'poHitnosti': poHitnosti,
      'poTeritoriji': poTeritoriji,
      'hitnost': hitnost,
      'terId': terId,

      'poNazTer': poNazTer,
      'poNazOpst': poNazOpst,
      'poOpisu':  poOpisu,
      'poVol': poVol,
      'poPraz': poPraz,
      'traziOvo': traziOvo,
      'showMyVS': showMyVS,
    };

    function appendData(teritorija, vs) {
      $('#dataTable').append('<tr><td> <a class="searchResult" href="#" value="' + vs.id + '">' + vs.nazivMesta + '</a></td> <td>' + vs.nivoHitnosti + '</td> <td>' + vs.teritorija.naziv + '</td> <td>' + vs.datumVreme + '</td></tr>');
    }

    $.ajax({
        type: "GET",
        url: "api/vsituacije",
        data: data,
        success: function(response) {
          var nizSituacija = response;
          $("#dataTable tr").remove();
          $('#dataTable').append('<tr><th>Naziv mesta</th><th>Hitnost</th><th>Teritorija</th><th>Datum i vreme</th></tr>');
          nizSituacija.forEach(function(vs) {
            appendData(vs.teritorija, vs);
          });
        },
        error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
        }
    });

  });
});