pipeline {
    agent any

    tools {
        jdk 'Java 17'
        gradle "gradle"
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
                sh './gradlew test --tests com.epam.api.tests.* -Dbase.url=http://192.168.0.4:8080'
            }
        }
        stage('UI') {
            when {
                expression { return params.ui }
            }
            steps {
                sh './gradlew test --tests com.epam.ui.tests.* -Dselenide.headless=true -Dbase.url=http://192.168.0.4:8080'
            }
        }
    }
    post {
        always {
            allure([
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'build/allure-results']]
            ])
            sh './gradlew sonarqube -Dsonar.token=sqp_743dc947795ef998d1be107bf86731d94be2974f -Dsonar.host.url=http://192.168.0.4:9000'
        }
    }
}