<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}



$compare_page = array(
    "SK" => array(
        "title" => "POROVNANIE",

        "table_head_12" => array("FARBA", "KAPACITA", "ROZMERY", "HMOTNOSŤ", "DISPLEJ", "ROZLÍŠENIE", "ODOLNOSŤ", "KAMERA", "VIDEO", "PREDNÁ KAMERA<br>(kamera TrueDepth)", "ČIP", "FACE ID", "APPLE PAY", "BATÉRIA", "SENZORY", "OBSAH BALENIA"),

        "table_text_12" => array("čierna, biela, zelená, modrá, fialová, (PRODUCT)<sup>RED</sup>", "64 GB, 128 GB, 256 GB", "výška: 146,7mm <br>šírka: 71,5mm <br>hrúbka: 7,4mm", "162 gramov", "6,1\" Super Retina XDR <br> OLED displej <br> True Tone <br>Široké farebné spektrum (P3) <br>Haptic Touch <br>Kontrastný pomer 2 000 000 : 1 <br>Maximálny jas 625 nitov, 1 200 nitov (HDR) <br>", "2 532 x 1 170 pixelov pri 460 pixeloch na palec", "IP68 (maximálna hĺbka 6 metrov do 30 minút) podľa normy IEC 60529", "12 Mpx sústava dvoch kamier <br>Širokouhlý objektív:  clona ƒ/1,6 <br>Ultraširokouhlý objektív: clona ƒ/2,4", "Nahrávanie HDR v Dolby Vision - 30 FPS <br> Nahrávanie 4K videa pri 24, 25, 30 alebo 60 FPS <br> Nahrávanie 1 080p HD videa pri 25, 30 alebo 60 FPS <br> Nahrávanie 720p HD videa pri 30 FPS <br>Optická stabilizácia obrazu pre video (širokouhlý objektív) <br>Kinematografická stabilizácia videa (4K, 1 080p a 720p) <br>2x optický zoom <br>Až 3x digitálne priblíženie <br>Jasnejší blesk True Tone <br>Spomalené video 1 080p pri 120 FPS alebo 240 FPS <br>Časozberné video so stabilizáciou <br>Nočný režim s časozberným videom <br>Formáty nahrávaného videa: HEVC a H.264", "12 Mpx kamera <br>Clona ƒ/2,2 <br>Animoji a Memoji <br>Nočný režim <br>Deep Fusion <br>Smart HDR 3 s detekciou scény <br>Nahrávanie HDR v Dolby Vision - 30 FPS <br>Nahrávanie 4K videa pri 24, 25, 30 alebo 60 FPS <br>Nahrávanie 1 080p HD videa pri 25, 30 alebo 60 FPS <br>Spomalené video 1 080p pri 120 FPS alebo 240 FPS <br> Časozberné video so stabilizáciou <br>Nočný režim s časozberným videom <br>Rozšírený dynamický rozsah pre video pri 30 snímkach/s <br>Kinematografická stabilizácia videa (4K, 1 080p a 720p) <br>Fotky a Live Photos so širokým farebným spektrom <br>Korekcia šošoviek <br>Blesk Retina <br>Automatická stabilizácia obrazu <br>Režim nepretržitého snímania", "Čip A14 Bionic - Neural Engine", "Podporované kamerou TrueDepth na rozpoznávanie tvári", "Platby iPhonom pomocou Face ID v obchodoch, v aplikáciách a na webe", "Prehrávanie videa: až 17 hodín <br>Prehrávanie zvuku: až 65 hodín <br>Lítiovo-iónová batéria <br>Bezdrôtové nabíjanie MagSafe do 15 W <br>Bezdrôtové nabíjanie Qi do 7,5 W <br>Rýchle nabíjanie až na 50 % za 30 minút (pomocou adaptéra s výkonom 20 W alebo vyšším, predáva sa samostatne)", "Face ID <br>Barometer <br>Trojosový gyroskop <br>Akcelerometer <br>Senzor blízkosti <br>Senzor okolitého svetla", "iPhone s iOS 14 <br>Kábel s konektormi USB‑C a Lightning <br>Dokumentácia<br><br>"),


        "table_head_6S" => array("FARBA", "KAPACITA", "ROZMERY", "HMOTNOSŤ", "DISPLEJ", "ROZLÍŠENIE", "ODOLNOSŤ", "KAMERA", "VIDEO", "PREDNÁ KAMERA<br>(kamera TrueDepth)", "ČIP", "TOUCH ID", "APPLE PAY", "BATÉRIA", "SENZORY", "OBSAH BALENIA"),

        "table_text_6S" => array("strieborná, kozmická sivá, zlatá, rúžovozlatá", "32 GB, 64GB, 128GB", "výška: 138,3mm <br>šírka: 67,1mm <br>hrúbka: 7,1mm", "143 gramov", "4,7\" Displej Retina HD <br>IPS displej <br>3D Touch <br>plnohodnotný štandard sRGB <br>Maximálny jas 500 nitov <br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "1 334 × 750 pixlov pri 326 pixloch na palec", "------", "12 Mpx kamera <br>Širokouhlý objektív:  clona ƒ/2,2<br class=\"no-see\">", "Nahrávanie 4K videa pri 25 alebo 30 FPS <br>Nahrávanie 1 080p HD videa pri 25, 30 alebo 60 FPS <br>Nahrávanie 720p HD videa pri 30 FPS <br>Až 3x digitálne priblíženie <br>Spomalené video 1 080p pri 120 FPS  a 720p video pri 240 FPS <br>Časozberné video so stabilizáciou <br>Formáty nahrávaného videa: HEVC a H.264<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "5 Mpx kamera <br>Clona ƒ/2,2 <br>Blesk Retina <br>HDR pre fotky <br>Nahrávanie 720p HD videa pri 30 FPS<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "Čip A9", "Senzor odtlačku prsta druhej generácie v tlačidle Domov", "Platby iPhonom pomocou Face ID v obchodoch, v aplikáciách a na webe", "Prehrávanie videa: až 11 hodín <br>Prehrávanie zvuku: až 50 hodín <br>Lítiovo-iónová batéria<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "Barometer <br>Trojosový gyroskop <br>Akcelerometer <br>Senzor blízkosti <br>Senzor okolitého svetla <br class=\"no-see\"><br class=\"no-see\">", "iPhone s iOS 14 <br>Adaptér do elektrickej siete <br>Kábel s konektormi USB‑A a Lightning <br>Slúchadlá EarPods (3.5mm JACK) <br>Dokumentácia")
    ),
    "EN" => array(
        "title" => "COMPARSION",

        "table_head_12" => array("COLOR", "CAPACITY", "DIMENSIONS", "WEIGHT", "DISPLAY", "RESOLUTION", "RESISTANCE", "CAMERA", "VIDEO", "FRONT CAMERA <br>(TrueDepth)", "CHIP", "FACE ID", "APPLE PAY", "BATTERY", "SENSORS", "PACKAGE CONTENTS"),

        "table_text_12" => array("black, white, green, blue, purple, (PRODUCT)<sup>RED</sup>", "64 GB, 128 GB, 256 GB", "height: 146,7mm <br>width: 71,5mm <br>thickness: 7,4mm", "162 grams", "6,1\" Super Retina XDR <br>OLED display <br>True Tone <br>Wide color spectrum (P3) <br>Haptic Touch <br>Contrast ratio 2 000 000 : 1 <br>Maximum brightness 625 nits, 1,200 nits (HDR)", "2 532 x 1 170 pixelov on 460 pixel per inch", "IP68 (maximum depth 6 meters within 30 minutes) according to IEC 60529", "12 Mpx system of two cameras <br>Wide angle lens: aperture ƒ / 1.6 <br>Ultra-wide lens: aperture ƒ / 2.4", "HDR recording in Dolby Vision - 30 FPS <br>4K video recording at 24, 25, 30 or 60 FPS <br>Record 1,080p HD video at 25, 30 or 60 FPS <br>720p HD video recording at 30 FPS <br>Optical image stabilization for video (wide angle lens) <br>Cinematographic video stabilization (4K, 1,080p and 720p) <br>2x optical zoom <br>Up to 3x digital zoom <br>Brighter True Tone flash <br>Slow motion video 1,080p at 120 FPS or 240 FPS <br>Time-lapse video with stabilization <br>Night mode with time-lapse video <br>Recording video formats: HEVC and H.264", "12 Mpx camera <br> Aperture ƒ / 2.2 <br>Anime and Memos <br>Night mode <br>Deep Fusion <br>Smart HDR 3 with scene detection <br>HDR recording in Dolby Vision - 30 FPS <br>4K video recording at 24, 25, 30 or 60 FPS <br>Record 1,080p HD video at 25, 30 or 60 FPS <br>Slow motion video 1,080p at 120 FPS or 240 FPS <br>Time-lapse video with stabilization <br>Night mode with time-lapse video <br>Extended dynamic range for video at 30 fps <br>Cinematographic video stabilization (4K, 1,080p and 720p) <br>Photos and Live Photos with a wide color spectrum <br>Lens correction <br>Lightning Retina <br>Automatic image stabilization <br>Continuous shooting mode", "CHIP A14 Bionic - Neural Engine", "Supported by TrueDepth camera for face recognition", "IPhone payments by ApplePay using Face ID in stores, apps, and the web", "Video playback: up to 17 hours <br>Audio playback: up to 65 hours <br>Lithium-ion battery <br>MagSafe wireless charging up to 15 W <br>Wireless Qi charging up to 7.5 W <br>Fast charging up to 50% in 30 minutes (using an adapter with a power of 20 W or higher, sold separately)", "Face ID <br>Barometer <br>Triaxial gyroscope <br>Accelerometer <br>Proximity sensor <br>Ambient light sensor", "iPhone with iOS 14 <br>Cable with USB ‑ C and Lightning connectors <br>Documentation<br><br>"),


        "table_head_6S" => array("COLOR", "CAPACITY", "DIMENSIONS", "WEIGHT", "DISPLAY", "RESOLUTION", "RESISTANCE", "CAMERA", "VIDEO", "FRONT CAMERA", "CHIP", "TOUCH ID", "APPLE PAY", "BATTERY", "SENSORS", "PACKAGE CONTENTS"),

        "table_text_6S" => array("silver, cosmic gray, gold, pink-gold", "32 GB, 64GB, 128GB", "height: 138,3mm <br>width: 67,1mm <br>thickness: 7,1mm", "143 grams", "4,7\" Retina Display HD <br>IPS display <br>3D Touch <br>full sRGB standard <br>Maximum brightness 500 nits<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "1 334 × 750 pixel to 326 pixel on inch", "------<br class=\"no-see\"><br class=\"no-see\">", "12 Mpx camera <br>Wide angle lens: aperture ƒ/2,2<br class=\"no-see\"><br class=\"no-see\">", "4K video recording at 25 or 30 FPS <br>Record 1,080p HD video at 25, 30 or 60 FPS <br>720p HD video recording at 30 FPS <br>Up to 3x digital zoom <br>Slow motion video 1,080p at 120 FPS and 720p video at 240 FPS <br>Time-lapse video with stabilization <br>Recording video formats: HEVC and H.264<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "5 Mpx kamera <br>Aperture ƒ/2,2 <br>Lightning Retina <br>HDR for photos <br>720p HD video recording at 30 FPS<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "Chip A9", "Second generation fingerprint sensor in the Home button.", "IPhone payments by ApplePay using Face ID in stores, apps, and the web.", "Video playback: up to 11 hours <br>Audio playback: up to 50 hours <br>Lithium-ion battery<br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\"><br class=\"no-see\">", "Barometer <br>Triaxial gyroscope <br>Accelerometer <br>Proximity sensor <br>Ambient light sensor<br class=\"no-see\"><br class=\"no-see\">", "iPhone with iOS 14 <br>Adapter <br>Cable with USB ‑ A and Lightning connectors <br>EarPods Headphones (3.5mm JACK) <br>Documentation")

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

    <title><?php echo $compare_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>

    <!-- POROVNANIE -->
    <div class="bg" id="porovnanie_bg"></div>
    <div class="container-fluid">
        <section class="porovnanie">
            <div class="iph12">
                <p class="nadpis">iPhone 12</p>
                <div class="obrazky">
                    <img src="../images/porovnanie/1_IPH12_FRONT.png" alt="iPhone 12 spredu">
                    <img src="../images/porovnanie/2_IPH12_BACK.png" alt="iPhone 12 zozadu">
                </div>
                <div class="table_div">

                    <table>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][0];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][0];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][1];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][1];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][2];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][2];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][3];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][3];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][4];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][4];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][5];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][5];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][6];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][6];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][7];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][7];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][8];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][8];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][9];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][9];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][10];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][10];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][11];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][11];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][12];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][12];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][13];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][13];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][14];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][14];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_12"][15];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_12"][15];?></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="iph6S">
                <p class="nadpis">iPhone 6S</p>
                <div class="obrazky">
                    <img src="../images/porovnanie/3_6S_FRONTB.png" alt="iPhone 6S spredu">
                    <img src="../images/porovnanie/4_6S_BACKB.png" alt="iPhone 6S zozadu">
                </div>
                <div class="table_div">

                    <table>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][0];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][0];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][1];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][1];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][2];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][2];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][3];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][3];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][4];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][4];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][5];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][5];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][6];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][6];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][7];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][7];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][8];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][8];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][9];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][9];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][10];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][10];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][11];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][11];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][12];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][12];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][13];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][13];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][14];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][14];?></td>
                        </tr>
                        <tr>
                            <td class="head"><?php echo $compare_page[$jazyk]["table_head_6S"][15];?></td>
                            <td><?php echo $compare_page[$jazyk]["table_text_6S"][15];?></td>
                        </tr>
                    </table>
                </div>
            </div>

        </section>
    </div>
    

    <?php $color = 'w';?>
    <?php include 'footer.php';?>


</body>
</html>