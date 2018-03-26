$(document).ready(function() {

    $('#logo').click(function() {
        location.reload();
    });

    $('#connexion').click(function() {
        $('#conteneurAccueil').hide();
        $('#onglet').show();
        contenu.innerHTML = identificationPage;
        active(ong2);
        document.body.style.backgroundColor = "#354665";
    });

    $('#inscription').click(function() {
        $('#conteneurAccueil').hide();
        $('#onglet').show();
        contenu.innerHTML = inscriptionPage;
        active(ong1);
        document.body.style.backgroundColor = "#354665";
    });
    
     $('#deconnexion').click(function() {
        $('#loginUser').val("");
          $('#connexion').show();
            $('#inscription').show();
            $('#profil').hide();
            $('#deconnexion').hide();
    });

    $('.gridster').on('click','#conduite',function(){alert('it works');})
    $('.gridster').on('click','#info',function(){alert('it works');})
    $('.gridster').on('click','#lecon',function(){alert('it works');})
    $('.gridster').on('click','#course',function(){alert('it works');})

    $('#onglet').hide();
    $('#deconnexion').hide();
    $('#profil').hide();
    console.log( "ready!" );

    $('#explication_precis1').animate({height: '150px', opacity: '0.4'}, "slow");
    $('#explication_precis2').animate({height: '150px', opacity: '0.4'}, "slow");
    $('#explication_precis3').animate({height: '150px', opacity: '0.4'}, "slow");
    $('#explication_precis1').animate({height: '150px', opacity: '1'}, "slow");
    $('#explication_precis2').animate({height: '150px', opacity: '1'}, "slow");
    $('#explication_precis3').animate({height: '150px', opacity: '1'}, "slow");
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

const identificationPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><div class='form-group'><input type='text' name='login' id='login' class='form-control input-sm' placeholder='login'></div><div class='form-group'><input type='password' name='pass' id='pass' class='form-control input-sm' placeholder='password'></div><button type='button' onClick='connexion()' name='buttonConnexion' class='btn btn-info btn-block'>Connexion</button></div></div></div></div></div>";

const inscriptionPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='first_name' id='first_name' class='form-control input-sm floatlabel' placeholder='First Name'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='last_name' id='last_name' class='form-control input-sm' placeholder='Last Name'></div></div></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='numero' id='numero' class='form-control input-sm floatlabel' placeholder='Number'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><select name='type' id='type' class='form-control'><option>etudiant</option><option>senior</option></select></div></div></div><div class='form-group'><input type='email' name='email' id='email' class='form-control input-sm' placeholder='Email Address'></div><div class='form-group'><input type='text' name='login' id='login' class='form-control input-sm' placeholder='Login'></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password' id='password' class='form-control input-sm' placeholder='Password'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password_confirmation' id='password_confirmation' class='form-control input-sm' placeholder='Confirm Password'></div></div></div><button type='button' onClick='inscription()' name='buttonInscription' class='btn btn-info btn-block'>Inscription</button></div></div></div></div></div>";
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
    var numero=document.getElementById("numero").value;
    var login=document.getElementById("login").value;
    var email=document.getElementById("email").value;
    var mdp=document.getElementById("password").value;
    var planning=null;
    var statut=document.getElementById("type").value;

    <!--changer le type en menu deroulant (senior/etudiant)-->
        var url="v1/user/"; 
    $.ajax({
        type : 'POST',
        contentType : 'application/json',
        url : url,
        dataType : "json",
        data : JSON.stringify({
            "nom" : nom,
            "prenom" : prenom,
            "login" : login,
            "numero" : numero,
            "statut" : statut,
            "email" : email,
            "password":mdp
        }),
        success : function(data, textStatus, jqXHR) {
            alert("Profil créé avec succés. Il sera accessible lorsqu'il aura été validé par le modérateur.");
            document.body.style.backgroundColor = "white";
            $('#conteneurAccueil').show();
            $('#onglet').hide();
        },
        error : function(jqXHR, textStatus, errorThrown) {
            alert("Erreur lors de la création de compte. Le login choisi peut-être déjà utilisé.");
            console.log("jqXHR" +jqXHR);
            console.log("error"+errorThrown);
            console.log('postUser error: ' + textStatus);
        }
    });

}

function connexion(){

    var login=document.getElementById("login").value;
    var mdp=document.getElementById("pass").value;


    var url="v1/user/"+login; 
    $.ajax({
        type : 'GET',
        url : url,
        dataType : "json",
        beforeSend : function(req) {
            req.setRequestHeader("Authorization", "Basic " + btoa(login + ":" + mdp));
        },

        success : function(json) {
            var tab = JSON.stringify(json);
            var js=JSON.parse(tab);
            var valide=js.valide;
            console.log(valide);
            if(valide == "oui"){
            alert("Connexion OK !");
            $('#loginUser').val(login);
            document.body.style.backgroundColor = "white";
            $('#conteneurAccueil').show();
            $('#connexion').hide();
            $('#inscription').hide();
            $('#profil').show();
            $('#deconnexion').show();
            $('#onglet').hide();
            } else {
                alert("Votre profil n'a pas encore été validé par le modérateur.");
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            alert("Login ou mot de passe incorrect !")
            console.log('postUser error: ' + textStatus);
        }
    });

}
