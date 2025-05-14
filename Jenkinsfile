pipeline {
   
   agent any
   
   
   
   stages {
      
      stage("build") {
         steps {
            echo("build the project")
         }
      }
      
      stage("Run Unit test") {
         steps {
            echo("run UTs")
         }
      }
      
      stage("Run Integration test") {
         steps {
            echo("run ITs")
         }
      }
      
      stage("Deploy to dev") {
         steps {
            echo("deploy to dev")
         }
      }
      
      stage("Deploy to QA") {
         steps {
            echo("deploy to QA")
         }
      }
      
      stage('Run Regression Automation Tests') {
         when {
            expression {
               currentBuild.result == null || currentBuild.result == 'SUCCESS'
            }
         }
         steps {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
               git branch: 'main', url: 'https://github.com/sudeeppanshikar/Dec2024POMFramework.git'
               sh "mvn clean install"
            }
         }
         post {
            failure {
               script {
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }
      
      stage('Publish Allure Reports') {
         steps {
            script {
               allure([
               includeProperties: false,
               jdk: '',
               properties: [],
               reportBuildPolicy: 'ALWAYS',
               results: [[path: '/allure-results']]
               ])
            }
         }
      }
      
      stage("Deploy to stage") {
         steps {
            echo("deploy to stage")
         }
      }
      
      stage("Run sanity test cases on Stage") {
         steps {
            echo("Run sanity test cases on Stage")
         }
      }
      
      stage("Deploy to uat") {
         steps {
            echo("deploy to stage")
         }
      }
      
      stage("Run sanity test cases on uat") {
         steps {
            echo("Run sanity test cases on UAT")
         }
      }
      
      stage("Deploy to PROD") {
         steps {
            echo("deploy to PROD")
         }
      }
      
      
      
   }
   
   
}