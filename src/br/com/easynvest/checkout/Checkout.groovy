package br.com.easynvest.checkout
class Checkout{
    def call (jenkins) {
        jenkins.echo "Checkout Step"

        def gitVars = jenkins.checkout([
                $class: 'GitSCM',
                branches: [[name: jenkins.env.REPO_BRANCH]],
                doGenerateSubmoduleConfigurations: false,
                extensions: [[
                    $class: 'LocalBranch',
                    localBranch: jenkins.env.REPO_BRANCH
                ], [
                    $class: 'CleanBeforeCheckout'
                ], [
                    $class: 'CloneOption',
                    depth: 1,
                    noTags: false,
                    reference: '',
                    shallow: true,
                    timeout: 10
                ]], 
                submoduleCfg: [],
                userRemoteConfigs: [[url: jenkins.env.REPO_URL]]
            ])

        jenkins.env.GIT_COMMIT = "${gitVars.GIT_COMMIT}"
        jenkins.sh label: "List files", script: "ls -la"

    }
}
