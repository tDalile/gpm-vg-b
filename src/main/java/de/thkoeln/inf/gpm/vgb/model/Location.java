package de.thkoeln.inf.gpm.vgb.model;

import model.LocationDao;
import util.DbUtil;

import java.util.List;

public class Location {
    private Long id = null;
    private String zipCode;
    private String name;

    public static Location createOrUpdate(Location location) {
        return DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.save(location));
    }

    public static List<Location> findAll() {
        return DbUtil.INSTANCE.runInTransaction(LocationDao.Companion::findAll);
    }

    public static Location findById(Long locationId) {
        return DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.get(locationId).toLocation());
    }

    public static void delete(Long locationId) {
        DbUtil.INSTANCE.runInTransaction(() -> LocationDao.Companion.delete(locationId));
    }

    public Location(Long id, String zipCode, String name) {
        this.id = id;
        this.zipCode = zipCode;
        this.name = name;
    }

    public Location(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public Long getId() {
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
