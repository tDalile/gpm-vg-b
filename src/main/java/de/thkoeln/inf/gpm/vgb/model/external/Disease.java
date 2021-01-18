package de.thkoeln.inf.gpm.vgb.model.external;

import model.DiseaseDao;
import util.DbUtil;

import java.util.List;

public class Disease {
    private Long id;
    private Long category;
    private String description;

    public static Disease createOrUpdate(Disease disease) {
        return DbUtil.INSTANCE.runInTransaction(() -> DiseaseDao.Companion.save(disease));
    }

    public static List<Disease> findAll() {
        return DbUtil.INSTANCE.runInTransaction(DiseaseDao.Companion::findAll);
    }

    public static Disease findById(Long diseaseId) {
        return DbUtil.INSTANCE.runInTransaction(() -> DiseaseDao.Companion.get(diseaseId).toDisease());
    }

    public static void delete(Long diseaseId) {
        DbUtil.INSTANCE.runInTransaction(() -> DiseaseDao.Companion.delete(diseaseId));
    }

    public Disease(Long id, Long category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
    }

    public Disease(Long category, String description) {
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
