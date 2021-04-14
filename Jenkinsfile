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
            enviroment{
                scannerHome = tool 'SONAR_HOME'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL'){
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a48386e226386b870334ac1480265962ba9de67c -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
    }
}

