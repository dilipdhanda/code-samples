package com.c0.intellib.Spring;

public class _Hibernate {
  /*
  HibernateTemplate implements: HibernateOperations, InitializingBean, and Serializable
  but NOT BeanFactoryAware

  BeanFactoryAware is a Spring Framework interface that allows a bean to be aware of its owning
  BeanFactory — the container that created it. It’s a callback interface that lets your bean get a reference
  to the Spring IoC container itself.
  - used in Rare, framework-level scenarios where a bean truly needs to interact with the container
  (e.g., a custom framework component, dynamic bean lookup, plugin system, etc.)
  - Avoid it. In normal app code. You should prefer constructor injection or @Autowired instead.
   */
}
