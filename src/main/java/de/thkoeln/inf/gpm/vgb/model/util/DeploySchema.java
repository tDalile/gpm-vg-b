package de.thkoeln.inf.gpm.vgb.model.util;

public class DeploySchema {

    public static void main(String[] args) {
        DbSetup.INSTANCE.getDb();
        DbSetup.INSTANCE.recreateSchema();
        DbSetup.INSTANCE.insertDummyData();
    }
}
