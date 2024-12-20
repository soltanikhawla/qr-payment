package com.DataMappingApplication.Repository;

import com.DataMappingApplication.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

        @Query("SELECT p.Project_Name FROM Project p")
     List<String> findAllProjectNames();
    List<Project> findByStatus(String status);
//        //Project findByID_PROJECT(long ID_PROJECT);
//         List<Project> findByID_PROJECT(long ID_PROJECT);
//Project findByID_PROJECT(long ID_PROJECT);
//@Query("SELECT p FROM Project p WHERE p.project = :project ORDER BY p.version DESC")
//List<Project> findVersionsByProject(@Param("project") Project project);
//@Query("SELECT p FROM Project p WHERE p.Project_Name = :projectName ORDER BY p.version DESC")
//List<Project> findVersionsByProject(@Param("projectName") String projectName);
//@Query("SELECT p FROM Project p WHERE p.ID_PROJECT = :projectId ORDER BY p.version DESC")
//List<Project> findVersionsByProjectId(@Param("projectId") long projectId);

    @Query("SELECT COUNT(*) FROM Project WHERE status =  'Draft'")
    long nombreProjetDraft();

    @Query("SELECT COUNT(*) FROM Project WHERE status = 'In progress'")
    long nombreProjectInProgress();

    @Query("SELECT COUNT(*) FROM Project WHERE status = 'Closed'")
    long nombreProjectDone();
}
