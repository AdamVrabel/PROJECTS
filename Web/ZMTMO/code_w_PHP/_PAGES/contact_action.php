<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}


$thanks_contact = array(
    "SK" => array(
        "title" => "ĎAKUJEM !",
        "text" => "ĎAKUJEM ZA VAŠU SPÄTNÚ VÄZBU !",

        "text_2" => "Toto je Vaša správa:",

        "vypis_m" => "Vaše meno:",
        "vypis_m_ERR" => "Vaše meno nebolo zaznamenané.",

        "vypis_email" => "Váš email:",
        "vypis_email_ERR" => "Váš email nebol zaznamenaný.",
        
        "vypis_msg" => "Text správy:",
        "vypis_msg_ERR" => "Vaša správa nebola zaznamenaná."
    ),
    "EN" => array(
        "title" => "THANKS !",
        "text" => "THANK YOU FOR YOUR FEEDBACK!",

        "text_2" => "This is your message:",

        "vypis_m" => "Name:",
        "vypis_m_ERR" => "Your name was not recorded.",

        "vypis_email" => "E-mail:",
        "vypis_email_ERR" => "Your email was not recorded.",
        
        "vypis_msg" => "Message:",
        "vypis_msg_ERR" => "Your message was not recorded."
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

    <meta http-equiv="refresh" content="15; URL=../index.php<?php echo ('?lang='.$jazyk);?>" />

    <title><?php echo $thanks_contact[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body class="gray contact_action">

    <?php include 'header.php';?>

    <div class="thx">
        <h2><?php echo $thanks_contact[$jazyk]["text"];?></h2>
        <div class="content">

            <p class="nadpis"><?php echo $thanks_contact[$jazyk]["text_2"];?></p>

            <?php 
                if($_POST['meno']!=''){
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_m"]."<span>".$_POST['meno']."</span></p>"); 
                }
                else{
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_m_ERR"]."</p>");
                }
                if($_POST['email']!=''){
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_email"]."<span>".$_POST['email']."</span></p>"); 
                }
                else{
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_email_ERR"]."</p>");
                }
                if($_POST['message']!=''){
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_msg"]."<span>".$_POST['message']."</span></p>"); 
                }
                else{
                    echo ("<p>".$thanks_contact[$jazyk]["vypis_msg_ERR"]."</p>");
                }
            ?>
        </div>

    </div>
    <?php
     //PRIDANIE KONTAKT INFO DO SÚBORU
     $myfile_a = fopen("../EXPORT/8_kontakt_EXPORT.txt", "a") or die("Nedokážem otvoriť súbor!");

     fwrite($myfile_a, "\n\tMENO: \t\t".$_POST['meno']."\n");
     fwrite($myfile_a, "\tE-MAIL: \t".$_POST['email']."\n");
     fwrite($myfile_a, "\tSPRÁVA: \t".$_POST['message']."\n\n");

     fclose($myfile_a);
    ?>

    <?php include 'footer.php';?>


</body>
</html>