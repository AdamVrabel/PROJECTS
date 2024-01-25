<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}

$index_menu = array(
    "SK" => array("Domov", "Technické špecifikácie", "Galéria", "Používateľské rozhranie", "Porovnanie", "Príslušenstvo", "Hodnotenie", "Kontakt"),
    "EN" => array("Home", "Technical specifications", "Gallery", "User interface", "Comparsion", "Accessories", "Rating", "Contact")
);

$index_page = array(
    "SK" => array(
        "title" => "DOMOV",
        "h1" => "iPhone 12",
        "first_text" => "Ak túžiš po kvalite, iPhone 12 je pre teba to pravé. <br> Určite dokáže zaujať kvalitou spracovania a úžasným operačným systémom iOS.",
        "first_tlacidlo1" => "od 799 €",
        "first_tlacidlo2" => "STRÁNKA VÝROBCU",

        "third_1_nadpis" => "Naozaj rýchly",
        "third_1_text" => "Čip A14 Bionic je 16-jadrový Neural Engine ktorý sa ti nepodarí jednoducho zahltiť. Zvládne biliardy operácii každú sekundu. Je vysoko efektívny, takže výdrž batérie to neohrozí.",
        "third_2_nadpis" => "NOČNÝ REŽIM",
        "third_2_text" => "Už žiadne tmavé fotky bez detailov. Ak sa tvoji kamoši vydržia nehýbať aspoň 3 sekundy tak to máš v kapse...",
        "third_3_nadpis" => "INTUITÍVNOSŤ",
        "third_3_text" => "Veľmi jednoducho sa používa, ak si už niekedy mal iPhone tak určite vieš o čom hovorím. Stačí len klepnúť alebo potiahnuť prstom.",
        "third_4_nadpis" => "VÝKONNÝ A SPOĽAHLIVÝ",
        "third_4_text" => "Podarilo sa Vám zaseknúť telefón tak, že mu pomohol iba reštart. Pri tomto telefóne sa to nestane. Úžasný operačný systém iOS sa postará o svižný chod aj zložitejších úkonov.",
        "third_5_nadpis" => "PODPORUJE 5G",
        "third_5_text" => "V prípade LTE pripojenia tento telefón zvládne rýchlosti až do 2Gb/s. Streamovanie videa, sťahovanie a pozeranie filmov vo vysokej kvalite bez problémov.",
        "third_6_nadpis" => "APPLE PAY",
        "third_6_text" => "Platby iPhonom pomocou Face ID v obchodoch, v aplikáciách a na webe.",

        "footer_text" => "Táto stránka opisuje telefón iPhone 12, ktorý vlastním. Týmto projektom chcem ukázať môj telefón aj iným ľudom."
    ),
    "EN" => array(
        "title" => "HOME",
        "h1" => "iPhone 12",
        "first_text" => "If you want quality, the iPhone 12 is for you.<br> Can impress with the quality of processing and the amazing iOS operating system.",
        "first_tlacidlo1" => "from 799 €",
        "first_tlacidlo2" => "APPLE website",

        "third_1_nadpis" => "REALLY FAST",
        "third_1_text" => "The A14 Bionic chip is a 16-core Neural Engine that you can't easily overwhelm. He can handle billiards every second. It is highly efficient, so battery life will not be compromised.",
        "third_2_nadpis" => "NIGHT MODE",
        "third_2_text" => "No more dark photos without details. If your friends can't move for 3 seconds, then you have it in your pocket ...",
        "third_3_nadpis" => "INTUITIVITY",
        "third_3_text" => "It's very easy to use, if you've ever had an iPhone, so you know what I'm talking about. Just tap or swipe.",
        "third_4_nadpis" => "POWERFUL AND RELIABLE",
        "third_4_text" => "You managed to block your phone operating system so that only a restart helped it. This will not happen with this phone. The amazing iOS operating system will take care of the agile operation of even more complex tasks.",
        "third_5_nadpis" => "SUPPORTS 5G",
        "third_5_text" => "On LTE connection, this phone can handle speeds of up to 2Gb /s. Stream videos, download and watch high quality movies without any problems.",
        "third_6_nadpis" => "APPLE PAY",
        "third_6_text" => "Payments with your iPhone by Apple Pay using Face ID in stores, apps, and the web..",

        "footer_text" => "This page describes the iPhone 12 I own. With this project, I want to show my phone to other people."
    )
);
?>


<!DOCTYPE html>
<html lang="<?php echo (strtolower($jazyk));?>">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="icon" href="images/favicon.png" sizes="16x16" type="image/png">

    <!-- GOOGLE FONTS - OPEN SANS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">

    <!-- MOJE CSS -->
    <link rel="stylesheet" href="styles/style.css">

    <title><?php echo $index_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>
    <?php include 'index_header.php';?>

    <!-- PRVA SEKCIA -->
    <div class="container-fluid">
    
        <section class="first">
            <img src="images/home/main/0.png" alt="iPhone 12">
            <div class="content">
                <h1><?php echo $index_page[$jazyk]["h1"]; ?></h1>
                <p class="text"><?php echo $index_page[$jazyk]["first_text"]; ?></p>
                <div class="tlacidlo"><a href="https://www.alza.sk/iphone-12-64gb-biely-d6216435.htm"><?php echo $index_page[$jazyk]["first_tlacidlo1"]; ?></a></div>
                <div class="tlacidlo"><a href="https://www.apple.com/sk/iphone-12/specs/"><?php echo $index_page[$jazyk]["first_tlacidlo2"]; ?></a></div>

            </div>
        </section>

    </div>
    
    <?php
        $dir = 'images/home/';

        $i=0;
        foreach (glob($dir."*.jpg") as $filename) {
            //echo "$filename"."\n";
            
            $file[$i]=(basename($filename));
            $i++;
            //echo $file."\n";
        }
    // echo '<pre>';
    // print_r ($file);
    // echo '</pre>';
    ?>

    <!-- DRUHÁ SEKCIA - MINI GALÉRIA -->
    <div class="container-fluid-color">

        <section class="second">

            <?php 
            echo '<div class="mini-gallery mini-gallery-visible">';
            foreach($file as $fn){
                echo ("\n\t\t\t\t".'<div class="wrapper"><a href="images/home/BIG/'.$fn.'"><img src="images/home/'.$fn.'" alt="iPhone 12"></a></div>');
            }
            echo ("\n\t\t\t".'<div>');
            ?>
            
            </div>
           
            
        </section>
    </div>

    <!-- TRETIA SEKCIA -->
    <div class="container-fluid">

        <section class="third">
            <p class="nadpis">iPhone 12</p>
            <div class="obal">
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_1_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_1_text"];?></p>
                </div>
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_2_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_2_text"];?></p>
                </div>
            </div>
            
            <div class="obal">
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_3_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_3_text"];?></p>
                </div>
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_4_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_4_text"];?></p>
                </div>
            </div>

            <div class="obal">
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_5_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_5_text"];?></p>
                </div>
                <div class="tag">
                    <p class="header"><?php echo $index_page[$jazyk]["third_6_nadpis"];?></p>
                    <p class="text"><?php echo $index_page[$jazyk]["third_6_text"];?></p>
                </div>
            </div>

        </section>
    </div>

    <?php include 'index_footer.php';?>


</body>
</html>