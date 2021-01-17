package de.thkoeln.inf.gpm.vgb.model;


import model.PreconditionDao;
import util.DbUtil;

import java.util.List;

public class Precondition {
    private Long id;
    private MedicalHistory medicalHistory;
    private Disease disease;

    public static Precondition createOrUpdate(Precondition precondition) {
        return DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.save(precondition));
    }

    public static List<Precondition> findAll() {
        return DbUtil.INSTANCE.runInTransaction(PreconditionDao.Companion::findAll);
    }

    public static Precondition findById(Long preconditionId) {
        return DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.get(preconditionId).toPrecondition());
    }

    public static void delete(Long preconditionId) {
        DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.delete(preconditionId));
    }

    public Precondition(Long id, MedicalHistory medicalHistory, Disease disease) {
        this.id = id;
        this.medicalHistory = medicalHistory;
        this.disease = disease;
    }

    public Precondition(MedicalHistory medicalHistory, Disease disease) {
        this.medicalHistory = medicalHistory;
        this.disease = disease;
    }

    public Long getId() {
        return id;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
