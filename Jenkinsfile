pipeline {
    agent {
        docker {
            image 'maven:3.6.1-jdk-8-alpine' 
            args ' -v $HOME/.m2:/root/.m2 -v /root/.ssh:/root/.ssh' 
        }
    }
    stages {
		stage('Prepare') {
		    steps {
			sh 'apk update'
			sh 'apk add rsync graphviz openssh mariadb mariadb-client openrc git'
			//sh '/usr/bin/mysql_upgrade'
			sh 'mysql_install_db --user=mysql --rpm'
			sh '/usr/bin/mysqld_safe &'
			sh 'sleep 5' // for mysql to startup
			//sh 'mysql -u root -e "CREATE DATABASE HA;"'
			//sh 'mysql -u root HA < log4j.sql'
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
				withMaven() {
					sh '$MVN_CMD -T 1C -B -DskipTests clean deploy'
					//sh 'mvn org.pitest:pitest-maven:mutationCoverage -DtimeoutConstant=8000'
				}
            }

		}
   		stage('Sonarqube') {
   			steps {
   				//org.jacoco:jacoco-maven-plugin:prepare-agent
   		    	sh '$MVN_CMD -DskipTests=true  sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=$SONAR_TOKEN -Dsonar.organization=homeautomation'
   			}
   		}	
//	    stage('Deploy') {
//	       parallel {
//	      		 stage('JUnit') {
//					steps {
//						junit '**/target/surefire-reports/**/*.xml'  
//		            }
//				}
//				stage('Deploy') {
//	        		steps {
//	        			sh 'mvn deploy:deploy-file -Dfile=target/HomeAutomationBase-0.0.1-SNAPSHOT.jar -DpomFile=pom.xml -DrepositoryId=archiva.snapshots -Durl=http://jenkins:8081/repository/snapshots'
//	   				}
//	   			}
//				stage('Archive') {
//	   			    steps {
//	   			        archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
//	   			    	archiveArtifacts artifacts: '**/pom.xml', fingerprint: true
//	   			    }	   			    
//	   			}	  
//	   		}	
//	    }
    }
}
