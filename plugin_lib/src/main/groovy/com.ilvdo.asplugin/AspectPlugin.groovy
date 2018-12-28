package com.ilvdo.asplugin

import com.android.build.gradle.AppPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

/**
 * Created by sjy on 2018/12/26
 * Describe gradle 插件类 该类为配置aspectJ 的插件
 */
public class AspectPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {

        if(!project.android){

            throw new IllegalStateException('Must apply \'com.android.application\' or \'com.android.library\' first!');

        }
//        def hasApp = project.plugins.withType(AppPlugin)
//        def hasLib = project.plugins.withType(LibraryPlugin)
//        if (!hasApp && !hasLib) {
//            throw new IllegalStateException("'android' or 'android-library' plugin required.")
//
//        }

        project.dependencies {
            api 'org.aspectj:aspectjrt:1.8.6'
        }

//        project.configurations {
            final def variants
            final def log = project.logger
            variants = project.android.applicationVariants
            variants.all { variant ->
                JavaCompile javaCompile = variant.javaCompile
                javaCompile.doLast {
                    String[] args = [
                            "-showWeaveInfo",
                            "-1.5",
                            "-inpath", javaCompile.destinationDir.toString(),
                            "-aspectpath", javaCompile.classpath.asPath,
                            "-d", javaCompile.destinationDir.toString(),
                            "-classpath", javaCompile.classpath.asPath,
                            "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)
                    ]
                    log.debug "ajc args: " + Arrays.toString(args)

                    MessageHandler handler = new MessageHandler(true)
                    new Main().run(args, handler)
                    for (IMessage message : handler.getMessages(null, true)) {
                        switch (message.getKind()) {
                            case IMessage.ABORT:
                            case IMessage.ERROR:
                            case IMessage.FAIL:
                                log.error message.message, message.thrown
                                break
                            case IMessage.WARNING:
                                log.warn message.message, message.thrown
                                break
                            case IMessage.INFO:
                                log.info message.message, message.thrown
                                break
                            case IMessage.DEBUG:
                                log.debug message.message, message.thrown
                                break
                        }
                    }

                }
            }

//        }



    }

}