apiVersion = '0.0.0'

pipeline{
	agent any
	
	environment{
	EPHEMERAL_HOST = "${params.EPHEMERAL_HOST}"}
	CONTAINER_API_PATH = "${params.CONTAINER_API_PATH}"
	API_EPHEMERAL_URL = "http://${EPHEMERAL_HOST}:9998"
	}
	stages{
		stage('Pruebas de Unidad'){
			agent{
				docker{image 'maven:3.6.3-jdk-11-slim'}
			}	
			steps{
				sh 'echo "Pruebas unitarias"'
				sh 'cd /tmp && ls'
				sh 'echo "Obteniendo dependencias"'
				sh 'mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline'
				sh 'echo "Ejecución de  pruebas unitarias"'
				sh 'mvn test'
			}
		}

		stage('peparar el nombre del api api-calc'){
			echo "Obteniendo versión con maven"
			echo "antes: ${apiVersion}"
			sh 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout > backend.txt'
			script{
				apiVersion = "${CONTAINER_API_PATH}:"+ readFile('backend.txt').trim()+ "-" + env.BUILD_NUMBER
			}
			echo "Despues: ${apiVersion}"
		}

		stage('Setup del ambiente efimero'){
			echo "construyendo el api ${apiVersion}"
			sh 'docker build -t ${apiVersion} .'
			echo "Generar el archivo docker-compose"
			sh "sed -i 's@{{API_DOCKER_IMAGE}}@${apiVersion}@g' docker-compose.dist"
			sh 'cat docker-compose -f docker-compose.dist up -d'
			sh 'sleep 5'
			sh "docker-compose -f docker-compose.dist ps"
		}

		stage('Restart del ambiente compose'){
			steps{
				sh "docker-compose -f docker-compose.dist down"
				sh "docker-compose -f docker-compose.dist up -d"
				sh "sleep 10"
			}
		}

	}

}
post{
	always{
		"bajando el ambiente efimero..."
		sh "docker-compose -f docker-compose-dist down"
	}
	success{
		echo "success"
	}
	unstable{
		echo "unstable"
	}
	failure{
		echo "failure"
	}
}