package de.thkoeln.inf.gpm.vgb.model;

import model.AddressDao;
import util.DbUtil;

public class Address {
    private Long id;
    private String street;
    private String houseNumber;
    private Location location;

    public static Address createOrUpdate(Address address) {
        return DbUtil.INSTANCE.runInTransaction(() -> AddressDao.Companion.save(address));
    }

    public static Address findById(Long addressId) {
        return DbUtil.INSTANCE.runInTransaction(() -> AddressDao.Companion.get(addressId).toAddress());
    }

    public static void delete(Long addressId) {
        DbUtil.INSTANCE.runInTransaction(() -> AddressDao.Companion.delete(addressId));
    }

    public Address(Long id, String street, String houseNumber, Location location) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.location = location;
    }

    public Address(String street, String houseNumber, Location location) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
