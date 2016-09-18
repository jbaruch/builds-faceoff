package org.javaone.buildfaceoff

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

/**
 * @author jbaruch
 * @since 9/17/16
 */
class SearchContentTask extends DefaultTask {


    @InputFiles
    FileCollection filesToSearch

    @Input
    List<String> stringsToFind

    @Input
    boolean fail

    @TaskAction
    void searchContent() {
        def found = filesToSearch.any { File file ->
            def results = stringsToFind.findAll { file.text.contains(it) }
            if (results) {
                logger.warn("WARNING! $results found in $file!")
            }
            results
        }
        if (found && fail) {
            throw new GradleException("Build aborted, search terms found (see warnings above)")
        }

    }
}
