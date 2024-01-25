document.addEventListener("DOMContentLoaded", function(event) { // POCKA POKIAL SA NACITA CELA STRANKA   
    // PO NAČITANI STRÁNKY ZOBRAZI #main (HLAVNÚ OBRAZOVKU)
    document.getElementById('main').style.display = 'block';
    //document.getElementById('game_over').style.display = 'block';

    document.getElementById("pauza").addEventListener('click', function() {   // AK KLIKNEM NA PAUZU ZRUŠÍ SA 
        document.getElementById('pauza').style.display = 'none';
    });
});

// id MAIN //
function mainPlay(){
    //jQuery('#main').hide();
    //jQuery('#hra').show();
    //jQuery('#main').fadeOut(700);
    //jQuery('#hra').delay(800).fadeIn(1000);
    document.getElementById('main').style.display = 'none';
    document.getElementById('narocnosti').style.display = 'block';
}

function mainSettings(){
    document.getElementById('main').style.display = 'none';    
    document.getElementById('settings').style.display = 'block';
}

function mainHowTo(){
    document.getElementById('main').style.display = 'none';
    document.getElementById('howToPlay').style.display = 'block';
}

// id NAROCNOSTI //
function narocnostiGame(prvok){
    var obtiaznost=["LAHKA", "STREDNA", "TAZKA"];
    // LAHKA   >>> obtiaznost[0] 
    // STREDNA >>> obtiaznost[1] 
    // TAZKA   >>> obtiaznost[2] 
    console.log("ZVOLENA OBTIAŽNOSŤ JE: " + obtiaznost[prvok]);

    document.getElementById('narocnosti').style.display = 'none';
    document.getElementById('hra').style.display = 'block';
}
function narocnostiBack(){
    document.getElementById('narocnosti').style.display = 'none';
    document.getElementById('main').style.display = 'block';
}
function narocnostiSettings(){
    document.getElementById('narocnosti').style.display = 'none';
    document.getElementById('settings').style.display = 'block';
}

// id SETTINGS //
function settingsToMenu(){
    document.getElementById('settings').style.display = 'none';
    document.getElementById('main').style.display = 'block';
}
// id HOWTOPLAY //
function howToPlayBack(){
    document.getElementById('howToPlay').style.display = 'none';
    document.getElementById('main').style.display = 'block';
}

// id HRA //
function hraToBack(){
    document.getElementById('howToPlay').style.display = 'none';
    document.getElementById('main').style.display = 'block';
}
function pauza(){
    document.getElementById('pauza').style.display = 'flex';
}
function stav_vyhra(){  // ak zborim vsetky gulicky a mam ešte nejaký život
    document.getElementById('hra').style.display = 'none';
    document.getElementById('narocnosti').style.display = 'block';
}
function stav_stratenie_zivota(){
    document.getElementById('hra').style.display = 'none';
    document.getElementById('stratenie_zivota').style.display = 'block';
}
function gameOver(){
    document.getElementById('hra').style.display = 'none';
    document.getElementById('game_over').style.display = 'block';
}

// id STRATENIE_ZIVOTA //
function stratenieToHra(){
    document.getElementById('stratenie_zivota').style.display = 'none';
    document.getElementById('hra').style.display = 'block';
}

function stratenieToNarocnost(){
    document.getElementById('stratenie_zivota').style.display = 'none';
    document.getElementById('narocnosti').style.display = 'block';
}

// id GAME_OVER //
function game_overToMenu(){
    document.location.reload();   //ZNOVA NAČÍTA STRÁNKU (reloadne)
    //document.getElementById('game_over').style.display = 'none';
    //document.getElementById('main').style.display = 'block';
}