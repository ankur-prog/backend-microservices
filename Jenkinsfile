pipeline {
    agent any
    options {
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
        timeout(time: 12, unit: 'HOURS')
    }
    stages {
        stage("Hello!\nPlease read the logs.") {
            steps {
                echo "Hello! This is my first jenkin pipeline."
            }
        }
    }
}
