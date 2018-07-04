$(document).ready(function(){
    $("#vSituacije").show();
    $("#search").show();
    $("#novaSituacija").hide();
    $("#korisnici").hide();
    $("#teritorije").hide();
    $("#dodajTeritoriju").hide();
    $("#podesavanja").hide();
    $("#prikazSituacije").hide();
   
    $("#vSituacijem").on("click", function(){
      $("#vSituacije").show();
      $("#search").show();
      $("#novaSituacija").hide();
      $("#korisnici").hide();
      $("#teritorije").hide();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").hide();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#vSituacijem").addClass("active");
      $("#searchm").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").removeClass("active");

    });
    $("#novaSituacijam").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").show();
      $("#korisnici").hide();
      $("#teritorije").hide();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").hide();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#novaSituacijam").addClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").removeClass("active");
    });
    $("#korisnicim").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").hide();
      $("#korisnici").show();
      $("#teritorije").hide();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").hide();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#korisnicim").addClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").removeClass("active");
    });
    $("#teritorijem").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").hide();
      $("#korisnici").hide();
      $("#teritorije").show();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").hide();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#teritorijem").addClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").removeClass("active");
    });
    $("#dodajTeritorijum").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").hide();
      $("#korisnici").hide();
      $("#teritorije").hide();
      $("#dodajTeritoriju").show();
      $("#podesavanja").hide();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#dodajTeritorijum").addClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").removeClass("active");
    });
    $("#podesavanjam").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").hide();
      $("#korisnici").hide();
      $("#teritorije").hide();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").show();
      $("#prikazSituacije").hide();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#podesavanjam").addClass("active");
      $("#prikazSituacije").removeClass("active");
    });
    $("#prikazSituacije").on("click", function(){
      $("#vSituacije").hide();
      $("#search").hide();
      $("#novaSituacija").hide();
      $("#korisnici").hide();
      $("#teritorije").hide();
      $("#dodajTeritoriju").hide();
      $("#podesavanja").hide();
      $("#prikazSituacije").show();

      $("#vSituacijem").removeClass("active");
      $("#novaSituacijam").removeClass("active");
      $("#korisnicim").removeClass("active");
      $("#teritorijem").removeClass("active");
      $("#dodajTeritorijum").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#podesavanjam").removeClass("active");
      $("#prikazSituacije").addClass("active");
    });
});