package de.thkoeln.inf.gpm.vgb.util;

import util.DbUtil;

public class DeploySchema {

    public static void main(String[] args) {
        DbUtil.INSTANCE.getDb();
        DbUtil.INSTANCE.recreateSchema();
        DbUtil.INSTANCE.insertDummyData();
    }
}
