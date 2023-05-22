// Declarative Pipeline using groovy script

// Defining 
def gv

pipeline{
    agent any
    environment{
        MY_VERSION_VAR = "1.0.0"
    }
    // parameters could be used in external groovy script
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'],description: 'Version to deploy')
        booleanParam(name: 'isRelease', defaultValue: false, description: '')
    }
    stages{
        stage("init"){
            steps{
                echo "init"
                echo "version project: ${VERSION}"
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("executing some Groovy script"){
            steps{
                script {
                    gv.scriptingApp()
                }
            }
        }        
        stage("build dev"){
            options{
                ansiColor("xterm")
            }
            when{
                branch "development"
            }
            steps{
                echo "build your nice project with version ${MY_VERSION_VAR}"
            }
        }
        stage("build master"){
            when{
                branch "master"
            }
            steps{
                echo "build your nice project!"
            }
        }
        stage("accept deployment"){
            options{
                timeout(time: 2, unit: "MINUTES")
            }
            steps{
                input(message: "Approve deploy?", ok:"Yes")
            }
        }
        stage("executing node"){
            agent{
                docker { image 'sebasnaa/nodeagent:1.2' }
            }
            steps{
                script {
                    gv.executeNode()
                }
            }
        }        
    }
}