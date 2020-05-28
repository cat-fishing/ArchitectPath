package com.scg.test.springtest.lesson_01;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 需要注入IOC容器类数组
 */
public class ImportSelectorDemo implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.scg.test.springtest.lesson_01.Coffee"};
    }
}
