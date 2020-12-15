package br.com.easynvest.checkout
class Checkout{
    def call (jenkins, jobParams) {
        jenkins.echo "Checkout Step"

        def gitVars = jenkins.checkout([
                $class: 'GitSCM',
                branches: [[name: jobParams.repoBranch]],
                doGenerateSubmoduleConfigurations: false,
                extensions: [[
                    $class: 'LocalBranch',
                    localBranch: jobParams.repoBranch
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
                userRemoteConfigs: [[url: jobParams.repoUrl]]
            ])

        jenkins.env.GIT_COMMIT = "${gitVars.GIT_COMMIT}"
        jenkins.sh label: "List files", script: "ls -la"

    }
}
