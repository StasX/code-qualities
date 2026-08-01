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

def infectionTesting() {
    try {
        sh '''
        vendor/bin/infection \
            --threads=auto \
            --log-xml=infection.xml \
            --logger-summary=infection-summary.txt
        '''
    } finally {
        archiveArtifacts(
            artifacts:'infection.xml,infection-summary.txt',
            fingerprint:true,
            allowEmptyArchive:true
        )
    }
}
