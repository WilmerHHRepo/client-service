pipeline {
    agent any
    environment {
        DOCKER_CREDS = credentials('docker-credentials')
        }
        stages {
            stage('Build') {
                agent {
                    docker { image 'maven:3.6.3-openjdk-11-slim' }
                }
                    steps {
                        //sh 'gradle build'
                        sh 'mvn -B verify'
                        //archiveArtifacts artifacts: 'build/libs/labgradle-*-SNAPSHOT.jar', fingerprint: true
                        //archiveArtifacts artifacts: 'build/libs/*-SNAPSHOT.jar', fingerprint: true
                        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                        sh 'echo carpetas'
                        sh 'ls -l'
                        sh 'ls -l target/classes/cloud/csonic/labmaven/'
                        sh 'pwd'
                    }
            }

            stage('SonarQube') {
                steps {
                    sh 'pwd'
                    sh 'ls -l /var/jenkins_home/workspace/pipeline-final@2'
                    sh 'ls -l'
                    script{
                        def scannerHome = tool 'scanner-default'
                        withSonarQubeEnv('sonar-server') {
                        sh "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=labmaven \
                            -Dsonar.projectName=labmaven \
                            -Dsonar.sources=src/main \
                            -Dsonar.java.binaries=/var/jenkins_home/workspace/pipeline-final@2/target/classes \
                            -Dsonar.tests=src/test"
                        }
                    }
                }
            }
            stage('Build Image') {
                steps {
                    copyArtifacts filter: 'target/*.jar',
                                    fingerprintArtifacts: true,
                                    projectName: '${JOB_NAME}',
                                    flatten: true,
                                    selector: specific('${BUILD_NUMBER}'),
                                    target: 'target/'
                    sh 'docker --version'
                    sh 'docker-compose --version'
                    sh 'docker-compose build'
                }
            }
        }
}
