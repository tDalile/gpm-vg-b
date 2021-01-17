package de.thkoeln.inf.gpm.vgb.model;

import model.DiseaseDao;
import model.LocationDao;
import util.DbUtil;

import java.util.List;

public class Location {
    private Integer id = null;
    private String zipCode;
    private String name;

    public static Location createOrUpdate(Location location) {
        return DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.save(location));
    }

    public static List<Location> findAll() {
        return DbUtil.INSTANCE.runInTransaction(LocationDao.Companion::findAll);
    }

    public static Location findById(Integer locationId) {
        return DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.get(locationId)).toLocation();
    }

    public static void delete(Integer locationId) {
        DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.delete(locationId));
    }

    public Location(Integer id, String zipCode, String name) {
        this.id = id;
        this.zipCode = zipCode;
        this.name = name;
    }

    public Location(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
