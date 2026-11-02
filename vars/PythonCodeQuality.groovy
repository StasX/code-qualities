def pylintTesting() {
    try {
        sh '''
        status=0
        python -m pylint . \
            > pylint-report.txt 2>&1 || status=$?

        cat pylint-report.txt
        exit "$status"
        '''
    } finally {
        archiveArtifacts(
            artifacts:'pylint-report.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}

def blackTesting() {
    try {
        sh '''
        status=0
        python -m black --check --diff . \
            > black-report.txt 2>&1 || status=$?

        cat black-report.txt
        exit "$status"
        '''
    } finally {
        archiveArtifacts(
            artifacts:'black-report.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}

def mypyTesting() {
    try {
        sh '''
        status=0
        python -m mypy . \
            > mypy-report.txt 2>&1 || status=$?

        cat mypy-report.txt
        exit "$status"
        '''
    } finally {
        archiveArtifacts(
            artifacts:'mypy-report.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}
