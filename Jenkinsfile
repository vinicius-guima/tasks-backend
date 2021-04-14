pipeline {
    agent any
    stages{
        stage('Build backend'){
            steps{
               sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
    }
}