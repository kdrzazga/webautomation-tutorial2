pipeline {
    agent any

    tools{
        git 'Default'
    }

    triggers {
        cron('15 0 * * *')
    }

    stages {
        stage('checkout'){
            steps {
                git 'https://github.com/kdrzazga/webautomation-tutorial2'
            }
            post {
                always {
                    echo 'Checkout finished.'
                }
                success {
                    echo "Project checked out from GH"
                }
            }
        }
        stage('build') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean compile"
            }
            post {
                failure {
                    echo "Build failed"
                }
            }
        }
        stage('test'){
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true test"
            }
            post {
                success {
                    echo "Tests passed"
                }
            }
        }
        stage('deploy'){
            steps {
                script {
                    deploy
                }

            }
        }
    }
}
