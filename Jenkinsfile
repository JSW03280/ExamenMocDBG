pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'Maven3.9.11'
    }

    environment {
        VERSION_BACK = "1.0.0"
    }

    stages {
        stage ('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/JSW03280/ExamenMocDBG.git'
            }
        }

        stage ('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage ('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage ('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage ('Mover jar') {
            steps {
                bat 'echo "Eliminando directorio versiones..."'
                bat 'if exist "versiones" rmdir /s /q "versiones"'
            }

            post {
                success {
                    bat 'echo "Se crea el directorio versiones con la última versión de la api"'
                    bat 'mkdir versiones'
                    bat 'copy ".\\target\\EamenMocDBG-%VERSION_BACK%.jar" "versiones"'
                }
            }
        }

        /*
        stage ('Despliegue') {
            steps {
                bat '''
                    echo "Starting deploy..."
                    java -jar target/biblioteca-%VERSION_BACK%.jar
                '''
            }
        }
        */
    }
}