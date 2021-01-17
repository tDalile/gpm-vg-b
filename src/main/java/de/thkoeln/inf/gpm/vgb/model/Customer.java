package de.thkoeln.inf.gpm.vgb.model;

import model.CustomerDao;
import util.DbUtil;

public class Customer {
    private Long id = null;
    private String entry;
    private Long insurantId = null;

    public static Customer createOrUpdate(Customer customer) {
        return DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.save(customer));
    }

    public static Customer findById(Long customerId) {
        return DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.get(customerId).toCustomer());
    }

    public static void delete(Long customerId) {
        DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.delete(customerId));
    }

    public Customer(String entryDate) {
        this.entry = entryDate;
    }

    public Customer(Long id, String entryDate, Long insurantId) {
        this.id = id;
        this.entry = entryDate;
        this.insurantId = insurantId;
    }

    public Long getId() {
        return id;
    }

    public String getEntry() {
        return entry;
    }

    public Long getInsurantId() {
        return insurantId;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setInsurantId(Long insurantId) {
        this.insurantId = insurantId;
    }
}
