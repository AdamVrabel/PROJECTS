// HUDBA
var zvuky = 0;      // AK 0 TAK NEHRAJU ZVUKY    // AK 1 TAK HRAJU ZVUKY

var zvuk_rozbitia = new Audio("sounds/rozbitie-kocky.mp3");
var zvuk_odraz = new Audio("sounds/odraz-gulicky.mp3");
var zvuk_win = new Audio("sounds/zborenie-vsetkych-tehliciek.mp3");
var zvuk_gameover = new Audio("sounds/game-over.mp3");

document.addEventListener("DOMContentLoaded", function(event) { // POCKA POKIAL SA NACITA CELA STRANKA   
    

    var hudba = document.getElementById("music");
    //var hudba_hraje = 0;

    // AK KLIKNEM NA button s class .playMusic
    var buttonsPlay = document.querySelectorAll('.playMusic');
    buttonsPlay = Array.prototype.slice.call( buttonsPlay ); // SPRAVÍ MI POLE Z MOJICH .playMusic

    
    buttonsPlay.forEach(function(buttonPlay){
        buttonPlay.addEventListener('click', function() {

            // AK JE HUDBA ZASTAVENA -> SPUSTI JU
            if (hudba.paused){
                hudba.load();
                hudba.play();
                //hudba_hraje = 1;
                document.getElementById("musicIcon1").src = "images/icons/music.png";
                document.getElementById("musicIcon2").src = "images/icons/music.png";
            }
            else{
                hudba.pause();
                hudba.currentTime = 0;
                //hudba_hraje = 0;
                document.getElementById("musicIcon1").src = "images/icons/nomusic.png";
                document.getElementById("musicIcon2").src = "images/icons/nomusic.png";
            }
              
        });
    });

    // ZVUKY
    //zvuky = 0;

    var buttonsSounds = document.querySelectorAll('.playSounds');
    buttonsSounds = Array.prototype.slice.call( buttonsSounds ); // SPRAVÍ MI POLE Z MOJICH .buttonsSounds

    buttonsSounds.forEach(function(buttonSound){
        buttonSound.addEventListener('click', function() {
            
            if(zvuky == 0){
                zvuky = 1;
                document.getElementById("soundIcon1").src = "images/icons/sound.png";
                document.getElementById("soundIcon2").src = "images/icons/sound.png";
            }
            else if(zvuky == 1){
                zvuky = 0;
                document.getElementById("soundIcon1").src = "images/icons/nosound.png";
                document.getElementById("soundIcon2").src = "images/icons/nosound.png";
            }

        });
    });


    

    // document.querySelectorAll('.play').addEventListener('click', function(event) {
        
    //     var jeZapnute = false;
    //     if (jeZapnute == false){
    //         Play();
    //     }
        
    // });

    
    
});


