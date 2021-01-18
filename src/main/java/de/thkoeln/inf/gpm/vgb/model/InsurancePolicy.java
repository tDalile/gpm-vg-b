package de.thkoeln.inf.gpm.vgb.model;

import model.InsurancePolicyDao;
import util.DbUtil;

import java.util.List;

public class InsurancePolicy {
    private Long id;
    private Boolean isNewCustomer;
    private Double riskSurcharge;
    private String riskSurchargeReason;
    private Double monthlyContribution;
    private Double initialContributionAmount;
    private String startOfContract;
    private Boolean isPremiumTariff;
    private String signDate;
    private Boolean isActive;
    private Insurant insurant;
    private MedicalHistory medicalHistory;

    public static InsurancePolicy createOrUpdate(InsurancePolicy insurancePolicy) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.save(insurancePolicy));
    }

    public static List<InsurancePolicy> findAll() {
        return DbUtil.INSTANCE.runInTransaction(InsurancePolicyDao.Companion::findAll);
    }

    public static InsurancePolicy findById(Long claimId) {
        return DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.get(claimId).toInsurancePolicy());
    }

    public static void delete(Long claimId) {
        DbUtil.INSTANCE.runInTransaction(() -> InsurancePolicyDao.Companion.delete(claimId));
    }

    public InsurancePolicy(Long id, Boolean isNewCustomer, Double riskSurcharge, String riskSurchargeReason, Double monthlyContribution, Double initialContributionAmount, String startOfContract, Boolean isPremiumTariff, String signDate, Boolean isActive, Insurant insurant, MedicalHistory medicalHistory) {
        this.id = id;
        this.isNewCustomer = isNewCustomer;
        this.riskSurcharge = riskSurcharge;
        this.riskSurchargeReason = riskSurchargeReason;
        this.monthlyContribution = monthlyContribution;
        this.initialContributionAmount = initialContributionAmount;
        this.startOfContract = startOfContract;
        this.isPremiumTariff = isPremiumTariff;
        this.signDate = signDate;
        this.isActive = isActive;
        this.insurant = insurant;
        this.medicalHistory = medicalHistory;
    }

    public InsurancePolicy(Boolean isNewCustomer, Double riskSurcharge, String riskSurchargeReason, Double monthlyContribution, Double initialContributionAmount, String startOfContract, Boolean isPremiumTariff, String signDate, Boolean isActive, Insurant insurant, MedicalHistory medicalHistory) {
        this.isNewCustomer = isNewCustomer;
        this.riskSurcharge = riskSurcharge;
        this.riskSurchargeReason = riskSurchargeReason;
        this.monthlyContribution = monthlyContribution;
        this.initialContributionAmount = initialContributionAmount;
        this.startOfContract = startOfContract;
        this.isPremiumTariff = isPremiumTariff;
        this.signDate = signDate;
        this.isActive = isActive;
        this.insurant = insurant;
        this.medicalHistory = medicalHistory;
    }

    public Long getId() {
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

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Insurant getInsurant() {
        return insurant;
    }

    public void setInsurant(Insurant insurant) {
        this.insurant = insurant;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
