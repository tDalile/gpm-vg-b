package de.thkoeln.inf.gpm.vgb.model.external;


import model.InsurantDao;
import util.DbUtil;

public class Insurant {
    private Long id = null;
    private String name;
    private String firstName;
    private String birthdate;
    private String sex;
    private Double size;
    private Double weight;
    private Address address;
    private Customer customer;

    public static Insurant createOrUpdate(Insurant insurant) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurantDao.Companion.save(insurant));
    }

    public static Insurant findById(Long insurantId) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurantDao.Companion.get(insurantId).toInsurant());
    }

    public static void delete(Long insurantId) {
        DbUtil.INSTANCE.runInTransaction(() -> InsurantDao.Companion.delete(insurantId));
    }

    public Insurant(Long id,
                    String name,
                    String firstName,
                    String birthdate,
                    String sex,
                    Double size,
                    Double weight,
                    Address address,
                    Customer customer) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.size = size;
        this.weight = weight;
        this.address = address;
        this.customer = customer;
    }

    public Insurant(String name,
                    String firstName,
                    String birthdate,
                    String sex,
                    Double size,
                    Double weight,
                    Address address,
                    Customer customer) {
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.size = size;
        this.weight = weight;
        this.address = address;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
