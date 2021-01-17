package de.thkoeln.inf.gpm.vgb.model;

import model.ClaimDao;
import util.DbUtil;

import java.util.List;

public class Claim {
    private Integer id;
    private String claimDate;
    private Double bmi;
    private Long riskFactorAge;
    private Long riskFactorBmi;
    private Long riskFactorMedicalHistory;
    private Boolean isInsurable;
    private Insurant insurant;
    private InsurancePolicy insurancePolicy;
    private MedicalHistory medicalHistory;

    public static Claim createOrUpdate(Claim claim) {
        return DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.save(claim));
    }

    public static List<Claim> findAll() {
        return DbUtil.INSTANCE.runInTransaction(ClaimDao.Companion::findAll);
    }

    public static Claim findById(Integer claimId) {
        return DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.get(claimId)).toClaim();
    }

    public static void delete(Integer claimId) {
        DbUtil.INSTANCE.runInTransaction(() -> ClaimDao.Companion.delete(claimId));
    }

    public Claim(Integer id, String claimDate, Double bmi, Long riskFactorAge, Long riskFactorBmi, Long riskFactorMedicalHistory, Boolean isInsurable, Insurant insurant, InsurancePolicy insurancePolicy, MedicalHistory medicalHistory) {
        this.id = id;
        this.claimDate = claimDate;
        this.bmi = bmi;
        this.riskFactorAge = riskFactorAge;
        this.riskFactorBmi = riskFactorBmi;
        this.riskFactorMedicalHistory = riskFactorMedicalHistory;
        this.isInsurable = isInsurable;
        this.insurant = insurant;
        this.insurancePolicy = insurancePolicy;
        this.medicalHistory = medicalHistory;
    }

    public Claim(String claimDate, Double bmi, Long riskFactorAge, Long riskFactorBmi, Long riskFactorMedicalHistory, Boolean isInsurable, Insurant insurant, InsurancePolicy insurancePolicy, MedicalHistory medicalHistory) {
        this.claimDate = claimDate;
        this.bmi = bmi;
        this.riskFactorAge = riskFactorAge;
        this.riskFactorBmi = riskFactorBmi;
        this.riskFactorMedicalHistory = riskFactorMedicalHistory;
        this.isInsurable = isInsurable;
        this.insurant = insurant;
        this.insurancePolicy = insurancePolicy;
        this.medicalHistory = medicalHistory;
    }

    public Integer getId() {
        return id;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
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
