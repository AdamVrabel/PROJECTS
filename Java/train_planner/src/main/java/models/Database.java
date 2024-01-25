package models;

import models.items.Ticket;
import models.persons.Passenger;
import models.train.EconomyClassWagon;
import models.train.FirstClassWagon;
import models.train.TrainRoute;
import models.train.Wagon;

import java.sql.*;
import java.util.ArrayList;


/**
 * <b>CLASS FOR WORK WITH DATABASE</b>
 * <p>USING:</p>
 * <li>Postgres.app (PostgreSQL 15)</li>
 * <li>pgAdmin 4</li>
 * <li>PostgreSQL JDBC Driver</li>
 *
 * <p><br>All changes in the application are always saved/loaded from the database.</p>
 * <p>DB consists of 3 tables: user_acounts, train_routes and tickets.</p>
 * <p>I use a lot of SQL queries to select the manage information in my application</p>
 */

public class Database {

    public static String defaultDate = "2023-04-08";
    public static String defaultTime = "10:00";

    // STATICKE POLE SEARCH REQUEST, PRI PRECHODIE NA AfterSearch screen načítavam z databazy podla tohto array
    //   STRINGY - pre vypisy na GUI
    public static String[] searchRequest = new String[4];
    // 0 : DATUM
    // 1 : ČAS
    // 2 : FROM
    // 3 : TO

    public static ArrayList<TrainRoute> searchTrainRoutes = null; // po vyhľadaní naplní toto pole

    ///// DATABASE INFO /////
    String dbname = "OOP_PROJECT";
    String user = "postgres";
    String pass = "postgres";

    private Connection connectDB(){
        //System.out.println("_____________________________________");
        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");             // LOAD CONTROLLER

            String url  = "jdbc:postgresql://localhost:5432/"+dbname;
            connection = DriverManager.getConnection(url, user, pass);    // CREATE CONNECTION

            //if(connection != null){
            //    System.out.println("Connected to database !");
            //}
            //else{
            //    System.out.println("Database connection FAILED !");
            //}

        } catch (ClassNotFoundException e) {
            System.out.println("The driver for the database could not be loaded.");
            //e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database connection FAILED !");
            //e.printStackTrace();
        }

        return connection;
    }

    private void disconectDB(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //if (connection.isClosed() && statement.isClosed() && resultSet.isClosed()) {
        //    System.out.println("The connection to the database was successfully closed.");
        //}
        //System.out.println("_____________________________________");
    }
    private void disconectDB(Statement statement, Connection connection) throws SQLException {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if (connection.isClosed() && statement.isClosed()) {
//            System.out.println("The connection to the database was successfully closed.");
//        }
//        System.out.println("_____________________________________");
    }

    // VYPÍŠE VŠETKÝCH UŽÍVATEĽOV Z DATABÁZY
    // PRINT ALL USERS FROM DATABASE
    public void printAllUsers(){
        String tableName = "user_account";

        // PRIPOJI SA NA DATABAZU
        Connection connection = connectDB();
        if(connection == null){
            return;
        }

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + tableName;
            //String query = "SELECT * FROM " + tableName + " WHERE gender = 'Male'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int money = resultSet.getInt("money");

                // tu môžete spracovať hodnoty získané z ResultSet
                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
                System.out.println("Money: " + money);
                System.out.println();
            }

            // ODPOJENIE Z DATABAZY
            disconectDB(resultSet, statement, connection);



        } catch (Exception e){
            System.out.println(e);
        }
    }

    // SKONTROLUJE ČI SA V DATABÁZE NACHÁDZA ZADANÝ POUŽÍVATEL (ak nachádza tak TRUE)
    // CONTROL LOGIN, SEARCH FOR user in database (if is in database, return TRUE)
    public boolean validateLogin(String usernameFromInput, String passwordFromInput){
        // PRIPOJI SA NA DATABAZU
        Connection connection = connectDB();
        if(connection == null){
            return false;
        }

        Statement statement;
        ResultSet resultSet = null;
        try {
            // SPOČÍTA KOĽKO ZÁZNAMOV VYHOVUJE (má username a password ako zadaný)
            //  SELECT COUNT(*) FROM user_account WHERE username = 'meno_pouzivatela' AND password = 'heslo'
            String query = "SELECT COUNT(*) FROM user_account WHERE username = '" + usernameFromInput + "' AND password = '"+ passwordFromInput + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                int number = resultSet.getInt(1);
                System.out.println("Najdenych: " + number + " riadkov v tabulke. #USERNAME: " + usernameFromInput + " #PASSWORD: " + passwordFromInput);
                //System.out.println("Pocet riadkov v tabulke " + number);

                if(resultSet.getInt(1) > 0){
                    disconectDB(resultSet, statement, connection);// ODPOJENIE Z DATABAZY
                    return true;    // našlo daného usera, môže sa prihlásiť
                }
            }



            // ODPOJENIE Z DATABAZY
            disconectDB(resultSet, statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    // PRIDÁ NOVÉHO UŽÍVATEĽA
    // ADD NEW USER TO DATABASE
    public void addNewUser(String usernameFromInput, String passwordFromInput){
        // PRIPOJI SA NA DATABAZU
        Connection connection = connectDB();
        if(connection == null){
            return;
        }

        Statement statement;
        try {
            // UZ SOM SKONTROLOVAL CI NEPRIDAVAM ROVNAKEHO v Registration.java

            String query = "INSERT INTO user_account (username, password) VALUES('" + usernameFromInput + "','" + passwordFromInput + "')";

            statement = connection.createStatement();
            int pocetRiadkov = statement.executeUpdate(query);

            // ODPOJENIE Z DATABAZY
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    // vrati objekt typu Passenger s aktualizovanými údajmi z databázy (načíta dáta z databázy)
    // load info from database to class Passenger
    public Passenger getUser(String usernameFromInput, String passwordFromInput){
        Passenger newUser = null;

        // DATABASE CONECTION
        Connection connection = connectDB();
        if(connection == null){
            return null;
        }
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM user_account WHERE username = '" + usernameFromInput + "' AND password = '"+ passwordFromInput + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                int id = resultSet.getInt("account_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int money = resultSet.getInt("money");

                newUser = new Passenger(id, username, password, firstName, lastName, money);
            }
            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);
        } catch (Exception e){
            System.out.println(e);
        }

        return newUser;
    }

    /////////////////////////////////////////////////
    // AKTUALIZUJE USERA V DATABAZE
    public void updateUserTICKET(int userId, String ticketID){

        // QUERY
            // UPDATE public.user_account
            // SET tickets = array_append(tickets, 'novy_ticket2')
            // WHERE "account_id"=19;


        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "UPDATE public.user_account SET tickets = array_append(tickets, '"+ticketID+"') WHERE \"account_id\"="+userId;


            statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(query);
            System.out.println("Počet riadkov updatnutých v databáze: " + rowsUpdated);

            // DISCONECT DATABASE
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

    }
    /////////////////////////////////////////////////

    public ResultSet search(String usernameFromInput, String passwordFromInput){
        // PRIPOJI SA NA DATABAZU
        Connection connection = connectDB();
        if(connection == null){
            return null;
        }

        Statement statement;
        ResultSet resultSet = null;
        try {
            //  SELECT * FROM user_account WHERE username = 'meno_pouzivatela' AND password = 'heslo'
            String query = "SELECT * FROM user_account WHERE username = '" + usernameFromInput + "' AND password = '"+ passwordFromInput + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(!resultSet.isBeforeFirst()){
                System.out.println("NENASIEL SA ZAZNAM V DATABAZE");
            }
            else{
                // NARABANIE S NACITANYMI UDAJMI
                while (resultSet.next()) {
                    int id = resultSet.getInt("account_id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    int money = resultSet.getInt("money");

                    // tu môžete spracovať hodnoty získané z ResultSet
                    System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
                    System.out.println("Money: " + money);
                    System.out.println();
                }
            }



            // ODPOJENIE Z DATABAZY
            //disconectDB(resultSet, statement, connection);
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }
        return resultSet;

    }


    // FOR AFTER LOGIN - NEPOUZIVAM
    // LOAD trainRoutes from database and stores to ArrayList
    public ArrayList<TrainRoute> loadTrainRoutesFromDB(String date){
        ArrayList<TrainRoute> routes = new ArrayList<TrainRoute>();

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            //String query = "SELECT * FROM train_routes";
            String query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                Time departureTime = resultSet.getTime("departureTime");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Date routeDate = resultSet.getDate("routeDate");

                //System.out.println(id + from + to + departureTime + arrivalTime);
                routes.add(new TrainRoute(id, from, to, departureTime, arrivalTime, routeDate));
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);



        } catch (Exception e){
            System.out.println(e);
        }

        return routes;
    }

    // FOR AFTER LOGIN - NEPOUZIVAM
    public ArrayList<TrainRoute> loadTrainRoutesFromDB(String fromINPUT, String toINPUT, String date){
        ArrayList<TrainRoute> routes = new ArrayList<TrainRoute>();

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            //String query = "SELECT * FROM train_routes";
            // IF fromINPUT je prazny tak take query, aj je toINPUT prazdny ine query AK SU OBA VYPLNENE TAK INE QUERY
            String query = null;
            if(fromINPUT.isEmpty() && toINPUT.isEmpty()){
                query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";
            }
            else if((fromINPUT.isEmpty() == true) && (toINPUT.isEmpty() == false)){
                // TO INPUT NIEJE PRAZDNE
                query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "' AND \"to\"='" + toINPUT + "'";
            }
            else if((fromINPUT.isEmpty() == false) && (toINPUT.isEmpty() == true)){
                // FROM INPUT NIEJE PRAZDNE
                query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "' AND \"from\"='" + fromINPUT + "'";
            }
            else{
                // AJ FROM AJ TO SU VYPLNENE
                query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "' AND \"from\"='" + fromINPUT + "' AND \"to\"='" + toINPUT +"'";
            }
            //String query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "' AND \"from\"='" + fromINPUT + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                Time departureTime = resultSet.getTime("departureTime");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Date routeDate = resultSet.getDate("routeDate");

                //System.out.println(id + from + to + departureTime + arrivalTime);
                routes.add(new TrainRoute(id, from, to, departureTime, arrivalTime, routeDate));
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);



        } catch (Exception e){
            System.out.println(e);
        }

        return routes;
    }




    // NACITA TainRoutes Z DATABAZY ZO SEARCH (from, to, date, time)    POMOCOU searchRequest ARRAYu
    // SEARCH REQUEST FROM SEARCH.FXML
    public ArrayList<TrainRoute> loadTrainRoutesBySearchRequest(){
        ArrayList<TrainRoute> routes = new ArrayList<TrainRoute>();

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            //String query = "SELECT * FROM train_routes";
            // IF fromINPUT je prazny tak take query, aj je toINPUT prazdny ine query AK SU OBA VYPLNENE TAK INE QUERY
            String query = null;
            query = "SELECT * FROM train_routes";
            // SELECT * FROM train_routes WHERE "routeDate"='2023-04-08' AND "departureTime">='12:00' AND "from"='Bratislava' AND "to"='Košice'
            query = "SELECT * FROM train_routes WHERE \"routeDate\"='"+ searchRequest[0] +"' AND \"departureTime\">='"+ searchRequest[1] +"' AND \"from\"='"+ searchRequest[2] +"' AND \"to\"='"+ searchRequest[3] +"' ";

            //    query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                Time departureTime = resultSet.getTime("departureTime");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Date routeDate = resultSet.getDate("routeDate");
                //System.out.println(id + from + to + departureTime + arrivalTime);

                TrainRoute tmpRoute = new TrainRoute(id, from, to, departureTime, arrivalTime, routeDate);
                // SEM SPOČÍTA KOĽKO VAGÓNOV AKÝCH MÁ PRIDAŤ (podľa databázy)
                int numberOfFirstClassWagons = resultSet.getInt("economyWagons");
                int numberOfEconomyWagons = resultSet.getInt("fclassWagons");
                // VYTVORENIE DANÉHO POČTU VOZŇOV PODĽA TYPU
                for(int i = 0; i < numberOfEconomyWagons; i++){
                    Wagon tmpWagon = new EconomyClassWagon();
                    tmpRoute.addWagon(tmpWagon);
                }
                for(int i = 0; i < numberOfFirstClassWagons; i++){
                    Wagon tmpWagon = new FirstClassWagon();
                    tmpRoute.addWagon(tmpWagon);
                }
                tmpRoute.setUpWagons(); // NASTAVÍ MIESTA A CENY VAGÓNOV (polymorfiou)


                routes.add(tmpRoute);
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

        return routes;
    }

    // VRÁTI JEDNU TRAIN ROUTE PODĽA TRAIN ROUTE ID V DATABAZE
    public TrainRoute getTrainRoute(String id){

        TrainRoute tmp = null;

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = null;
            query = "SELECT * FROM train_routes WHERE \"id\"='"+id+"'"; // VYBERIE CELY RIADOK, KDE JE DANE ID

            //    query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                Time departureTime = resultSet.getTime("departureTime");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Date routeDate = resultSet.getDate("routeDate");

                //System.out.println(id + from + to + departureTime + arrivalTime);
                tmp = new TrainRoute(Integer.parseInt(id), from, to, departureTime, arrivalTime, routeDate);
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

        return tmp;
    }



    // CREATE NEW TRAIN ROUTE - ADD TO DATABASE
    public void addRouteToDB(String from, String to, String departureTime, String arrivalTime, String date){

        // QUERY
            // INSERT INTO public.train_routes(
            //	"from", "to", "departureTime", "arrivalTime", "routeDate")
            //	VALUES ('Mičigen', 'Konec Sveta', '12:00', '15:01', '3033-01-01');


        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "INSERT INTO public.train_routes(" +
                    "\"from\", \"to\", \"departureTime\", \"arrivalTime\", \"routeDate\")" +
                    "VALUES ('"+from+"', '"+to+"', '"+departureTime+"', '"+arrivalTime+"', '"+date+"');";


            statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(query);
            System.out.println("Počet riadkov vložených do databázy: " + rowsInserted);

            // DISCONECT DATABASE
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

    }
    ////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WORK WITH ticket TABLE

    // PRIDÁ KÚPENÝ TICKED DO TABUĽKY ticket (id cesty, id usera)
    public void addBoughtTicketToDB(int routeID, int userID, int ticketPrice){

        // QUERY
        //  INSERT INTO public.tickets(
        //        "routeId", "userId")
        //  VALUES (?, ?);

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "INSERT INTO public.tickets(\"routeId\", \"userId\", \"ticketPrice\") VALUES ("+routeID+","+userID+","+ticketPrice+")";


            statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(query);
            System.out.println("Počet riadkov vložených do databázy: " + rowsInserted);

            // DISCONECT DATABASE
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    // VRATIM SI ARRAYLIST integerov(cisiel ciest ktore má dany user kupene)
    // PODLA TYCH CISIEL CIEST VYTVORIM ARRAYLIST TICKETOV DO Passenger.acutalLoggedUser inventara
    public ArrayList<Integer> getTicketIDs(int userID){

        ArrayList<Integer> ticketIDs = new ArrayList<Integer>();
        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = null;
            query = "SELECT * FROM public.tickets WHERE \"userId\"="+userID;

            //    query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                Integer routeId = resultSet.getInt("routeId");
                ticketIDs.add(routeId);
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

        return ticketIDs;
    }

    // VRÁTI MI ARRAYLIST TICKETOV KTORÉ MÁ USER S userID KÚPENÉ
    public ArrayList<Ticket> getTickets(int userID){

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = null;
            query = "SELECT * FROM public.tickets WHERE \"userId\"="+userID;

            //    query = "SELECT * FROM train_routes WHERE \"routeDate\"='" + date + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // NARABANIE S NACITANYMI UDAJMI
            while (resultSet.next()) {
                int routeId = resultSet.getInt("routeId");
                int ticketPrice = resultSet.getInt("ticketPrice");
                TrainRoute tmpRoute = getTrainRoute(String.valueOf(routeId));   // VYTVORI TRAINROUTE PODLA ROUTEID
                Ticket newTicket = new Ticket(tmpRoute, userID, ticketPrice);
                tickets.add(newTicket);
            }

            // DISCONECT DATABASE
            disconectDB(resultSet, statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

        return tickets;
    }

    // AKTUALIZUJE USEROV KREDIT
    public void updateUserMoney(int userId, int money){

        // QUERY
        // UPDATE public.user_account
        // SET tickets = array_append(tickets, 'novy_ticket2')
        // WHERE "account_id"=19;


        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "UPDATE public.user_account SET \"money\" = '"+money+"' WHERE \"account_id\"="+userId;


            statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(query);
            System.out.println("Počet riadkov updatnutých v databáze: " + rowsUpdated);

            // DISCONECT DATABASE
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }

    }
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // VYMAZE ROUTE PODLA ID Z DATABAZY
    public void deleteRoute(String routeIDtoDelete){

        // DATABASE CONNECTION
        Connection connection = connectDB();

        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "DELETE FROM public.train_routes WHERE \"id\"='"+routeIDtoDelete+"'";


            statement = connection.createStatement();
            int rowsDeleted = statement.executeUpdate(query);
            System.out.println("Vymazaných riadkov: " + rowsDeleted);

            // DISCONECT DATABASE
            disconectDB(statement, connection);

        } catch (Exception e){
            System.out.println(e);
        }


    }


}
