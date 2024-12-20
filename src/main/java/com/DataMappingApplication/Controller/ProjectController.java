package com.DataMappingApplication.Controller;

import com.DataMappingApplication.Entity.*;
import com.DataMappingApplication.Repository.ProjectRepository;
import com.DataMappingApplication.Service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("project/")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository ;


    @PostMapping("create")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            // Récupérez la liste des noms de projet existants
            List<String> existingProjectNames = projectRepository.findAllProjectNames();

            // Vérifiez si le nom du projet existe déjà dans la liste
            if (existingProjectNames.contains(project.getProject_Name())) {
                return new ResponseEntity<>("Le nom du projet existe déjà.", HttpStatus.CONFLICT);
            }

            Project _project = projectService.create(project);
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur interne du serveur.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("list")
    public List<Project> read() {
        return projectService.lire();
    }

    @DeleteMapping("delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        return projectService.delete(id);
    }

    @GetMapping("{projectId}")
    public Project findProjectById(@PathVariable Long projectId){

        return projectService.getProjectById(projectId);
    }

//    @GetMapping("/project/list/{userName}")
//    public List<Project> findMappingById(@PathVariable String userName){
//        return projectService.findProjectByUserName(userName);
//    }

    // get nombre project Draft
//    @GetMapping("/nombreProjectDraft")
//    public ResponseEntity<?> findnombreProjectDraft(){
//        final long nbr = projectService.nombreProjectDraft();
//        return ResponseEntity.ok(new NombreProjetParStatus(nbr));
//    }
    // get nombre project Done
//    @GetMapping("/nombreProjectInProgress")
//    public ResponseEntity<?> findnombreProjectInProgress(){
//        final long nbr = projectService.nombreProjectInprogress();
//        return ResponseEntity.ok(new NombreProjetParStatus(nbr));
//    }

    // get nombre project Done
//    @GetMapping("/nombreProjectDone")
//    public ResponseEntity<?> findnombreProjectDone(){
//        final long nbr = projectService.nombreProjectDone();
////        return ResponseEntity.ok(new NombreProjetParStatus(nbr));
//    }
    @PutMapping("update")
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    //update by idProject
    @PutMapping("update/{idProject}")
    public Project updateProject(@PathVariable Long idProject, @RequestBody Project project) {
        return projectService.updateProjectByIdProject(idProject, project);
    }

    @GetMapping("/projects/filter/{status}")
    public List<Project> filterProjectsByStatus(@PathVariable String status) {
        return projectService.findByStatus(status);
    }

}