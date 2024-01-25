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
        "title" => "TECHNICKÉ ŠPECIFIKÁCIE",

        "table_head" => array("MATERIÁL", "FARBA", "KAPACITA", "ROZMERY", "HMOTNOSŤ", "DISPLEJ", "ROZLÍŠENIE", "ODOLNOSŤ", "ČIP", "FACE ID", "APPLE PAY", "PRIPOJENIE", "POLOHA", "VIDEOHOVORY", "HLASOVÉ HOVORY", "BATÉRIA", "SENZORY", "KAMERA", "ULTRAŠIROKOUHLÝ OBJEKTÍV", "ŠIROKOUHLÝ OBJEKTÍV", "VIDEO", "PREDNÁ KAMERA (kamera TrueDepth)", "OBSAH BALENIA"),

        "table_text" => array("Predná strana ceramic shield, hliník a sklenená zadná strana", "čierna, biela, zelená, modrá, fialová, (PRODUCT)<sup>RED</sup>", "64 GB, 128 GB, 256 GB", "výška: 146,7mm <br>šírka: 71,5mm <br>hrúbka: 7,4mm", "162 gramov", "6,1\" Super Retina XDR <br> OLED displej <br> True Tone <br> Široké farebné spektrum (P3) <br> Haptic Touch <br> Kontrastný pomer 2 000 000 : 1 <br> Maximálny jas 625 nitov, 1 200 nitov (HDR) <br>", "2 532 x 1 170 pixelov pri 460 pixeloch na palec", "IP68 (maximálna hĺbka 6 metrov do 30 minút) podľa normy IEC 60529", "Čip A14 Bionic - Neural Engine", "Podporované kamerou TrueDepth na rozpoznávanie tvári", "Platby iPhonom pomocou Face ID v obchodoch, v aplikáciách a na webe", "5G <br>Gigabit LTE s technológiou 4x4 MIMO a LAA <br>WiFi 6 s technológiou 2x2 MIMO <br>Bezdrôtová technológia Bluetooth 5.0 <br>Ultraširokopásmová technológia pre vnímanie priestoru <br>Pripojenie NFC s režimom čítania <br>Expresné karty s energetickou rezervou <br>", "Vstavané GPS, GLONASS, Galileo, QZSS a BeiDou <br>Digitálny kompas <br>Wi Fi <br>Mobilná sieť <br>Mikrolokalizácia iBeacon <br>", "Videohovory FaceTime cez mobilné pripojenie alebo pripojenie Wi Fi <br>Videohovory FaceTime HD (1 080p) cez 5G alebo Wi Fi", "FaceTime audio <br>Voice over LTE (VoLTE) <br>Volania cez Wi Fi <br>", "Prehrávanie videa: až 17 hodín <br>Prehrávanie zvuku: až 65 hodín <br>Lítiovo-iónová batéria <br>Bezdrôtové nabíjanie MagSafe do 15 W <br>Bezdrôtové nabíjanie Qi do 7,5 W <br>Rýchle nabíjanie až na 50 % za 30 minút (pomocou adaptéra s výkonom 20 W alebo vyšším, predáva sa samostatne)", "Face ID <br>Barometer <br>Trojosový gyroskop <br>Akcelerometer <br>Senzor blízkosti <br>Senzor okolitého svetla", "ultraširokouhlá a širokouhlá kamera <br>2x optický zoom <br>5x digitálne priblíženie <br>režim portrét (bokeh, ovládanie hĺbky ostrosti) <br>Optická stabilizácia obrazu <br>Automatická stabilizácia obrazu <br>Panoráma (až 63 Mpx) <br>Kryt objektívu zo zafírového kryštálu <br>Nočný režim <br>Pokročilá korekcia červených očí <br>Režim nepretržitého snímania <br>Geotagging fotiek <br>Formáty nasnímaných obrázkov: HEIF a JPEG", "clona ƒ/2,4 a 120° zorné pole", "clona ƒ/1,6", "Nahrávanie HDR v Dolby Vision - 30 FPS <br>Nahrávanie 4K videa pri 24, 25, 30 alebo 60 FPS <br>Nahrávanie 1 080p HD videa pri 25, 30 alebo 60 FPS <br>Nahrávanie 720p HD videa pri 30 FPS <br>Optická stabilizácia obrazu pre video (širokouhlý objektív) <br>Kinematografická stabilizácia videa (4K, 1 080p a 720p) <br>2x optický zoom <br>Až 3x digitálne priblíženie <br>Jasnejší blesk True Tone <br>Spomalené video 1 080p pri 120 FPS alebo 240 FPS <br>Časozberné video so stabilizáciou <br>Nočný režim s časozberným videom <br>Formáty nahrávaného videa: HEVC a H.264 <br>", "12 Mpx kamera <br>Clona ƒ/2,2 <br>Animoji a Memoji <br>Nočný režim <br>Deep Fusion <br>Smart HDR 3 s detekciou scény <br>Nahrávanie HDR v Dolby Vision - 30 FPS <br>Nahrávanie 4K videa pri 24, 25, 30 alebo 60 FPS <br>Nahrávanie 1 080p HD videa pri 25, 30 alebo 60 FPS <br>Spomalené video 1 080p pri 120 FPS alebo 240 FPS <br>Časozberné video so stabilizáciou <br>Nočný režim s časozberným videom <br>Rozšírený dynamický rozsah pre video pri 30 snímkach/s <br>Kinematografická stabilizácia videa (4K, 1 080p a 720p) <br>Fotky a Live Photos so širokým farebným spektrom <br>Korekcia šošoviek <br>Blesk Retina <br>Automatická stabilizácia obrazu <br>Režim nepretržitého snímania", "iPhone s iOS 14 <br>Kábel s konektormi USB‑C a Lightning <br>Dokumentácia")
    ),
    "EN" => array(
        "title" => "TECHNICAL SPECIFICATIONS",

        "table_head" => array("MATERIAL", "COLOUR", "CAPACITY", "DIMENSIONS", "WEIGHT", "DISPLAY", "RESOLUTION", "RESISTANCE", "CHIP", "FACE ID", "APPLE PAY", "CONNECTION", "POSITION", "VIDEO CALLS", "VOICE CALLS", "BATTERY", "SENSORS", "CAMERA", "ULTRAWIDE LENS", "WIDE LENS", "VIDEO", "FRONT CAMERA (TrueDepth camera)", "PACKAGE CONTENTS"),

        "table_text" => array("Front side ceramic shield, aluminum and a glass back", "black, white, green, blue, purple, (PRODUCT)RED", "64 GB, 128 GB, 256 GB", "height: 146,7mm <br>width: 71,5mm <br>thickness: 7,4mm", "162 grams", "6,1\" Super Retina XDR <br>OLED display <br>True Tone <br>Wide color spectrum (P3) <br>Haptic Touch <br>Contrast ratio 2 000 000 : 1 <br>Maximum brightness 625 nits, 1 200 nits (HDR) <br>", "2 532 x 1 170 pixel in 460 pixel on inch", "IP68 (maximum depth 6 meters within 30 minutes) according to IEC 60529", "Chip A14 Bionic - Neural Engine", "Supported by TrueDepth camera for face recognition", "Payments with your iPhone by Apple Pay using Face ID in stores, apps, and the web.", "5G <br>Gigabit LTE with 4x4 MIMO and LAA technology <br>WiFi 6 with 2x2 MIMO technology <br>Bluetooth 5.0 wireless technology <br>Ultra-wideband technology for space perception5 <br>NFC connection with read mode <br>Express cards with energy reserve <br>", "Built-in GPS, GLONASS, Galileo, QZSS and BeiDou <br>Digital compass <br>Wi Fi <br>Mobile network <br>IBeacon microlocalization <br>", "FaceTime video calls over a mobile or Wi Fi connection <br>FaceTime HD (1,080p) video calls over 5G or Wi Fi", "FaceTime audio <br Voice over LTE (VoLTE) <br>Wi Fi calling <br>", "Video playback: up to 17 hours <br>Audio playback: up to 65 hours <br>Lithium-ion battery <br>MagSafe wireless charging up to 15 W <br>Wireless Qi charging up to 7.5 W <br>Fast charging up to 50% in 30 minutes (using an adapter with a power of 20 W or higher, sold separately)", "Face ID <br>Barometer <br>Triaxial gyroscope <br>Accelerometer <br>Proximity sensor <br>Ambient light sensor", "ultra-wide and wide-angle camera <br>2x optical zoom <br>5x digital zoom <br>portrait mode (bokeh, depth of field control) <br>Optical image stabilization <br>Automatic image stabilization <br>Panorama (up to 63 Mpx) <br>Sapphire crystal lens cover <br>Night mode <br>Advanced red-eye correction <br>Continuous shooting mode <br>Geotagging photos <br>Captured image formats: HEIF and JPEG", "aperture ƒ / 2.4 and 120 ° field of view", "aperture ƒ/1,6", "HDR recording in Dolby Vision - 30 FPS <br>4K video recording at 24, 25, 30 or 60 FPS <br>Record 1,080p HD video at 25, 30 or 60 FPS <br>720p HD video recording at 30 FPS <br>Optical image stabilization for video (wide angle lens) <br>Cinematographic video stabilization (4K, 1,080p and 720p) <br>2x optical zoom <br>Up to 3x digital zoom <br>Brighter True Tone flash <br>Slow motion video 1,080p at 120 FPS or 240 FPS <br>Time-lapse video with stabilization <br>Night mode with time-lapse video <br>Recordable video formats: HEVC and H.264 <br>", "12 Mpx kamera <br>Aperture ƒ / 2.2 <br>Animois and Memois <br>Night mode <br>Deep Fusion <br>Smart HDR 3 with scene detection <br>HDR recording in Dolby Vision - 30 FPS <br>4K video recording at 24, 25, 30 or 60 FPS <br>Record 1,080p HD video at 25, 30 or 60 FPS <br>Slow motion video 1,080p at 120 FPS or 240 FPS <br>Time-lapse video with stabilization <br>Night mode with time-lapse video <br>Extended dynamic range for video at 30 fps <br>Cinematographic video stabilization (4K, 1,080p and 720p) <br>Photos and Live Photos with a wide color spectrum <br>Lens correction <br>Lightning Retina <br>Automatic image stabilization <br>Continuous shooting mode <br>", "iPhone with iOS 14 <br>Cable with USB ‑ C and Lightning connectors <br>Documentation")
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

    <title><?php echo $tech_page[$jazyk]["title"]; ?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>
    <!-- TECHNICKÉ ŠPECIFIKÁCIE -->
    <div class="container-fluid">
        <section class="tech">
            <h2><?php echo $tech_page[$jazyk]["title"]; ?></h2>

            <div class="table_div">

                <table>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][0];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][0];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][1];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][1];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][2];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][2];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][3];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][3];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][4];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][4];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][5];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][5];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][6];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][6];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][7];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][7];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][8];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][8];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][9];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][9];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][10];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][10];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][11];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][11];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][12];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][12];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][13];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][13];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][14];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][14];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][15];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][15];?></td>
                    </tr>
                    
                </table>

                <table>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][16];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][16];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][17];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][17];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][18];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][18];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][19];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][19];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][20];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][20];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][21];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][21];?></td>
                    </tr>
                    <tr>
                        <td class="head"><?php echo $tech_page[$jazyk]["table_head"][22];?></td>
                        <td><?php echo $tech_page[$jazyk]["table_text"][22];?></td>
                    </tr>
                </table>
                

            </div>
        </section>
    </div>    
    
    <?php include 'footer.php';?>
    
</body>
</html>