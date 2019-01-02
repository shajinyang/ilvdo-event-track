package com.ilvdo.event_track_compier;

import com.google.auto.service.AutoService;
import com.ilvdo.event_track_annotation.Events;
import com.ilvdo.event_track_annotation.Path;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.*;

/**
 * Created by sjy on 2018/12/27
 * Describe 路径注解处理
 */
@AutoService(Processor.class)
public class EventPathProcessor extends AbstractProcessor {

    //控制台信息打印
    private Messager mMessager;
    //文件处理
    private Filer mFileUtils;
    //元素处理
    private Elements mElementUtils;
    //事件路径存储
    private HashMap<String,ArrayList> eventPaths;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        mFileUtils = processingEnvironment.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        eventPaths= new HashMap<>();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mMessager.printMessage(Diagnostic.Kind.NOTE,"开始处理进程Processor——process");
        //获取注解的 元素(变量、类、方法)
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Events.class);
        if(null != elements){
            mMessager.printMessage(Diagnostic.Kind.NOTE,"找到了注解"+elements.size()+"个");
            for(Element element:elements){
                mMessager.printMessage(Diagnostic.Kind.NOTE,"被注解的类是："+element.getSimpleName());
                Events annotation = element.getAnnotation(Events.class);
                Path[] paths = annotation.value();
                for (Path path:paths
                     ) {
                    String[] ids=path.path();
                    String id=path.id();
                    ArrayList<String> list=new ArrayList<>();
                    for (String i:ids
                         ) {
                        list.add(i);
                    }
                    eventPaths.put(id,list);
                }
                ProxyInfo  proxyInfo = new ProxyInfo(eventPaths,mFileUtils);
            }
        }else {
            mMessager.printMessage(Diagnostic.Kind.NOTE,"未找到符合条件的注解");
        }
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotation = new LinkedHashSet<>();
        annotation.add(Events.class.getCanonicalName());
        return annotation;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }


}
