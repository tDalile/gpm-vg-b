package de.thkoeln.inf.gpm.vgb.model;

import model.InsurancePolicyDao;
import util.DbUtil;

import java.util.List;

public class InsurancePolicy {
    private Integer id;
    private Boolean isNewCustomer;
    private Double riskSurcharge;
    private String riskSurchargeReason;
    private Double monthlyContribution;
    private Double initialContributionAmount;
    private String startOfContract;
    private Boolean isPremiumTariff;
    private Customer customer;
    private MedicalHistory medicalHistory;

    public static InsurancePolicy createOrUpdate(InsurancePolicy insurancePolicy) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.save(insurancePolicy));
    }

    public static List<InsurancePolicy> findAll() {
        return DbUtil.INSTANCE.runInTransaction(InsurancePolicyDao.Companion::findAll);
    }

    public static InsurancePolicy findById(Integer claimId) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.get(claimId)).toInsurancePolicy();
    }

    public static void delete(Integer claimId) {
        DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.delete(claimId));
    }

    public InsurancePolicy(Integer id, Boolean isNewCustomer, Double riskSurcharge, String riskSurchargeReason, Double monthlyContribution, Double initialContributionAmount, String startOfContract, Boolean isPremiumTariff, Customer customer, MedicalHistory medicalHistory) {
        this.id = id;
        this.isNewCustomer = isNewCustomer;
        this.riskSurcharge = riskSurcharge;
        this.riskSurchargeReason = riskSurchargeReason;
        this.monthlyContribution = monthlyContribution;
        this.initialContributionAmount = initialContributionAmount;
        this.startOfContract = startOfContract;
        this.isPremiumTariff = isPremiumTariff;
        this.customer = customer;
        this.medicalHistory = medicalHistory;
    }

    public InsurancePolicy(Boolean isNewCustomer, Double riskSurcharge, String riskSurchargeReason, Double monthlyContribution, Double initialContributionAmount, String startOfContract, Boolean isPremiumTariff, Customer customer, MedicalHistory medicalHistory) {
        this.isNewCustomer = isNewCustomer;
        this.riskSurcharge = riskSurcharge;
        this.riskSurchargeReason = riskSurchargeReason;
        this.monthlyContribution = monthlyContribution;
        this.initialContributionAmount = initialContributionAmount;
        this.startOfContract = startOfContract;
        this.isPremiumTariff = isPremiumTariff;
        this.customer = customer;
        this.medicalHistory = medicalHistory;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getNewCustomer() {
        return isNewCustomer;
    }

    public void setNewCustomer(Boolean newCustomer) {
        isNewCustomer = newCustomer;
    }

    public Double getRiskSurcharge() {
        return riskSurcharge;
    }

    public void setRiskSurcharge(Double riskSurcharge) {
        this.riskSurcharge = riskSurcharge;
    }

    public String getRiskSurchargeReason() {
        return riskSurchargeReason;
    }

    public void setRiskSurchargeReason(String riskSurchargeReason) {
        this.riskSurchargeReason = riskSurchargeReason;
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public Double getInitialContributionAmount() {
        return initialContributionAmount;
    }

    public void setInitialContributionAmount(Double initialContributionAmount) {
        this.initialContributionAmount = initialContributionAmount;
    }

    public String getStartOfContract() {
        return startOfContract;
    }

    public void setStartOfContract(String startOfContract) {
        this.startOfContract = startOfContract;
    }

    public Boolean getPremiumTariff() {
        return isPremiumTariff;
    }

    public void setPremiumTariff(Boolean premiumTariff) {
        isPremiumTariff = premiumTariff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
