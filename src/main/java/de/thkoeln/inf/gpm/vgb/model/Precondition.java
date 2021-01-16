package de.thkoeln.inf.gpm.vgb.model;


import model.PreconditionDao;
import util.DbUtil;

import java.util.List;

public class Precondition {
    private Integer id;
    private MedicalHistory medicalHistory;
    private Disease disease;

    public static Precondition createOrUpdate(Precondition precondition) {
        return DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.save(precondition));
    }

    public static List<Precondition> findAll() {
        return DbUtil.INSTANCE.runInTransaction(PreconditionDao.Companion::findAll);
    }

    public static Precondition findById(Integer preconditionId) {
        return DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.get(preconditionId)).toPrecondition();
    }

    public static void delete(Integer preconditionId) {
        DbUtil.INSTANCE.runInTransaction(() -> PreconditionDao.Companion.delete(preconditionId));
    }

    public Precondition(Integer id, MedicalHistory medicalHistory, Disease disease) {
        this.id = id;
        this.medicalHistory = medicalHistory;
        this.disease = disease;
    }

    public Precondition(MedicalHistory medicalHistory, Disease disease) {
        this.medicalHistory = medicalHistory;
        this.disease = disease;
    }

    public Integer getId() {
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
