pipeline {
    agent any
    stages{
        stage('Build backend'){
            steps{
               sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('Unit Tests'){
            steps{
               sh 'mvn test'
            }
        }
        stage('Sonar Analisys'){
            environment{
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL'){
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a48386e226386b870334ac1480265962ba9de67c -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage('Quality Gate'){
            steps{
                sleep(5)
                timeout(time:1 , unit: 'MINUTES'){
                waitForQualityGate abortPipeline:true
                }
            }
        }
        stage('Deploy Backend test'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'TOMCAT_LOGIN', path: '', url: 'http://localhost:8888/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage('API Testes'){
            steps{
                dir('api-test'){
                    git branch: 'main', credentialsId: 'GIT_LOGIN', url: 'https://github.com/vinicius-guima/api-test'
                    sh 'mvn test'
                }
            }
        }
         stage('Deploy Frontend'){
            steps{
                dir('Frontend'){
                    git branch: 'master', credentialsId: 'GIT_LOGIN', url: 'https://github.com/vinicius-guima/tasks-frontend'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat9(credentialsId: 'TOMCAT_LOGIN', path: '', url: 'http://localhost:8888/')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }
        }
                stage('Functional Tests'){
            steps{
                dir('functional-test'){
                    git branch: 'main', credentialsId: 'GIT_LOGIN', url: 'https://github.com/vinicius-guima/https://github.com/vinicius-guima/functional-tests'
                    sh 'mvn test'
                }
            }
        }
    }
}

