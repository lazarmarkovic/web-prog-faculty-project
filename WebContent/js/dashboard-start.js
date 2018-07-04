
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
        }
    });
    
});

function setUser(response){
  user = response;
  console.log(user);
}