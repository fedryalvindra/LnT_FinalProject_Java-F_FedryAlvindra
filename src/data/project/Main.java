package data.project;

import java.util.Vector;

import data.config.Connectiondb;
import data.utility.Pudding;

public class Main {

    public Vector<Pudding> puddsData = new Vector<Pudding>();
    private Connectiondb connectionDb;

    public Main() {
        connectionDb = new Connectiondb();
        new Menu(puddsData, connectionDb);
    }

    public static void main(String[] args) {
        new Main();
    }

}
