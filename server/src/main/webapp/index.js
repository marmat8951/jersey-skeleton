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

    $('#show_profil').hide();
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

const identificationPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><div class='form-group'><input type='text' name='login' id='login' class='form-control input-sm' placeholder='Addresse mail'></div><div class='form-group'><input type='password' name='pass' id='pass' class='form-control input-sm' placeholder='password'></div><button type='button' onClick='connexion()' name='buttonConnexion' class='btn btn-info btn-block'>Connexion</button></div></div></div></div></div>";

const inscriptionPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='first_name' id='first_name' class='form-control input-sm floatlabel' placeholder='First Name'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='last_name' id='last_name' class='form-control input-sm' placeholder='Last Name'></div></div></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='numero' id='numero' class='form-control input-sm floatlabel' placeholder='Number'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><select name='type' id='type' class='form-control'><option>etudiant</option><option>senior</option></select></div></div></div><div class='form-group'><div class='form-group'><input type='email' name='login' id='login' class='form-control input-sm' placeholder='Addresse Mail'></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password' id='password' class='form-control input-sm' placeholder='Password'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password_confirmation' id='password_confirmation' class='form-control input-sm' placeholder='Confirm Password'></div></div></div><button type='button' onClick='inscription()' name='buttonInscription' class='btn btn-info btn-block'>Inscription</button></div></div></div></div></div>";
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

function generate_login_page(){
    $('#conteneurAccueil').hide();
    $('#show_profil').show();

    var login = $('#loginUser').val()
    var labels = ["statut", "email", "nom", "prenom", "numero", "password"];

    var div_init = document.getElementById('init_div');
    var page_de_base = "";

    init_page_login_whit_get();
    get_all_champs();

    function get_all_champs(){
        var url="v1/user/"+login; 
        $.ajax({
            type : 'GET',
            url : url,
            dataType : "json",
            success : function(json) {
                var tab = JSON.stringify(json);
                var js=JSON.parse(tab);                      
                $('#div_statut_get').text(js.statut)
                $('#div_nom_get').text(js.nom)
                $('#div_prenom_get').text(js.prenom)
                $('#div_numero_get').text(js.numero)
                $('#div_email_get').text(js.login)
                $('#div_password_get').text(js.password)

            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("Login incorrect !")
                console.log('postUser error: ' + textStatus);
            }
        });
    }

    function init_page_login_whit_get(){
        for(var i=0; i<labels.length; i++) {
            if(i === 0){
                page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "' class='div_profil'></div></div><br/>"
            } else if(i === labels.length-1){
                page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "_get' class='div_profil_get' style='display:none'></div><div id='div_" + labels[i] + "' class='div_profil'><button class='button_profil' onclick=\"display_input_update('" + labels[i] + "')\">Modifier</button></div></div><br/>"
            } else {
                page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "_get' class='div_profil_get'></div><div id='div_" + labels[i] + "' class='div_profil'><button class='button_profil' onclick=\"display_input_update('" + labels[i] + "')\">Modifier</button></div></div><br/>"
            }
        }             
        page_de_base += "<br/><button class='button_profil' onclick=\"send_update()\">Confirmer et envoyer les modifications</button>"
        div_init.innerHTML = page_de_base;
    }

    function display_input_update(param){                
        var display = ""
        var div = "div_" + param
        var div_display = document.getElementById(div)
        var html_display = ""

        if (param === "password"){
            html_display = "<input type='password' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><br/><input type='password' name='" + param + "_check' id='" + param + "_check' placeholder='" + param + "_check' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
        } else if (param === "numero"){
            html_display = "<input type='tel' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
        } else if (param === "email"){    
            html_display = "<input type='email' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
        } else {
            html_display = "<input type='text' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
        }
        div_display.innerHTML = html_display
    }

    function display_update(param){
        var div_name = "div_" + param + "_get"
        var div_update_put = document.getElementById(div_name);
        var input_changes = $("#"+param+"_input").val()
        $("#"+div_name).text(input_changes)
    }


    function send_update(){
        console.log("SEND UPDATES")
        $.ajax({
            type: 'PUT',
            url: url,
            contentType : 'application/json',
            data : JSON.stringify({
                "nom":$("#div_nom_get").text(),
                "prenom":$("#div_prenom_get").text(),
                "numero":$("#div_numero_get").text(),
                "email":$("#div_email_get").text(),
                "password":$("#div_password_get").text()              
            }),

            dataType : "json",
            success: function( json ) {
                alert("Inscription reussie !!!");
            },
            error: function( xhr, status, errorThrown ) {

                console.log( "Error: " + errorThrown );
                console.log( "Status: " + status );
                console.dir( xhr );
            },
            complete: function( xhr, status ) {

            }
        });
    }
}
