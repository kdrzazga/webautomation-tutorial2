pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo off
                echo Stage: Checkout
                if exist WA2 rmdir /q /s WA2
                mkdir WA2
                cd WA2
                git clone https://github.com/kdrzazga/webautomation-tutorial2.git
                java --version
            }
        }

        stage('Build') {
            steps {
                echo off
                echo Stage: Build
                cd WA2\webautomation-tutorial2\
                dir
                git checkout adapting_pom
                c:\maven\bin\mvn clean install -DskipTests
            }
        }

        stage('Unit and UI Test') {
            steps {
                echo off
                echo Stage: Unit and UI Test
                cd WA2\webautomation-tutorial2\
                echo "Running specified tests only"
                c:\maven\bin\mvn test -Dtest=AnotherSpringTutorialApplicationTests,WebpageTests,TestDataEncryptorTest
                echo ---------------------------------
            }
        }

        stage('pseudo-Deploy') {
            steps {
                echo off
                echo Stage: pseudo-Deploy
                cd WA2\webautomation-tutorial2\target
                start /B java -jar webautomation-tutorial2-0.8.0-SNAPSHOT-jar-with-dependencies.jar
            }
        }

        stage('API Tests') {
            steps {
                echo off
                echo Stage: API Test
                cd WA2\webautomation-tutorial2\
                echo "Running specified tests only"
                c:\maven\bin\mvn test -Dtest=GlobalInfoTests,SirThaddeusTextTests#testFirstLine,SirThaddeusTextTests#testSecondLine,SirThaddeusTextTests#testNegative
                echo ---------------------------------
            }
        }
    }
}