pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'maven'
    }
    environment {
//        MAVEN_HOME = tool 'Maven'
//        DB_HOST = 'host.docker.internal'
        REPO_URL = 'https://github.com/Soukaina235/gestion_bibliotheque_ci_cd.git'
//        SONARQUBE_SERVER = 'SonarServer'
//        SONARQUBE_TOKEN = 'sqp_21469daaff7f8e46af04d585d6c16262d6232622'
        SONARQUBE_SERVER = 'SonarServer'
        // Define the credentials ID for the SonarQube token
        SONARQUBE_CREDENTIALS_ID = 'sonar-token'
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
//        stage('Quality Analysis') {
//            steps {
//                withSonarQubeEnv(installationName: 'sonar' , credentialsId: sonar) {
//                    sh 'mvn sonar:sonar'
//                }
//            }
//        }


//        stage('Quality Analysis') {
//            steps {
//                withSonarQubeEnv(SONARQUBE_SERVER) {
//                    sh 'mvn sonar:sonar -Dsonar.login=$SONARQUBE_CREDENTIALS_ID'
//                }
//            }
//        }


        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    withCredentials([string(credentialsId: SONARQUBE_CREDENTIALS_ID, variable: 'SONAR_TOKEN')]) {
                        sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                    }
                }
            }
        }

//        stage('Quality Analysis') {
//            script {
//                def scannerHome = tool 'sonar'
//                withSonarQubeEnv(SONARQUBE_SERVER) {
//                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=bibliotheque -Dsonar.sources=src/ -Dsonar.host.url=https://8292-197-147-182-20.ngrok-free.app -Dsonar.login=${SONARQUBE_TOKEN}"
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
