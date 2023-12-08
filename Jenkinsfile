#!groovy
def BN = (BRANCH_NAME == 'master' || BRANCH_NAME.startsWith('releases/')) ? BRANCH_NAME : 'releases/2024-06'

library "knime-pipeline@$BN"

properties([
	buildDiscarder(logRotator(numToKeepStr: '5')),
	disableConcurrentBuilds()
])

try {
	node('maven && large && java17') {
		stage('Checkout Sources') {
			env.lastStage = env.STAGE_NAME
			checkout scm
		}

		stage('Materialize API Baseline') {
			env.lastStage = env.STAGE_NAME

			sh '''#!/bin/bash -eux
			    DEST="/var/cache/jenkins/p2/baselines/$BRANCH_NAME"
                TEMP_DEST="$DEST.$$"
                mkdir -p "$TEMP_DEST"
                cp org.knime.sdk.setup/API-Baseline.target "$TEMP_DEST/"
                /opt/p2-director/p2-director -data "$TEMP_DEST" -application org.eclipse.pde.api.tools.apiAnalyzer \\
                    -project org.knime.sdk.setup -baseline "$TEMP_DEST/API-Baseline.target" -vmargs -Xmx2048m

                if [[ -d "$DEST" ]]; then
                    mv "$DEST" "$DEST.old"
                fi
                mv "$TEMP_DEST" "$DEST"
                rm -rf "$DEST.old"
			'''
        }
	}
} catch (ex) {
	currentBuild.result = 'FAILURE'
	throw ex
} finally {
	notifications.notifyBuild(currentBuild.result);
}
/* vim: set shiftwidth=4 expandtab smarttab: */
