package com.codigo.aplios.toolbox.core.metrics;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
//
// import org.junit.runners.BlockJUnit4ClassRunner;
// import org.junit.runners.model.FrameworkMethod;
// import org.junit.runners.model.InitializationError;
//
// public class OrderedRunner extends BlockJUnit4ClassRunner {
//
// // -----------------------------------------------------------------------------------------------------------------
//
// public OrderedRunner(Class<?> clazz) throws InitializationError {
//
// super(clazz);
// }
//
// // -----------------------------------------------------------------------------------------------------------------
// // TODO:
// @Override
// protected List<FrameworkMethod> computeTestMethods() {
// final List<FrameworkMethod> list = super.computeTestMethods();
// final List<FrameworkMethod> copy = new ArrayList<>(list);
//
// Collections.sort(copy, (FrameworkMethod fm1, FrameworkMethod fm2) -> {
// Order o1 = fm1.getAnnotation(Order.class);
// Order o2 = fm2.getAnnotation(Order.class);
//
// if (o1 == null || o2 == null) {
// return -1;
// }
//
// return o1.order() - o2.order();
// });
//
// return copy;
// }
//
// // -----------------------------------------------------------------------------------------------------------------
// }