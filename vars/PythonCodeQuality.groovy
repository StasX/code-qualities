def banditScan() {
    try {
        sh '''
        python -m bandit \
            -r ./ \
            -x ./.venv,./venv \
            -f json \
            -o bandit.json
        '''
    } finally {
        archiveArtifacts(
            artifacts:'bandit.json',
            fingerprint:true
        )
    }
}