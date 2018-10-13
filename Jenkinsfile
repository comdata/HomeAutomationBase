pipeline {
    agent {
        docker {
            image 'maven:3.5.4-jdk-8-alpine' 
            args '-v /var/jenkins_home/.m2:/root/.m2 -v /root/.ssh:/root/.ssh' 
        }
    }
    stages {
		stage('Prepare') {
		    steps {
			sh 'apk update'
			sh 'apk add rsync openssh mariadb mariadb-client openrc git'
			sh 'mysql_install_db --user=mysql --rpm'
			sh '/usr/bin/mysqld_safe &'
			sh 'sleep 5' // for mysql to startup
			sh 'mysql -u root -e "CREATE DATABASE HA;"'
			sh 'mysql -u root HA < log4j.sql'
		    } 
		}
	
		stage('Build dependencies') {
		    steps {
			sh 'rm -rf /root/git'
			sh 'mkdir -p /root/git'
	//		sh 'cd /root/git'
	//		sh 'cd /root/git && rm -rf obera-base zwave'
	//		sh 'cd /root/git && git clone https://github.com/oberasoftware/obera-base.git'
	//		sh 'cd /root/git && cd obera-base && mvn -DskipTests install && cd ..'
	//		sh 'cd /root/git && git clone https://github.com/comdata/zwave.git'
	//		sh 'cd /root/git && cd zwave && mvn -DskipTests install && cd ..'
	//		sh 'cd /root/git && git clone https://github.com/comdata/HomeAutomationZWave.git'
	//		sh 'cd /root/git && cd HomeAutomationZWave && mvn -DskipTests install && cd ..'
		    }
		}

		stage('Build') { 
			steps {
				sh 'mvn -T 1C -B clean install'
            }

		}
	
	    stage('Deploy') {
	       parallel {
	      		 stage('JUnit') {
					steps {
						junit '**/target/surefire-reports/**/*.xml'  
		            }
				}
				stage('Deploy') {
	        		steps {
	        			sh 'mvn deploy:deploy-file -Dfile=target/HomeAutomationBase-0.0.1-SNAPSHOT.jar -DpomFile=pom.xml -DrepositoryId=archiva.snapshots -Durl=http://jenkins:8081/repository/snapshots'
	   				}
	   			}
	  
	   		}	
	    }
    }
}
