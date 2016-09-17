package org.javaone.buildfaceoff

import org.gradle.api.GradleException
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
        project.task('searchContent') << {
            boolean found = false

            project.contentSearch.stringsToSearch.each { String str ->
                project.files(project.sourceSets.main.java.files).each { File file ->
                    if (file.text.contains(str)) {
                        project.logger.warn("WARNING: Found $str in $file")
                        found = true
                    }
                }
            }
            if(found && project.contentSearch.fail){
                throw new GradleException("Build aborted, search terms found (see warnings above)")
            }
        }
    }
}
