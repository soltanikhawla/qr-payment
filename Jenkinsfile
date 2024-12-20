pipeline  { 
        
       agent any 

         environment {

         version = "1.0.${currentBuild.number}"
    }
   
        stages {


       stage('Build') {
            steps {
              withMaven(globalMavenSettingsConfig: '', jdk: 'jdk_17', maven: 'maven', mavenSettingsConfig: '', traceability: false) {
                sh 'mvn clean'
                sh 'mvn install package -Dmaven.test.skip=true'
               

              }    
			 }
			 
			 }

       stage('copy .war to webapps'){
         steps{
           sh "mv target/*.war  /usr/local/tomcat9/webapps/projet_mapping_${env.version}.war"
           echo "La version de la build est ${env.version}"
           
       }

       }

       stage('version build') {
    steps {
        script {
            // Écrire la version dans le fichier version.txt
            writeFile file: 'version.txt', text: "${env.version}"
        }
         
         sh 'cat version.txt '
        // Archiver le fichier version.txt comme artefact
        archiveArtifacts artifacts: 'version.txt', fingerprint: true
        sh 'cp version.txt /usr/local/tomcat9/'
        
    }
}

       stage('Stop Tomcat') {
            steps { 
                  sh '/usr/local/tomcat9/bin/shutdown.sh'
                  sleep time: 120
                  }
            }
         
         stage('Start Tomcat') {
            steps {  
                  keepRunning {
                  sh '/usr/local/tomcat9/bin/startup.sh'
                  sleep time: 120            
                  }                    
        }
         }
            
          
        

  

        }

       
}    
