package kr.heartpattern.exhibitionismmaven;

import kr.heartpattern.exhibitionism.ExhibitionismKt;
import kr.heartpattern.exhibitionism.ExhibitionismOptions;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mojo(name = "exhibit", defaultPhase = LifecyclePhase.PACKAGE)
public class Exhibitionism extends AbstractMojo {
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(property = "output")
    private File output;

    @Parameter(property = "parallel", defaultValue = "1")
    private int parallel;

    @Parameter(property = "publify", defaultValue = "true")
    private boolean publify;

    @Parameter(property = "open", defaultValue = "true")
    private boolean open;

    @Parameter(property = "path")
    private String[] paths;

    @Parameter(property = "exhibit.debug")
    private boolean debug;

    @Parameter(property = "noStaticFinal")
    private boolean noStaticFinal;

    public void execute() throws MojoExecutionException {
        Artifact artifact = project.getArtifact();
        File tempOut = output;
        if (tempOut == null) {
            tempOut = new File(artifact.getFile().getParentFile(), "exhibitionism.tmp");
        }

        Logger logger = Logger.getLogger("Maven");
        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(Level.ALL);
        logger.setLevel(Level.ALL);
        logger.addHandler(console);
        console.setFormatter(new SimpleFormatter());
        if (!debug) {
            logger.setLevel(Level.WARNING);
        }

        ExhibitionismKt.transform(
                new ExhibitionismOptions(
                        project.getArtifact().getFile(),
                        tempOut,
                        logger,
                        parallel,
                        new HashSet<>(Arrays.asList(paths)),
                        publify,
                        open,
                        noStaticFinal
                )
        );

        if (output == null) {
            project.getArtifact().getFile().delete();
            tempOut.renameTo(project.getArtifact().getFile());
        }
    }
}
