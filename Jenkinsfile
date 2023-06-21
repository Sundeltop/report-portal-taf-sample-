pipeline {
    agent any

    tools {
        jdk 'Java 17'
        gradle "gradle"
    }

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        HOST = credentials('HOST')
    }

    parameters {
        booleanParam(defaultValue: true, name: 'api')
        booleanParam(defaultValue: false, name: 'ui')
    }

    stages {
        stage('Gradle clean') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('API') {
            when {
                expression { return params.api }
            }
            steps {
                sh './gradlew test --tests com.epam.api.tests.* -Dbase.url=$HOST:8080'
            }
        }
        stage('UI') {
            when {
                expression { return params.ui }
            }
            steps {
                sh './gradlew test --tests com.epam.ui.tests.* -Dselenide.headless=true -Dbase.url=$HOST:8080'
            }
        }
    }
    post {
        always {
            allure([
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'build/allure-results']]
            ])
            sh './gradlew sonarqube -Dsonar.token=$SONAR_TOKEN -Dsonar.host.url=$HOST:9000'
        }
    }
}