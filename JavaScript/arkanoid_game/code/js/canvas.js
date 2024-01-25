/////////////////
// Constructor //
class User {
    constructor(name, nahrateScore){
        this.name = name;
        this.nahrateScore = nahrateScore;
    }
    score(){
        console.log(this.name + " nahral " + this.nahrateScore + " bodov.");
    }
    login(){
        console.log(this.name, "just logged in.");
        this.timeStample();
    }
    timeStample(){
        var today = new Date();
        //var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        var date = today.getDate() + "." + (today.getMonth()+1) + "." + today.getFullYear();
        
        var today = new Date();
        var time = today.getHours() + ":" + today.getMinutes();
        console.log("[" + time + "]  " + date);
    }

}

var user1 = new User('User');
//console.log(user1.name+" ("+user1.age+")");
user1.login();
//user1.timeStample();
/////////////////

class Observer {
    constructor(value) {
        this.value = value;
        this.prevValue = 0;
        this.observe.bind(this);
        this.notify.bind(this);
    }

    observe() {
        setInterval(() => {
            if(this.value !== this.prevValue) {
                //console.log('observer works');
                //console.log('Nahraté skóre: '+ this.value);
                user1.nahrateScore = this.value;
                user1.score();
                
                this.prevValue = this.value;
            }
        }, 10)
    }

    notify(score) {
        console.log('notify');
        this.value = score;
    }
}

var observer = new Observer(0);
observer.observe();



/////////////////////////////////////////////
///// MODEL ///// VIEW ///// CONTROLLER /////
/////////////////////////////////////////////

document.addEventListener("DOMContentLoaded", function(event) { // POCKA POKIAL SA NACITA CELA STRANKA

/////////////////////////////////////
////////////// MODEL ////////////////
/////////////////////////////////////
    
var canvas = document.querySelector("canvas")
var ctx = canvas.getContext("2d");
    
var globalID; // PRE requestAnimationFrame
function cancelAllAnimationFrames(){
    var id = window.requestAnimationFrame(function(){});
    while(id--){
        window.cancelAnimationFrame(id);
    }
}


// SKÓRE
var rozbite = 0;
var score = 0;
var nasobokSkore = 1;
// ŽIVOTY
var lives = 3;

class Lopticka {
    constructor(x, y, speed_dx, speed_dy, dx, dy, ballRadius){
        this.x = x
        this.y = y
        this.speed_dx = speed_dx
        this.speed_dy = speed_dy
        this.dx = dx
        this.dy = dy
        this.ballRadius = ballRadius
    }
}
const ball = new Lopticka(canvas.width/2, canvas.height-30, 2, -2, 2, -2, 10);

class Padlo{
    constructor(paddleHeight, paddleWidth, paddleX, rightPressed, leftPressed){
        this.paddleHeight = paddleHeight
        this.paddleWidth = paddleWidth
        this.paddleX = paddleX
        this.rightPressed = rightPressed
        this.leftPressed = leftPressed
    }
}
const paddle = new Padlo(10, 75, (canvas.width-75)/2, false, false);
// štartovacia pozicia
//let x = canvas.width/2;
//let y = canvas.height-30;

//var speed_dx = 2;
//var speed_dy = -2;
// posun loptičky
//var dx = ball.speed_dx;
//var dy = ball.speed_dx;

// polomer loptičky
//var ballRadius = 10;

// definícia pádla
//var paddleHeight = 10;
//var paddleWidth = 75;
//var paddleX = (canvas.width-paddleWidth) / 2 ;

// OVLÁDANIE PÁDLA
//var rightPressed = false;
//var leftPressed = false;

// TEHLIČKY
var brickRowCount = 5;
var brickColumnCount = 8;
var brickWidth = 45;
var brickHeight = 20;
var brickPadding = 8;
var brickOffsetTop = 30;
var brickOffsetLeft = 30;

//2D POLE KOCIEK
// KAŽDÁ KOCKA JE OBJEKT
var bricks = [];
for(var c = 0; c < brickColumnCount; c++){
    bricks[c] = [];
    for(var r = 0; r < brickRowCount; r++){
        bricks[c][r] = {x: 0, y: 0, status: 1};
    }
}


// funkcia pre pohyb pomocou myši
function mouseMoveHandler(e){
    var relativeX = e.clientX - canvas.offsetLeft;
    if(relativeX > 0 && relativeX < canvas.width){
        paddle.paddleX = relativeX - paddle.paddleWidth/2;
    }
}

// detekcia kolízie loptičky s tehličkami
function collisionDetection() {
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
            var b = bricks[c][r];
            if(b.status == 1) {
                if(ball.x > b.x && ball.x < b.x+brickWidth && ball.y > b.y && ball.y < b.y+brickHeight) {
                    ball.dy = -ball.dy;
                    b.status = 0;       // AK TRAFÍ LOPTIČKA TEHLIČKU TAK SA UŽ NEVYKRESLÍ
                    
                    //ZVUK ROZBITIA KOCKY
                    if(zvuky){
                        zvuk_rozbitia.load();
                        zvuk_rozbitia.play();
                    }

                    // POČÍTANIE SKÓRE
                    rozbite++;
                    score = score + ( 10 * nasobokSkore) ;

                    if(rozbite == brickRowCount * brickColumnCount){  // AK ZNIČÍM VŠETKY GULIČKY
                        //alert("YOU WIN !");
                        // ZVUK VÝHRY
                        if(zvuky){
                            zvuk_win.load();
                            zvuk_win.play();
                        }
                        rozbite = 0;
                        stav_vyhra();
                        cancelAllAnimationFrames(globalID);
                        /////////////////
                        for(let c = 0; c < brickColumnCount; c++){
                            for(let r = 0; r < brickRowCount; r++){
                                b = bricks[c][r];
                                b.status = 1;
                            }
                        }
                        //x = canvas.width/2;
                        ball.x = canvas.width/2;
                        //y = canvas.height-30;
                        ball.y = canvas.height-30;
                        ball.dx = 0;
                        ball.dy = 0;
                        //dx = 0;
                        //dy = 0;
                        paddle.paddleX = (canvas.width-paddle.paddleWidth) / 2;
                        /////////////////
                        //document.location.reload();
                        //clearInterval(interval);
                    }
                }
            }
        }
    }
}

/////////////////////////////////////
/////////////// VIEW ////////////////
/////////////////////////////////////

// SKÓRE
const skoreItem = document.getElementById('score');
const skoreItem2 = document.getElementById('score2');
const skoreItem3 = document.getElementById('score3');

// výpis skóre
function drawScore(){
    //ctx.font = "16px Arial";
    //ctx.fillStyle = "#da54db";
    //ctx.fillText("Score: "+score, 8, 20);
    skoreItem.innerHTML = "skóre " + score;
    skoreItem2.innerHTML = score;   // VYPISE SCORE DO #STRATENIE-ZIVOTA
    skoreItem3.innerHTML = score;   // VYPISE SCORE DO #GAME-OVER
}

function drawImageToCanvas(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    var GameOverImg = document.getElementById("GameOverImage");
    ctx.drawImage(GameOverImg, (canvas.width/2)-155/2, (canvas.height/2)-256/2, 155, 256);
}

function drawLives(){  
    //ctx.font = "16px Arial";
    //ctx.fillStyle = "#da54db";
    //ctx.fillText("Lives: "+lives, canvas.width-65, 20);
    let element = document.getElementById('zivoty');    // VYMAZE VSETKY SRDIECKA V #zivoty
    while (element.firstChild){
        element.removeChild(element.firstChild);
    }
    for (let index = 0; index < lives; index++) {       // VYTVORI TOLKO <3 kolko je zivotov (lives)
        var img = document.createElement('img');
        img.src = 'images/icons/srdce.png';
        document.getElementById('zivoty').appendChild(img);     // V #HRA
        //document.getElementById('zivoty2').appendChild(img);    // V #STRATENIE_ZIVOTA      
    }
    let element2 = document.getElementById('zivoty2');    // VYMAZE VSETKY SRDIECKA V #zivoty
    while (element2.firstChild){
        element2.removeChild(element2.firstChild);
    }
    for (let index = 0; index < lives; index++) {       // VYTVORI TOLKO <3 kolko je zivotov (lives)
        var img = document.createElement('img');
        img.src = 'images/icons/srdce.png';
        //document.getElementById('zivoty2').appendChild(img);     // V #HRA
        document.getElementById('zivoty2').appendChild(img);    // V #STRATENIE_ZIVOTA      
    }
}

function drawBall(){    // kreslenie guličky
    ctx.beginPath();
    //ctx.arc(x, y, ballRadius, 0, Math.PI*2);
    ctx.arc(ball.x, ball.y, ball.ballRadius, 0, Math.PI*2);

    ctx.fillStyle = "#d438d5";
    ctx.fill();
    ctx.closePath();
}

function drawPaddle(){  // kreslenie pádla
    ctx.beginPath();
    ctx.rect(paddle.paddleX, canvas.height-paddle.paddleHeight, paddle.paddleWidth, paddle.paddleHeight);
    ctx.fillStyle = "#d438d5";
    ctx.fill();
    ctx.closePath();
}
function drawBricks() { // kreslenie tehličiek
    for(var c = 0; c < brickColumnCount; c++) {
        for(var r = 0; r < brickRowCount; r++) {
            if(bricks[c][r].status == 1){
                var brickX = (c * (brickWidth+brickPadding)) + brickOffsetLeft;
                var brickY = (r * (brickHeight+brickPadding)) + brickOffsetTop;
                bricks[c][r].x = brickX;
                bricks[c][r].y = brickY;
                ctx.beginPath();
                ctx.rect(brickX, brickY, brickWidth, brickHeight);
                
                if(r==0){
                    ctx.fillStyle = "#c129c2";
                }
                else if(r==1){
                    ctx.fillStyle = "#d438d5";
                }
                else if(r==2){
                    ctx.fillStyle = "#da54db";
                }
                else if(r==3){
                    ctx.fillStyle = "#e070e1";
                }
                else{
                    ctx.fillStyle = "#e68ce7";
                }
                ctx.fill();
                ctx.closePath();
            }
        }
    }
}

// JE UPLNE HORE // var globalID; // PRE requestAnimationFrame
function draw(){
    // drawing code...
    ctx.clearRect(0, 0, canvas.width, canvas.height)    // vyčistí plátno
    drawBall();                                         // kreslenie guličky
    drawPaddle();                                       // kreslenie pádla
    drawScore();
    drawLives();
    collisionDetection();                               // kontrola kolizie gulicky a tehličky
    drawBricks();                                       // kreslenie tehličiek

    // KOLÍZIA SO STENAMI
    if(ball.x + ball.dx > canvas.width-ball.ballRadius || ball.x + ball.dx < ball.ballRadius) {   // KOLÍZIA S PRAVOU A ĽAVOU STENOU
        ball.dx = -ball.dx;
        // ZVUK ODRAZU
        if(zvuky){
            zvuk_odraz.load();
            zvuk_odraz.play();
        }
    }
    if(ball.y + ball.dy < ball.ballRadius) {  // KOLÍZIA S HORNOU A DOLNOU STENOU
        ball.dy = -ball.dy;
        // ZVUK ODRAZU
        if(zvuky){
            zvuk_odraz.load();
            zvuk_odraz.play();
        }
    } else if(ball.y + ball.dy > canvas.height-ball.ballRadius){   
        if(ball.x > paddle.paddleX && ball.x < paddle.paddleX + paddle.paddleWidth){  // KOLIZIA S PÁDLOM
            ball.dy = -ball.dy;
            // ZVUK ODRAZU
            if(zvuky){
                zvuk_odraz.load();
                zvuk_odraz.play();
            }  
        }
        else{ // KOLIZIA S DOLNOU STENOU
            lives--;

            //user1.nahrateScore = score;
            //console.log("USER1 SKORE >>> "+user1.nahrateScore);
            // ZVUK GAME OVER
            if(zvuky){
                zvuk_gameover.load();
                zvuk_gameover.play();
            }  
            if(!lives){                             // GAME OVER ÚPLNÝ
                //alert("GAME OVER !");
                cancelAllAnimationFrames(globalID);
                observer.notify(score);       // OBSERVER -> notify 
                ball.dx = 0;
                ball.dy = 0;
                /// SEM
                drawImageToCanvas();
                setTimeout(gameOver, 2000);
                return;
                //gameOver();
                //document.location.reload();   //ZNOVA NAČÍTA STRÁNKU (reloadne)
            }
            else{                                   // AK MI UBERIE ŽIVOT
                //console.log("Odobralo život !");

                stav_stratenie_zivota();
                cancelAllAnimationFrames(globalID);

                //x = canvas.width/2;
                ball.x = canvas.width/2;
                //y = canvas.height-30;
                ball.y = canvas.height-30;
                ball.dx = 0;
                ball.dy = 0;
                paddle.paddleX = (canvas.width-paddle.paddleWidth) / 2;
            }
        }
    }

    ball.x += ball.dx;
    ball.y += ball.dy;
    //x += dx;
    //y += dy;

    //pohyb pádla
    if(paddle.rightPressed) {
        paddle.paddleX += 5;
        if (paddle.paddleX + paddle.paddleWidth > canvas.width){
            paddle.paddleX = canvas.width - paddle.paddleWidth;
        }
    }
    else if(paddle.leftPressed) {
        paddle.paddleX -= 5;
        if (paddle.paddleX < 0){
            paddle.paddleX = 0;
        }
    }

    globalID = requestAnimationFrame(draw);
    //requestAnimationFrame(draw);
}   //koniec draw()


/////////////////////////////////////
//////////// CONTROLLER /////////////
/////////////////////////////////////
document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.addEventListener("mousemove", mouseMoveHandler, false);

// funkcie pre stlačenie kláves
function keyDownHandler(e){
    if(e.key == "Right" || e.key == "ArrowRight"){
        paddle.rightPressed = true;
    }
    if(e.key == "Lefit" || e.key == "ArrowLeft"){
        paddle.leftPressed = true;
    }
}
function keyUpHandler(e){
    if(e.key == "Right" || e.key == "ArrowRight"){
        paddle.rightPressed = false;
    }
    if(e.key == "Lefit" || e.key == "ArrowLeft"){
        paddle.leftPressed = false;
    }
}

// AK STRATIM ZIVOT A KLIKNEM NA BUTTON ZNOVU
document.getElementById("strateny_zivot_znovu").addEventListener("click", strateny_zivot_znovu);
function strateny_zivot_znovu(){
    //console.log("clicked -> ZNOVU");
    
    stratenieToHra();
    ball.dx = ball.speed_dx;
    ball.dy = ball.speed_dy;

    cancelAllAnimationFrames(globalID);
    mainLoop();

}
// AK STRATIM ZIVOT A KLIKNEM NA BUTTON NAROČNOSTI
document.getElementById("strateny_zivot_narocnosti").addEventListener("click", strateny_zivot_narocnosti);
function strateny_zivot_narocnosti(){
    //console.log("clicked -> NAROČNOSŤ");
    stratenieToNarocnost();
}

/////////////////////////////////////

    function mainLoop(){
        /*setInterval(function() {
            globalID = requestAnimationFrame(draw);
        }, 1000);*/
        globalID = requestAnimationFrame(draw);        
    }
    function end_loop(){
        cancelAnimationFrame(globalID);
    }

    
    //button = document.getElementById("zacni")
    //canvas = document.querySelector("canvas")
    //ctx = canvas.getContext("2d")
    
    document.getElementById("narocnosti0").addEventListener('click', function() {
        cancelAllAnimationFrames(globalID);
        // NASTAVENIE RYCHLOSTI GULIČKY
        ball.speed_dx = 2;
        ball.speed_dy = -2;
        ball.dx = 2;
        ball.dy = -2;
        nasobokSkore = 1;
        paddle.paddleX = (canvas.width-paddle.paddleWidth) / 2;
        mainLoop();
        //draw();

        //requestAnimationFrame(mainLoop) // TOTO ZACNE HNED
        //document.getElementById("skonci").onclick = end
    });
    document.getElementById("narocnosti1").addEventListener('click', function() {
        cancelAllAnimationFrames(globalID);
        // NASTAVENIE RYCHLOSTI GULIČKY
        ball.speed_dx = 3,6;
        ball.speed_dy = -3,6;
        ball.dx = 3,6;
        ball.dy = -3,6;
        nasobokSkore = 3;
        paddle.paddleX = (canvas.width-paddle.paddleWidth) / 2;
        mainLoop();
        //draw();
    });
    document.getElementById("narocnosti2").addEventListener('click', function() {
        cancelAllAnimationFrames(globalID);
        // NASTAVENIE RYCHLOSTI GULIČKY
        ball.speed_dx = 4;
        ball.speed_dy = -4;
        ball.dx = 4;
        ball.dy = -4;
        nasobokSkore = 5;
        paddle.paddleX = (canvas.width-paddle.paddleWidth) / 2;
        mainLoop();
        //draw();
    });

    // AK STLACIM PAUZU TAK ZASTAVI
    document.getElementById("skonci").addEventListener('click', function() {
        //end_loop();
        cancelAllAnimationFrames(globalID);
    });
    // AK KLIKNEM NA #pauza (je na celu obrazovku) tak pokracuje
    document.getElementById("pauza").addEventListener('click', function() {
        mainLoop();
        //setTimeout(gameOver, 1500);  // AK SA VRATIM Z PAUZY TAK MI DA GAMEOVER
    });
    
  
    
}); // POCKA POKIAL SA NACITA CELA STRANKA  