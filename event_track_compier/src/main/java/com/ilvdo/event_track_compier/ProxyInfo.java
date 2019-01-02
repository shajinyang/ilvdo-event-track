package com.ilvdo.event_track_compier;

import com.alibaba.fastjson.JSON;
import com.squareup.javapoet.*;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.*;

/**
 * 代理类生成
 */
public class ProxyInfo {


    private static final String packageName = "com.ilvdo.event_track_compier";   //包名

    private static final String proxyClassName = "ProxyEventPaths";  // 生成的类的名称

    private static final String interclassName = "IGetEventPaths";  // 需要代理的类的名称
    private static final String interPackageName = "com.ilvdo.event_track_api.inter";  // 需要代理的类的包名

    private Filer mFileUtils;

    //存放view的id,元素
    public HashMap<String,ArrayList> injectValues;

    public ProxyInfo(HashMap<String,ArrayList> arrayValues, Filer mFileUtils) {
        this.injectValues=arrayValues;
        this.mFileUtils = mFileUtils;
        generateJavaCode();
    }

    /**
     * 生成java文件代码
     *
     * @return
     */
    public void generateJavaCode() {
        JavaFile javaFile;
        javaFile = JavaFile.builder(packageName,
                TypeSpec.classBuilder(proxyClassName)
                        .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                        .addSuperinterface(ClassName.get(interPackageName, interclassName))
                        .addMethod(
                                MethodSpec.methodBuilder("getPathList")
                                        .addAnnotation(Override.class)
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(String.class)
                                        .addCode(String.format("return \"%s\";", JSON.toJSONString(injectValues).replace("\"","\\\"")))
                                        .build()
                        ).build()
        ).build();

        try {
            javaFile.writeTo(mFileUtils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}