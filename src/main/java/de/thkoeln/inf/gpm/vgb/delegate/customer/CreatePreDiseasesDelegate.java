package de.thkoeln.inf.gpm.vgb.delegate.customer;

import de.thkoeln.inf.gpm.vgb.model.ProcessContext;
import de.thkoeln.inf.gpm.vgb.model.external.Claim;
import de.thkoeln.inf.gpm.vgb.model.external.Disease;
import de.thkoeln.inf.gpm.vgb.model.external.MedicalHistory;
import de.thkoeln.inf.gpm.vgb.model.external.Precondition;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;

public class CreatePreDiseasesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessContext processContext = new ProcessContext(delegateExecution);

        val claimId = processContext.getInternal().getClaimId();
        val claim = Claim.findById(claimId);
        val medicalHistory = MedicalHistory.createOrUpdate(new MedicalHistory());

        val diseaseList = Disease.findAll();
        val insurantDiseasesAsStrings = processContext
                .getInternal()
                .getPreconditionList()
                .split(";");

        val insurantDiseases = new ArrayList<Disease>();
        for (Disease disease : diseaseList) {
            for (String diseaseAsString : insurantDiseasesAsStrings) {
                if (disease.getDescription().equals(diseaseAsString)) {
                    insurantDiseases.add(disease);
                }
            }
        }

        if (!insurantDiseases.isEmpty()) {
            Disease highestCategoryDisease = insurantDiseases.get(0);
            for (Disease disease : insurantDiseases) {
                // determine highest category
                if (disease.getCategory() > highestCategoryDisease.getCategory())
                    highestCategoryDisease = disease;

                // save preconditions
                Precondition.createOrUpdate(new Precondition(medicalHistory, disease));
            }

            // set highest disease
            processContext.getInternal().setDiseaseCategory(highestCategoryDisease.getCategory());
            processContext.getInternal().setDiseaseId(highestCategoryDisease.getId());
            processContext.getInternal().setDiseaseDescription(highestCategoryDisease.getDescription());
        } else {
            processContext.getInternal().setDiseaseDescription(null);
        }

        // update claim
        claim.setMedicalHistory(medicalHistory);
        Claim.createOrUpdate(claim);

        // set process variables
        processContext.getInternal().setMedicalHistoryId(medicalHistory.getId());
    }
}
