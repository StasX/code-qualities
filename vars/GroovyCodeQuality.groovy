def codeNarcTesting() {
    try {
        sh 'gradle codenarcMain --continue --no-daemon'
    } finally {
        archiveArtifacts(
            artifacts: 'build/reports/codenarc/**/*.html',
            allowEmptyArchive: true
        )
    }
}

def spotBugsTesting() {
    try {
        sh 'gradle spotbugsMain --continue --no-daemon'
    } finally {
        archiveArtifacts(
            artifacts: 'build/reports/spotbugs/**/*.html,build/reports/spotbugs/**/*.xml',
            allowEmptyArchive: true
        )
    }
}