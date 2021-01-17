package de.thkoeln.inf.gpm.vgb.model;

import model.MedicalHistoryDao;
import util.DbUtil;

import java.util.List;

public class MedicalHistory {
    private Long id;

    public static MedicalHistory createOrUpdate(MedicalHistory medicalHistory) {
        return DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.save(medicalHistory));
    }

    public static List<MedicalHistory> findAll() {
        return DbUtil.INSTANCE.runInTransaction(MedicalHistoryDao.Companion::findAll);
    }

    public static MedicalHistory findById(Long medicalHistoryId) {
        return DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.get(medicalHistoryId).toMedicalHistory());
    }

    public static void delete(Long medicalHistoryId) {
        DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.delete(medicalHistoryId));
    }

    public MedicalHistory(Long id) {
        this.id = id;
    }

    public MedicalHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
