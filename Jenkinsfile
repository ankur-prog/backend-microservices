pipeline {
    agent any
    parameters {
        choice(name: 'ENVIRONMENT',
            choices: [DEV,STAGING,PRODUCTION],
            description: 'Select the environment to deploy.')
    }
    options {
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
        timeout(time: 12, unit: 'HOURS')
        timestamps()
    }
    triggers {
        cron '@midnight'
    }
    stages {
        stage('Make executable') {
            steps {
                sh('chmod +x ./scripts/build.sh')
            }
        }
        stage('Relative path') {
            steps {
                sh("./scripts/build.sh ${env.ENVIRONMENT}")
            }
        }
        stage('Full path') {
            steps {
                sh("${env.WORKSPACE}/scripts/build.sh ${env.ENVIRONMENT}")
            }
        }
        stage('Change directory') {
            steps {
                dir("${env.WORKSPACE}/scripts"){
                    sh("./build.sh ${env.ENVIRONMENT}")
                }
            }
        }
    }
}