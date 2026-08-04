def phpCodeSnifferTesting() {
    try {
        sh '''
        vendor/bin/phpcs \
            --standard=phpcs.xml.dist \
            --report=checkstyle \
            --report-file=phpcs.xml
        '''
    } finally {
        archiveArtifacts(
            artifacts:'phpcs.xml',
            fingerprint:true,
            allowEmptyArchive:true
        )
    }
}

def phpCSFixerTesting() {
    try {
        sh '''
        vendor/bin/php-cs-fixer check \
            --config=.php-cs-fixer.dist.php \
            --diff \
            --verbose \
            | tee php-cs-fixer.txt
        '''
    } finally {
        archiveArtifacts(
            artifacts:'php-cs-fixer.txt',
            fingerprint:true,
            allowEmptyArchive:true
        )
    }
}

def phpStanTesting() {
    try {
        sh '''
        #!/usr/bin/env bash
        set -o pipefail

        phpstan analyse \
            2>&1 | tee phpstan-report.txt
        '''
    } finally {
        archiveArtifacts(
            artifacts:'phpstan-report.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}
