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
            allowEmptyArchive:true,
            fingerprint:true
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
            > php-cs-fixer.txt 2>&1
        status=$?

        cat php-cs-fixer.txt
        exit "$status"
        '''
    } finally {
        archiveArtifacts(
            artifacts:'php-cs-fixer.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}

def phpStanTesting() {
    try {
        sh '''
        vendor/bin/phpstan analyse \
            > phpstan-report.txt 2>&1
        status=$?

        cat phpstan-report.txt
        exit "$status"
        '''
    } finally {
        archiveArtifacts(
            artifacts:'phpstan-report.txt',
            allowEmptyArchive:true,
            fingerprint:true
        )
    }
}