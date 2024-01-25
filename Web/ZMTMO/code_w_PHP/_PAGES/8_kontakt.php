<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}

$contact_page = array(
    "SK" => array(
        "title" => "KONTAKT",
        "contact_nadpis" => "kontaktujte ma !",

        "contact_form_meno" => "Vaše meno:",
        "contact_form_email" => "Váš e-mail:",
        "contact_form_message" => "Správa:",
        "contact_form_btn" => "odoslať",

        "whoiam_nadpis" => "kto som ?",
        "whoiam_meno" => "Adam Vrabeľ",
        "whoiam_text" => "som študent FIIT STU",
        "whoiam_email" => "xvrabela1@stuba.sk"
        
    ),
    "EN" => array(
        "title" => "CONTACT",
        "contact_nadpis" => "CONTACT ME !",

        "contact_form_meno" => "Your name:",
        "contact_form_email" => "Your e-mail:",
        "contact_form_message" => "Message:",
        "contact_form_btn" => "submit",

        "whoiam_nadpis" => "WHO AM I ?",
        "whoiam_meno" => "Adam Vrabeľ",
        "whoiam_text" => "i am student of FIIT STU",
        "whoiam_email" => "xvrabela1@stuba.sk"
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

    <title><?php echo $contact_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>

    <!-- KONTAKT -->
    <div class="container-fluid-color">
        <section class="kontakt">
            <div class="formular">
                <h2><?php echo $contact_page[$jazyk]["contact_nadpis"];?></h2>

                <form action="_PAGES/../contact_action.php<?php echo ("?lang=".$jazyk);?>" method="post">

                    <div class="input">
                        <label for="meno" class="input-label"><?php echo $contact_page[$jazyk]["contact_form_meno"];?></label>
                        <input type="text" id="meno" name="meno" class="input-field" value=""  required/>
                    </div>
        
                    <div class="input">
                        <label for="email" class="input-label"><?php echo $contact_page[$jazyk]["contact_form_email"];?></label>
                        <input type="text" id="email" name="email" class="input-field" value="" required/>
                    </div>
        
                    <div class="input">
                        <label for="message" class="input-label"><?php echo $contact_page[$jazyk]["contact_form_message"];?></label>
                        <input type="text" id="message" name="message" class="input-field" value="" />
                    </div>
        
                    <input type="submit" value="<?php echo $contact_page[$jazyk]["contact_form_btn"];?>">
        
                </form> 
            </div>
            <div class="whoiam">
                <h2><?php echo $contact_page[$jazyk]["whoiam_nadpis"];?></h2>
                <div class="iam">
                    <div class="image"><img src="../images/kontakt/kontakt.jpeg" alt="Adam Vrabeľ"></div>
                    <div class="text">
                        <p class="nadpis"><?php echo $contact_page[$jazyk]["whoiam_meno"];?></p>
                        <p class="small"><?php echo $contact_page[$jazyk]["whoiam_text"];?></p>
                        <p class="email"><?php echo $contact_page[$jazyk]["whoiam_email"];?></p>
                    </div>
                </div>
            </div>

        </section>
    </div>

    <?php $color = 'w';?>
    <?php include 'footer.php';?>



</body>
</html>