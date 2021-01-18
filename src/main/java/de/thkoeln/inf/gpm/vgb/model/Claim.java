package de.thkoeln.inf.gpm.vgb.model;

import model.ClaimDao;
import util.DbUtil;

import java.util.List;

public class Claim {
    private Long id;
    private String claimDate;
    private String desiredStartDate;
    private Double bmi;
    private Long riskFactorAge;
    private Long riskFactorBmi;
    private Long riskFactorMedicalHistory;
    private Boolean isInsurable;
    private String rejectionReason;
    private Insurant insurant;
    private InsurancePolicy insurancePolicy;
    private MedicalHistory medicalHistory;

    public static Claim createOrUpdate(Claim claim) {
        return DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.save(claim));
    }

    public static List<Claim> findAll() {
        return DbUtil.INSTANCE.runInTransaction(ClaimDao.Companion::findAll);
    }

    public static Claim findById(Long claimId) {
        return DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.get(claimId).toClaim());
    }

    public static void delete(Long claimId) {
        DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.delete(claimId));
    }

    public Claim(Long id, String claimDate, String desiredStartDate, Double bmi, Long riskFactorAge, Long riskFactorBmi, Long riskFactorMedicalHistory, Boolean isInsurable, String rejectionReason, Insurant insurant, InsurancePolicy insurancePolicy, MedicalHistory medicalHistory) {
        this.id = id;
        this.claimDate = claimDate;
        this.desiredStartDate = desiredStartDate;
        this.bmi = bmi;
        this.riskFactorAge = riskFactorAge;
        this.riskFactorBmi = riskFactorBmi;
        this.riskFactorMedicalHistory = riskFactorMedicalHistory;
        this.isInsurable = isInsurable;
        this.rejectionReason = rejectionReason;
        this.insurant = insurant;
        this.insurancePolicy = insurancePolicy;
        this.medicalHistory = medicalHistory;
    }

    public Claim(String claimDate, String desiredStartDate, Double bmi, Long riskFactorAge, Long riskFactorBmi, Long riskFactorMedicalHistory, Boolean isInsurable, String rejectionReason, Insurant insurant, InsurancePolicy insurancePolicy, MedicalHistory medicalHistory) {
        this.claimDate = claimDate;
        this.desiredStartDate = desiredStartDate;
        this.bmi = bmi;
        this.riskFactorAge = riskFactorAge;
        this.riskFactorBmi = riskFactorBmi;
        this.riskFactorMedicalHistory = riskFactorMedicalHistory;
        this.isInsurable = isInsurable;
        this.rejectionReason = rejectionReason;
        this.insurant = insurant;
        this.insurancePolicy = insurancePolicy;
        this.medicalHistory = medicalHistory;
    }

    public Long getId() {
        return id;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getDesiredStartDate() {
        return desiredStartDate;
    }

    public void setDesiredStartDate(String desiredStartDate) {
        this.desiredStartDate = desiredStartDate;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Long getRiskFactorAge() {
        return riskFactorAge;
    }

    public void setRiskFactorAge(Long riskFactorAge) {
        this.riskFactorAge = riskFactorAge;
    }

    public Long getRiskFactorBmi() {
        return riskFactorBmi;
    }

    public void setRiskFactorBmi(Long riskFactorBmi) {
        this.riskFactorBmi = riskFactorBmi;
    }

    public Long getRiskFactorMedicalHistory() {
        return riskFactorMedicalHistory;
    }

    public void setRiskFactorMedicalHistory(Long riskFactorMedicalHistory) {
        this.riskFactorMedicalHistory = riskFactorMedicalHistory;
    }

    public Boolean getInsurable() {
        return isInsurable;
    }

    public void setInsurable(Boolean insurable) {
        isInsurable = insurable;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Insurant getInsurant() {
        return insurant;
    }

    public void setInsurant(Insurant insurant) {
        this.insurant = insurant;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
