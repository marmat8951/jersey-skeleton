$(document).ready(function() {
    $("button").click(function () {
        $('#conteneurAccueil').hide();
        $('#connexion').hide();
        $('#deconnexion').show();
        $('#onglet').show();
    });

    $('#logo').click(function() {
        location.reload();
    });
    $('#onglet').hide();
    $('#deconnexion').hide();
    console.log( "ready!" );
});

function checkValue(element){
    var champ = document.getElementById(element);
    if(champ.value == 'Recherche..'){
        champ.value = '';
    }else{
        champ.value = 'Recherche..';
    }
}

ong1 = document.getElementById('inscription_login_page');
ong2 = document.getElementById('connexion_login_page');
contenu = document.getElementById('content_login_page');

const identificationPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><form role='form'><div class='form-group'><input type='text' name='login' id='login' class='form-control input-sm' placeholder='login'></div><div class='form-group'><input type='password' name='pass' id='pass' class='form-control input-sm' placeholder='password'></div><input type='submit' value='Login' class='btn btn-info btn-block'></form></div></div></div></div></div>";

const inscriptionPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><form role='form'><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='first_name' id='first_name' class='form-control input-sm floatlabel' placeholder='First Name'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='last_name' id='last_name' class='form-control input-sm' placeholder='Last Name'></div></div></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='numero' id='numero' class='form-control input-sm floatlabel' placeholder='Number'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='type' id='type' class='form-control input-sm' placeholder='junior / senior'></div></div></div><div class='form-group'><input type='email' name='email' id='email' class='form-control input-sm' placeholder='Email Address'></div><div class='form-group'><input type='text' name='login' id='login' class='form-control input-sm' placeholder='Login'></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password' id='password' class='form-control input-sm' placeholder='Password'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password_confirmation' id='password_confirmation' class='form-control input-sm' placeholder='Confirm Password'></div></div></div><button type='button' onClick='inscription()' name='buttonInscription' class='btn btn-info btn-block'>Inscription</button></form></div></div></div></div></div>";
contenu.innerHTML = identificationPage;

function nonactive(){
    ong1.className = "";
    ong2.className = "";
}

function active(moi){
    nonactive(); // nettoyage
    moi.className="active"; // je deviens active
}

ong1.addEventListener("click",function(){
    contenu.innerHTML = inscriptionPage;
    active(this);
})

ong2.addEventListener("click",function(){
    contenu.innerHTML = identificationPage;
    active(this);
})



function inscription(){
    var nom=document.getElementById("last_name").value;
    var prenom=document.getElementById("first_name").value;
    var telephone=document.getElementById("numero").value;
    var login=document.getElementById("login").value;
    var mail=document.getElementById("email").value;
    var mdp=document.getElementById("password").value;
    var planning=null;
    var type=document.getElementById("type").value;

    <!--changer le type en menu deroulant (senior/etudiant)-->
        var url="v1/user/"; 
    $.ajax({
        type : 'POST',
        contentType : 'application/json',
        url : url,
        dataType : "json",
        data : JSON.stringify({
            "name" : nom,
            "alias" : login,
            "email" : mail,
            "password" : mdp,
            "id" : 0
        }),
        success : function(data, textStatus, jqXHR) {
            alert("Utilisateur créé");
	      $('#conteneurAccueil').show();
       
        $('#deconnexion').show();
        $('#onglet').hide();
        },
        error : function(jqXHR, textStatus, errorThrown) {
            console.log('postUser error: ' + textStatus);
        }
    });

}
