<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}



$tech_page = array(
    "SK" => array(
        "title" => "ROZHRANIE",

        "nadpis_1" => "Používateľské rozhranie",
        "text_1" => "Pred časom som bol veľký zástanca Androidu. Android je lepší vo všetkom, hovoril som. Vtedy keď som nemal ani náhodou pravdu, nepoznal som iPhone a ani nikdy ho nevyskúšal. Tvrdil som to iba z dôvodu, čo mám ja to je predsa najlepšie...",
        "text_2" => "Postupom času ma kamoš prehovoril aby som si kúpil iPhone, rozhodol som sa pre iPhone 6, očaril ma. Malo to však niekoľko problémov s chybným modelom a kopec reklamácií. Rozhodol som sa pre to pre iPhone 6S. Na telefón som si zvykol behom jedného dňa. Intuitívnosť používania, nastavenia, rýchlosť a žiadne sekanie. Tieto pojmy pre mňa nabrali celkom iný zmysel.",

        "nadpis_3" => "operačný systém iOS je jednoducho zážitok",
        "text_3" => "Úžasná synchronizácia s iCloud-om, fotogaléria s podrobnosťami o fotkách a mnoho ďalšieho. Páči sa mi napríklad to, že v AppleStore nájdete iba overené aplikácie, a nie ako tomu je u konkurencie, kde si može nahrať APPku kde-aký šašo...",
        "text_4" => "Celé používateľské rozhranie je úžasne prepracované a je veľmi pekne dizajnovo spracované. Nehovoriac o prepojení s príslušenstvom. S odstupom času a získanými skúsenosťami by som už APPLE zariadenia určite nevymenil. Pri takomto telefóne si časom začnete všímať detaily, o ktorých ste pri iných telefónoch ani nedokázali všimnúť. No používaním a porovnávaním s ostatnými prídete na kopec chyb, ktoré majú \"iní\", no vy sa môžete len spokojne zasmiať.",
        "text_5" => "Úžasná je aj podpora zo strany APPLE a veľmi rozšírená komunita užívateľov. Ak sa nájde nejaká chyba alebo niečo neviete na svojom telefóne nastaviť. Skrátka všetko je na internete, do vyhľadávača napíšete čo chcete a vo väčšine prípadoch sa dopátrate k riešeniu na originálnej apple stránke.",

        "nadpis_6" => "ukážky používateľského rozhrania",

        "zvonenie" => "ZVONENIA TELEFÓNU",
        "notifikacie" => "ZVUKY UPOZORNENENÍ",
        "alarm" => "ZVUKY BUDÍKOV"
    ),
    "EN" => array(
        "title" => "USER INTERFACE",

        "nadpis_1" => "USER INTERFACE",
        "text_1" => "Some time ago I was a big fan of Android. Android is better in everything, I said. I didn't know the iPhone at all and never even tried it. I said it only because of what I have, it's still the best ...",
        "text_2" => "Over time, a friend tell me to buy an iPhone, I opted for the iPhone 6, enchanted me. However, it had several problems with the individual model. I opted for it for the iPhone 6S. I got used to the phone in one day. Intuitive use, settings, speed and no cutting. These concepts took on a completely different meaning for me.",

        "nadpis_3" => "The iOS operating system is simply an experience",
        "text_3" => "Amazing sync with iCloud, photo gallery with photo details and much more. For example, I like the fact that in AppleStore you will find only proven applications, and not as in the case of the competition, where you can upload an APP.",
        "text_4" => "The whole user interface is amazingly redesigned and is very nice. Not to mention the connection with accessories. With the passage of time and gained experience, I would never replace APPLE devices. Such a phone, you will eventually begin to notice details that you have not know before with other phones.",
        "text_5" => "But, by using and comparing with others, you will realize a lot's of bugs, problems who have \"others\" , but you can only laugh happily, because your iPhone is three steps forward. The support from APPLE and the very extended community of users are also amazing. If you have some error, find open theme on the Internet or type what you want into a search engine and in most cases you will find a solution on the original Apple website.",

        "nadpis_6" => "USER INTERFACE EXAMPLES",

        "zvonenie" => "RING EXAMPLES",
        "notifikacie" => "NOTIFICATION EXAMPLES",
        "alarm" => "ALARM EXAMPLES"
    )
);
?>

<!DOCTYPE html>
<html lang="<?php echo (strtolower($jazyk));?>">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="icon" href="../images/favicon.png" sizes="16x16" type="image/png">

    <!-- GOOGLE FONTS - OPEN SANS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">

    <!-- MOJE CSS -->
    <link rel="stylesheet" href="../styles/style.css">

    <title><?php echo $tech_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body class="rozh">

    <?php include 'header.php';?>

    <!-- SEKCIA ROZHRANIE -->
    <div class="container-fluid">
        <section class="rozhranie">
            <h2><?php echo $tech_page[$jazyk]["nadpis_1"];?></h2>
            <p><?php echo $tech_page[$jazyk]["text_1"];?></p>
            <p><?php echo $tech_page[$jazyk]["text_2"];?></p>
        </section>
    </div>
    <div class="container-fluid">
        <section>
            <div class="rozhranie-first visible">
                <video autoplay muted controls>
                    <source src="../images/rozhranie/1_rozhranie_video.mp4" type="video/mp4">
                </video>
                <div class="text">
                    <p class="nadpis"><?php echo $tech_page[$jazyk]["nadpis_3"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_3"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_4"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_5"];?></p>
                </div>
                <video autoplay muted controls>
                    <source src="../images/rozhranie/2_rozhranie.mp4" type="video/mp4">
                </video>
            </div>
            <!-- HIDDEN -->
            <div class="rozhranie-first hidden">
                <div class="video-sec">
                    <video autoplay muted controls>
                        <source src="../images/rozhranie/1_rozhranie_video.mp4" type="video/mp4">
                    </video>
                    <video autoplay muted controls>
                        <source src="../images/rozhranie/2_rozhranie.mp4" type="video/mp4">
                    </video>
                </div>
                <div class="text">
                    <p class="nadpis"><?php echo $tech_page[$jazyk]["nadpis_3"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_3"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_4"];?></p>
                    <p><?php echo $tech_page[$jazyk]["text_5"];?></p>
                </div>
            </div>
            <!-- HIDDEN -->
        </section>
    </div>
    <div class="container-fluid-color">
        <section class="rozhranie-ukazky">
            <h2><?php echo $tech_page[$jazyk]["nadpis_6"];?></h2>

            <div class="rozhranie-galeria">
                <div class="item">
                    <img src="../images/rozhranie/3_ApplePay.gif" alt="APPLE PAY GIF">
                </div>
                <div class="item">
                    <img src="../images/rozhranie/4_MAIN_PAGE.PNG" alt="hlavná obrazovka">
                </div>
                <div class="item">
                    <img src="../images/rozhranie/5_rozhranie.PNG" alt="hlavná obrazovka">
                </div>
                <div class="item">
                    <img src="../images/rozhranie/6_NASTAVENIA.PNG" alt="nastavenia">
                </div>
                <div class="item">
                    <img src="../images/rozhranie/7_rozhranie.gif" alt="používateľské rozhranie GIF">
                </div>
            </div>
        </section>
    </div>
    <div class="container-fluid">
        <section class="rozhranie-zvuky">
            <div class="item">
                <p><?php echo $tech_page[$jazyk]["zvonenie"];?></p>
                <ul>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/zvonenie1.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/zvonenie2.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                </ul>
            </div>
            <div class="item">
                <p><?php echo $tech_page[$jazyk]["notifikacie"];?></p>
                <ul>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/notification1.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/notification2.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                </ul>
            </div>
            <div class="item">
                <p><?php echo $tech_page[$jazyk]["alarm"];?></p>
                <ul>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/budik1.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                    <li>
                        <audio controls>
                            <source src="../images/rozhranie/SOUNDS/budik2.mp3" type="audio/mpeg">
                        </audio>
                    </li>
                </ul>
            </div>
            
        </section>
    </div>


    <?php include 'footer.php';?>

</body>
</html>