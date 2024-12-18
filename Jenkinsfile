pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven'
    }
    environment {
        REPO_URL = 'https://github.com/Soukaina235/gestion_bibliotheque_ci_cd.git'
        SONARQUBE_SERVER = 'SonarServer'
        SONARQUBE_CREDENTIALS_ID = 'sonar-token'
    }
    stages {
//        stage('Clean Workspace') {
//            steps {
//                cleanWs()
//            }
//        }
//        stage('Checkout') {
//            steps {
//                git branch: 'main', credentialsId: 'github', url: REPO_URL
//            }
//        }
//        stage('Build') {
//            steps {
//                sh 'mvn clean compile'
//            }
//        }
//        stage('Test') {
//            steps {
//                sh 'mvn test'
//            }
//        }
//        stage('Quality Analysis') {
//            steps {
//                withSonarQubeEnv(SONARQUBE_SERVER) {
//                    withCredentials([string(credentialsId: SONARQUBE_CREDENTIALS_ID, variable: 'SONAR_TOKEN')]) {
//                        sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
//                    }
//                }
//            }
//        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            mail to: 'soukaina.jabri333@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
        }
        failure {
            mail to: 'soukaina.jabri333@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
        }
    }
}
