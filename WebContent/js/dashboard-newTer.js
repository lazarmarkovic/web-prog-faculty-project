$(document).ready(function(){

  $("#createTerform").submit( function(event) {
    event.preventDefault();

    var naziv = $("#naziv121").val();
    var povrsina = $("#povrsina121").val();
    var bs = $("#brojStanovnika121").val();

    var data2 = {
      "naziv": naziv,
      "povrsina": povrsina,
      "brojStanovnika": bs
    };

    $.ajax({
      method: 'POST',
      type: 'POST',
      url: 'api/teritorije',
      dataType: 'json',
      data: JSON.stringify(data2),
      processData: false,
      contentType: 'application/json',
      success: function(response) {
          console.log(response);
          alert("Teritorija creirana.");
      },
      error: function(response) {
          console.log(response)
          alert(response.responseJSON.message);
      },
    });
  });
});