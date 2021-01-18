package de.thkoeln.inf.gpm.vgb.model.external;

import model.CustomerDao;
import util.DbUtil;

public class Customer {
    private Long id = null;
    private String entry;
    private String password;
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

    public Customer(String entryDate, String password) {
        this.entry = entryDate;
        this.password = password;
    }

    public Customer(Long id, String entryDate, String password, Long insurantId) {
        this.id = id;
        this.entry = entryDate;
        this.password = password;
        this.insurantId = insurantId;
    }

    public Long getId() {
        return id;
    }

    public String getEntry() {
        return entry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
