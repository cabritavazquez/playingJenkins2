def scriptingApp(){
    echo "building App with an external groovy script..."
}
def executeNode(){
    sh 'node -v'
    sh 'node holamundo.js'
}

def deployApp(){
    echo "deploying App with an external groovy script..."
}

return this