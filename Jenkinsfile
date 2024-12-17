pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven'
    }
    environment {
//        MAVEN_HOME = tool 'Maven'
        DB_HOST = 'host.docker.internal'
        REPO_URL = 'https://github.com/Soukaina235/gestion_bibliotheque_ci_cd.git'
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'github', url: REPO_URL
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
                // ${MAVEN_HOME}/bin/
            }
        }
        stage('Test') {
            steps {
//                sh '${MAVEN_HOME}/bin/mvn test'
                sh 'mvn test'
            }
        }
//        stage('Quality Analysis') {
//            steps {
//                withSonarQubeEnv('SonarQube') {
////                    sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
//                    sh 'mvn sonar:sonar'
//                }
//            }
//        }
//        stage('Deploy') {
//            steps {
//                echo 'Déploiement simulé réussi'
//            }
//        }
    }
//    post {
//        success {
//            emailext to: 'votre-email@example.com',
//                subject: 'Build Success',
//                body: 'Le build a été complété avec succès.'
//        }
//        failure {
//            emailext to: 'votre-email@example.com',
//                subject: 'Build Failed',
//                body: 'Le build a échoué.'
//        }
//    }
}
