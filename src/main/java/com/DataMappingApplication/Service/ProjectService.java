package com.DataMappingApplication.Service;

import com.DataMappingApplication.Entity.Project;
import com.DataMappingApplication.Repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
        private final ProjectRepository projectRepository ;



    public Project create(Project project) {
            return projectRepository.save(project) ;
        }

        public List<Project> lire() {
            return projectRepository.findAll();
        }


        public Project updatePro(Project project) {
            // TODO Auto-generated method stub
            return projectRepository.save(project);
        }

        public String delete(Long idProject) {
            projectRepository.deleteById(idProject);
            return "l'élément supprimé avec succés " + idProject;
        }

        public List<Project> findByStatus(String status) {
         return projectRepository.findByStatus(status);
        }

        @Transactional
        public Project getProjectById(Long idProject) {
            return projectRepository.findById(idProject).orElse(null);
        }
        public Project updateProject(Project project) {
            // TODO Auto-generated method stub
            return projectRepository.save(project);
        }

        public Project updateProjectByIdProject(Long idProject, Project project) {
            Project existingProject = projectRepository.findById(idProject).orElse(null);
            if (existingProject == null) {
                throw new ResourceNotFoundException("Project not found with id " + idProject);
            }
            existingProject.setProject_Name(project.getProject_Name());
            existingProject.setDate_Creation(project.getDate_Creation());
            existingProject.setStatus(project.getStatus());
            return projectRepository.save(existingProject);
        }
    @Transactional
    public long  nombreProjectDraft() {
        return projectRepository.nombreProjetDraft();
    }

    @Transactional
    public long  nombreProjectInprogress() {
        return projectRepository.nombreProjectInProgress();
    }

    @Transactional
    public long  nombreProjectDone() {
        return projectRepository.nombreProjectDone();
    }

//    public List<ProjectPercentage> calculateProjectPercentage() {
//        List<Project> projects = projectRepository.findAll();
//        List<ProjectPercentage> projectPercentages = new ArrayList<>();
//
//        for (Project project : projects) {
//            int totalFields = 0;
//            int mappedFields = 0;
//
//            List<Mapping> mappings = mappingRepository.findMappingByProjectId(project.getID_PROJECT());
//            List<ItemsTarget> itemsTargets = itemsTargetRepository.findByProjectId(project.getID_PROJECT());
//
//            for (ItemsTarget itemsTarget : itemsTargets) {
//                String tgtColumnNames = itemsTarget.getColumn_Name();
//                if (tgtColumnNames != null && !tgtColumnNames.isEmpty()) {
//                    totalFields++;
//                }
//            }
//
//            for (Mapping mapping : mappings) {
//                Boolean MappedColumn = mapping.getMapped();
//                if (MappedColumn != null && MappedColumn != false) {
//                    mappedFields++;
//                }
//            }
////            System.out.println("total mapped : "+mappedFields);
////            System.out.println("total field : "+totalFields);
//            double percentage;
//            if (totalFields == 0) {
//                percentage = 0.0;
//            } else {
//                percentage = (mappedFields * 100.0) / totalFields;
//            }
//
//            ProjectPercentage projectPercentageDTO = new ProjectPercentage(project.getProject_Name(), percentage);
//            projectPercentages.add(projectPercentageDTO);
//
//        }
//
//        return projectPercentages;
//    }
}
