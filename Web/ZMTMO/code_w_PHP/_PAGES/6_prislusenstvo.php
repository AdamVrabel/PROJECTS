<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}



$accessories_page = array(
    "SK" => array(
        "title" => "PRÍSLUŠENSTVO",
        "nadpis1" => "PRÍSLUŠENSTVO",

        "air_pods_nadpis" => "air pods",
        "air_pods_text1" => "AirPods predstavujú úplne nové poňatie bezdrôtových slúchadiel. Stačí ich vybrať z puzdra a hneď ich môžeš používať s iPhonom, Apple Watch, iPadom alebo Macom. Po jednoduchom nastavení jedným kliknutím fungujú AirPods úplne kúzelne. Automaticky sa zapínajú a pripájajú. Dokonca spoznajú, keď ich máš v ušiach, a pozastavia prehrávanie, keď ich vyberieš.",
        "air_pods_text2" => "Keď chceš zmeniť hlasitosť, preskočiť na inú skladbu, niekomu zavolať alebo vyhľadať trasu, len povedz \"Hey Siri\" a pokračuj tým, čo chceš. Môžeš mať v uchu len jeden AirPod, alebo si nasadiť oba. A pri počúvaní hudby alebo podcastov zapneš prehrávanie alebo preskočíš dopredu dvojitým klepnutím.",

        "watch_nadpis" => "apple watch",
        "watch_text" => "Apple Watch Series 6 disponujú širokou škálou funkcií, vďaka čomu môžem sledovať svoje celodenné aktivity ako napríklad počet prejdených krokov či množstvo spálených kalórií. Keďže dokážu merať kvalitu spánku, nemusím si ich skladať zo zápästia ani počas odpočinku. Inovatívna aplikácia Spánok, mi pomôže dosiahnúť lepší a pravidelný spánok, čo je pre vaše zdravie naozaj dôležité.",

        "mag_nadpis" => "MAG SAFE",
        "mag_text" => "Pohodlné bezdrôtové nabíjanie s extra veľkým výkonom až 15 W si teraz môžu užívať najmä majitelia iPhone 12 a iPhone 12 Pro. Presne na tieto modely je v nabíjačke MagSafe pripravené magnetické zarovnanie, ktoré telefón prichytí v ideálnej polohe. Nabíjačka je však kompatibilná aj so štandardom Qi, takže pohodlne nabijete nielen staršie modely iPhone, ale aj smartfóny iných značiek či AirPods.",

        "adapter_nadpis" => "ADAPTÉR NA NABÍJANIE",
        "adapter_text" => "Nabíjačka do siete Apple dokáže veľmi rýchlo nabiť tablet alebo telefón. Nabíjanú elektroniku pripojíte prostredníctvom výstupu s konektorom USB-C. Výkon nabíjačky Apple sa vyšplhal na 20 W."
    ),
    "EN" => array(
        "title" => "ACCESSORIES",
        "nadpis1" => "ACCESSORIES",

        "air_pods_nadpis" => "air pods",
        "air_pods_text1" => "AirPods represent a completely new concept of wireless headphones. Just remove them from the case and you can immediately use them with iPhone, Apple Watch, iPad or Mac.With one-click setup, AirPods work magically. They turn on and connect automatically. They will even recognize when you wearing them in your ears and pause playback when you tap on them.",
        "air_pods_text2" => "If you want to change the volume, skip to another song, call someone or find a route, just say \"Hey Siri\" and continue what you want. You can have only one AirPod in your ear, or put on both. And when listening to music or podcasts, double-tap to play or skip forward.",

        "watch_nadpis" => "apple watch",
        "watch_text" => "The Apple Watch 6 Series has a wide range of features, so I can track my all-day activities such as the number of steps taken or the number of calories burned. Since they can measure the quality of sleep, I don't have to compose them from my wrist even while resting. The innovative Sleep application will help me achieve a better and regular sleep, which is really important for your health.",

        "mag_nadpis" => "MAG SAFE",
        "mag_text" => "Convenient wireless charging with an extra large power of up to 15 W can now be enjoyed especially by owners of the iPhone 12 and iPhone 12 Pro. It is precisely for these models in the MagSafe charger that a magnetic alignment is prepared, which will hold the phone in the ideal position. However, the charger is also compatible with the Qi standard, so you can comfortably charge not only older iPhone models, but also smartphones from other brands or AirPods.",

        "adapter_nadpis" => "CHARGING ADAPTER",
        "adapter_text" => "The Apple network charger can charge a tablet or phone very quickly. You connect the charging electronics via an output with a USB-C connector. Apple charger power has climbed to 20 W."
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

    <title><?php echo $accessories_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>

    <!-- PRÍSLUŠENSTVO -->
    <div class="container-fluid">
        <section class="prislusenstvo">
            <h2><?php echo $accessories_page[$jazyk]["nadpis1"];?></h2>

            <div class="box first_box">
                <div class="image">
                    <img src="../images/prislusenstvo/1_AIRPODS.jpg" alt="AirPods">
                </div>
                <div class="text-wrap">
                    <h3><?php echo $accessories_page[$jazyk]["air_pods_nadpis"];?></h3>
                    <p><?php echo $accessories_page[$jazyk]["air_pods_text1"];?></p>
                    <p><?php echo $accessories_page[$jazyk]["air_pods_text2"];?></p>
                </div>
            </div>

            <div class="box flex-reverse">
                <div class="image">
                    <img src="../images/prislusenstvo/2_APPLEWATCH.png" alt="AppleWatch">
                </div>
                <div class="text-wrap">
                    <h3><?php echo $accessories_page[$jazyk]["watch_nadpis"];?></h3>
                    <p><?php echo $accessories_page[$jazyk]["watch_text"];?></p>
                </div>
            </div>
            
            <div class="wrapper">
                <div class="box-half">
                    <h3><?php echo $accessories_page[$jazyk]["mag_nadpis"];?></h3>
                    <p><?php echo $accessories_page[$jazyk]["mag_text"];?></p>
                </div>
                <div class="box-half">
                    <h3><?php echo $accessories_page[$jazyk]["adapter_nadpis"];?></h3>
                    <p><?php echo $accessories_page[$jazyk]["adapter_text"];?></p>
                </div>
            </div>

            <div class="galery-prislusenstvo">
                <div class="photo">
                    <img src="../images/prislusenstvo/4_APPLE WATCH.png" alt="APPLE WATCH">
                </div>
                <div class="photo ceter_airpods">
                    <img src="../images/prislusenstvo/3_AIRPODS2.jpg" alt="AIR PODS">
                </div>
                <div class="photo">
                    <img src="../images/prislusenstvo/5_APPLE WATCH3.png" alt="APPLE WATCH">
                </div>
            </div>

        </section>
    </div>

    <?php include 'footer.php';?>

</body>
</html>