package de.thkoeln.inf.gpm.vgb.model;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NonNull;
import model.CustomerDao;
import util.DbUtil;

@Data
public class Customer {
    @Nullable
    private Integer id = null;

    @NonNull
    private String entry;

    @Nullable
    private Integer insurantId = null;

    public static Customer createOrUpdate(Customer customer) {
        return DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.save(customer));
    }

    public static Customer findById(Integer customerId) {
        return DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.get(customerId)).toCustomer();
    }

    public static void delete(Integer customerId) {
        DbUtil.INSTANCE.runInTransaction(() -> CustomerDao.Companion.delete(customerId));
    }

    public Customer(String entryDate) {
        this.entry = entryDate;
    }

    public Customer(Integer id, String entryDate, Integer insurantId) {
        this.id = id;
        this.entry = entryDate;
        this.insurantId = insurantId;
    }

    public Integer getId() {
        return id;
    }

    public String getEntry() {
        return entry;
    }

    public Integer getInsurantId() {
        return insurantId;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setInsurantId(Integer insurantId) {
        this.insurantId = insurantId;
    }
}
