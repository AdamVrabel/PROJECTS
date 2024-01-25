package models.train;

import models.persons.TrainConductor;

public class Locomotive {
    //int actualSpeet; // bude sa zobrazovat pri simulovaní cesty v APPke
    final String nameOfLocomotive = "Najlepsia najrychlejsia lokomotiva";
    TrainConductor trainDriver;


    public Locomotive(){
        // prirad EMPLOYEE z databázy
    }

    public TrainConductor getTrainDriver() {return trainDriver;}
    public void setTrainDriver(TrainConductor trainDriver) {this.trainDriver = trainDriver;}
}
