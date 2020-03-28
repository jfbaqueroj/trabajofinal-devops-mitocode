pipeline {
	agent any
		
	stages{

		agent{
		
		docker{image 'maven:3.6.3-jdk-11-slim'}	

		stage('Archivar el JAR') {
			   steps {
				sh 'echo Creacion carpeta Archivo_JAR'			   
                sh 'mkdir Archivo_JAR'
                sh 'mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline'
                sh 'echo "ejecucion de maven completa. Ubicacion actual: "'                
                sh 'pwd'
                sh 'cp /target/*.jar /Archivo_JAR'                
                sh 'echo "Copiado del jar en /Archivo_JAR completo"'
                
            }
		}
	}
	}
	post {
        always {
        	sh 'cd /Archivo_JAR'
            archiveArtifacts artifacts: '*.jar', onlyIfSuccessful: true
        }
    }
}