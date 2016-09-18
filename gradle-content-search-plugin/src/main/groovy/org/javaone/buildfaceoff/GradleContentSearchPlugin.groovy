package org.javaone.buildfaceoff

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author jbaruch
 * @since 9/16/16
 */
class GradleContentSearchPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create('contentSearch', ContentSearchPluginExtension)
        project.tasks.create('searchContent', SearchContentTask) {
            project.afterEvaluate {
                filesToSearch = project.files(project.sourceSets.main.java.files)
                stringsToFind = project.contentSearch.stringsToFind
                fail = project.contentSearch.fail
            }
            it.outputs.upToDateWhen { true }
        }
    }
}

