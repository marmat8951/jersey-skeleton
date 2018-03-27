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
         location.reload();
    });

    $('#moderation').click(function() {
        window.location.href='http://localhost:8080/user.html';
    });


    $('#show_profil').hide();
    $('#onglet').hide();
    $('#deconnexion').hide();
    $('#profil').hide();
    $('#show_services').hide();
    $('#moderation').hide();
    $('#mode').hide();
    $('.buttons_mode').hide();
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

const inscriptionPage = "<div class='container'><div class='row centered-form'><div class='col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4'><div class='panel'><div class='panel-body'><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='first_name' id='first_name' class='form-control input-sm floatlabel' placeholder='First Name'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='last_name' id='last_name' class='form-control input-sm' placeholder='Last Name'></div></div></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='text' name='numero' id='numero' class='form-control input-sm floatlabel' placeholder='Number'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><select name='type' id='type' class='form-control'><option>etudiant</option><option>senior</option></select></div></div></div><div class='form-group'><div class='form-group'><input type='email' name='login' id='login' class='form-control input-sm' placeholder='Addresse Mail'></div><div class='row'><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password' id='password' class='form-control input-sm' placeholder='Password'></div></div><div class='col-xs-6 col-sm-6 col-md-6'><div class='form-group'><input type='password' name='password_confirmation' id='password_confirmation' class='form-control input-sm' placeholder='Confirm Password' onfocus='checkMdp()' onblur='checkMdp()'></div></div></div><button type='button' onClick='inscription()' name='buttonInscription' class='btn btn-info btn-block'>Inscription</button></div></div></div></div></div>";
contenu.innerHTML = identificationPage;

function checkMdp(){
    if($('#password').val() == $('#password_confirmation').val() && $('#password').val().length > 5 ){
        $('#password_confirmation').css('border','3px solid green');
    } else {
        $('#password_confirmation').css('border','3px solid red'); 
    }
}

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

    if($('#password').val() == $('#password_confirmation').val() && $('#password').val().length > 5 ){   
        var nom=document.getElementById("last_name").value;
        var prenom=document.getElementById("first_name").value;
        var numero=document.getElementById("numero").value;
        var login=document.getElementById("login").value;
        var mdp=document.getElementById("password").value;
        var planning=null;
        var statut=document.getElementById("type").value;


        var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$','i');

        if(!regEmail.test(login)){

            alert("Addresse mail incorrect !");
            $('login').css('border','3px solid red'); 
        } else {


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
    } else if ($('#password').val().length <= 5){
        alert("Le mot de passe doit contenir au moins 6 caractéres");
    } else {
        alert("Mot de passe incorrect !");

    }

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
                if(login == "admin@admin.com"){
                    $('#moderation').show();
                }
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
    init_page_login_whit_get();
    get_all_champs();
}

var labels = ["statut", "email", "nom", "prenom", "numero", "password"];
var div_init = document.getElementById('init_div');
var page_de_base = "";

function init_page_login_whit_get(){
    for(var i=0; i<labels.length; i++) {
        if(i <= 1){
            page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "_get' class='div_profil_get'></div></div><br/><br/>"
        } else if(i === labels.length-1){
            page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "_get' class='div_profil_get' style='display:none'></div><div id='div_" + labels[i] + "' class='div_profil'><button class='button_profil' onclick=\"display_input_update('" + labels[i] + "')\">Modifier</button></div></div><br/><br/>"
        } else {
            page_de_base += "<div class='inline_profil'><label class='label_profil'>" + labels[i] +" : </label><div id='div_" + labels[i] + "_get' class='div_profil_get'></div><div id='div_" + labels[i] + "' class='div_profil'><button class='button_profil' onclick=\"display_input_update('" + labels[i] + "')\">Modifier</button></div></div><br/><br/>"
        }
    }             
    page_de_base += "<br/><button class='button_profil' onclick=\"send_update()\">Confirmer et envoyer les modifications</button>"
    div_init.innerHTML = page_de_base;
}

function get_all_champs(){
    var login=$('#loginUser').val();
    var url="v1/user/"+login; 
    console.log(url);
    console.log("login: "+login)
    console.log("TEST1")
    $.ajax({
        type : 'GET',
        url : url,
        dataType : "json",
        success : function(json) {
            var tab = JSON.stringify(json);
            var js=JSON.parse(tab);console.log(js);
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

function display_input_update(param){                
    var display = ""
    var div = "div_" + param
    var div_display = document.getElementById(div)
    var html_display = ""

    if (param === "password"){
        html_display = "<input type='password' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><br/><input type='password' name='" + param + "_check' id='" + param + "_check' placeholder='" + param + "_check' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
    } else if (param === "numero"){
        html_display = "<input type='tel' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
    } else {
        html_display = "<input type='text' name='" + param + "' id='" + param + "_input' placeholder='" + param + "' class='input_profil' required><button class='button_profil' onclick=\"display_update('" + param + "')\">Envoyer</button>"
    }
    div_display.innerHTML = html_display;
}

function display_update(param){
    var div_name = "div_" + param + "_get"
    var div_update_put = document.getElementById(div_name);
    var input_changes = $("#"+param+"_input").val()
    $("#"+div_name).text(input_changes)
}

function send_update(){
    var url="v1/user/"+$('#loginUser').val(); 
    $.ajax({
        type: 'PUT',
        url: url,
        contentType : 'application/json',
        data : JSON.stringify({
            "nom":$("#div_nom_get").text(),
            "prenom":$("#div_prenom_get").text(),
            "numero":$("#div_numero_get").text()           
        }),

        dataType : "json",
        success: function( json ) {
            alert("Modification effectuées");
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

function create_services(param){
    $('#conteneurAccueil').hide();
    $('#show_services').show();

    var services_init = document.getElementById('show_services');

    var html_services = "<h1>"+param+"</h1><br/><iframe name='InlineFrame1' id='InlineFrame1' style='width:180px;height:220px;' src='https://www.mathieuweb.fr/calendrier/calendrier-des-semaines.php?nb_mois=1&nb_mois_ligne=4&mois=&an=&langue=fr&texte_color=B9CBDD&week_color=DAE9F8&week_end_color=C7DAED&police_color=453413&sel=true' scrolling='no' frameborder='0' allowtransparency='true'></iframe>";
    services_init.innerHTML = html_services;
}