package de.thkoeln.inf.gpm.vgb.model;

import model.MedicalHistoryDao;
import util.DbUtil;

import java.util.List;

public class MedicalHistory {
    private Integer id;

    public static MedicalHistory createOrUpdate(MedicalHistory medicalHistory) {
        return DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.save(medicalHistory));
    }

    public static List<MedicalHistory> findAll() {
        return DbUtil.INSTANCE.runInTransaction(MedicalHistoryDao.Companion::findAll);
    }

    public static MedicalHistory findById(Integer medicalHistoryId) {
        return DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.get(medicalHistoryId)).toMedicalHistory();
    }

    public static void delete(Integer medicalHistoryId) {
        DbUtil.INSTANCE.runInTransaction(() -> MedicalHistoryDao.Companion.delete(medicalHistoryId));
    }

    public MedicalHistory(Integer id) {
        this.id = id;
    }

    public MedicalHistory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
